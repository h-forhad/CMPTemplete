plugins {
    alias(libs.plugins.androidApplication).apply(false)
    alias(libs.plugins.androidLibrary).apply(false)
    alias(libs.plugins.kotlinAndroid).apply(false)
    alias(libs.plugins.kotlinMultiplatform).apply(false)
    alias(libs.plugins.compose.compiler).apply(false)
    alias(libs.plugins.jetbrainsCompose).apply(false)
    alias(libs.plugins.buildkonfig).apply(false)
}

apply(from = "git-hooks.gradle.kts")
//task("addPreCommitGitHookOnBuild") {
//    println("⚈ ⚈ ⚈ Running Add Pre Commit Git Hook Script on Build ⚈ ⚈ ⚈")
//    exec {
//        commandLine("cp", "./.scripts/pre-commit", "./.git/hooks")
//    }
//    println("✅ Added Pre Commit Git Hook Script.")
//}

tasks.register("ensureGitHooks") {
    dependsOn("installGitPreCommitHook")
}