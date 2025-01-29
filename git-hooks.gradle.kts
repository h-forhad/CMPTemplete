tasks.register("installGitPreCommitHook") {
  group = "git hooks"
  description = "Installs a pre-commit hook to block submodule changes"
  val hookScript = """
        #!/bin/sh
        # Check for submodule changes
        SUB_MODULES=${'$'}(git config --file .gitmodules --get-regexp path | awk '{print ${'$'}2}')
        for SUBMODULE in ${'$'}SUB_MODULES; do
            STAGED_FILES=${'$'}(git diff --cached --name-only -- "${'$'}SUBMODULE")
            if [ -n "${'$'}STAGED_FILES" ]; then
                echo "Error: Commit contains changes to submodule '${'$'}SUBMODULE'."
                echo "The following files are part of the submodule:"
                echo "${'$'}STAGED_FILES"
                exit 1
            fi
        done
        exit 0
    """.trimIndent()
  doLast {
    val hooksDir = File(rootProject.projectDir, ".git/hooks")
    hooksDir.mkdirs()
    val preCommitHook = File(hooksDir, "pre-commit")
    preCommitHook.writeText(hookScript)
    preCommitHook.setExecutable(true)
    println("Git pre-commit hook installed at ${preCommitHook.absolutePath}")
  }
}