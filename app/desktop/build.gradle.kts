import org.jetbrains.compose.desktop.application.dsl.TargetFormat.Deb
import org.jetbrains.compose.desktop.application.dsl.TargetFormat.Dmg
import org.jetbrains.compose.desktop.application.dsl.TargetFormat.Msi

plugins {
    kotlin("jvm")

    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.jetbrainsCompose)
}

dependencies {
    implementation(projects.decomposeRouter)
    implementation(projects.app)
    implementation(projects.core)
    implementation(projects.onlineStore)
    implementation(compose.desktop.currentOs)
    implementation(libs.material.window.size)
    implementation(compose.runtime)
    implementation(libs.koin.core)
    implementation(compose.foundation)
    implementation(compose.material3)
    implementation(compose.materialIconsExtended)
    implementation(compose.preview)
    implementation(libs.decompose)
    implementation(libs.decompose.compose)
}

val appVersion = "1.0.0"
version = appVersion

compose.desktop {
    application {
        mainClass = "com.greenrobotdev.favily.desktop.ApplicationKt"

        nativeDistributions {
            buildTypes.release {
                proguard {
                    configurationFiles.from("proguard-rules.pro")
                    obfuscate.set(false)
                    optimize.set(false)
                }
            }

            targetFormats(Dmg, Msi, Deb)

            modules("java.instrument", "java.management", "jdk.unsupported")

            packageName = "Favily"

            val iconsRoot = project.file("src/main/resources/icons")

            macOS {
                iconFile.set { iconsRoot.resolve("favily-desktop.icns") }
                packageVersion = appVersion
                dmgPackageVersion = appVersion
                pkgPackageVersion = appVersion
            }

            windows {
                iconFile.set { iconsRoot.resolve("favily-desktop.ico") }
                menuGroup = "Favily"
                // see https://wixtoolset.org/documentation/manual/v3/howtos/general/generate_guids.html
                upgradeUuid = "18159995-d967-4CD2-8885-77BFA97CFA9F"
                packageVersion = appVersion
                msiPackageVersion = appVersion
            }

            linux {
                iconFile.set { iconsRoot.resolve("favily_desktop.png") }
            }
        }
    }
}
