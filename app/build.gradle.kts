import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.buildkonfig)
    alias(libs.plugins.serialization)
}

kotlin {
    jvmToolchain(17)

    androidTarget()

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    jvm()

    sourceSets.commonMain.dependencies {
        api(projects.decomposeRouter)

        implementation(projects.core)
        implementation(projects.onlineStore)

        implementation(compose.ui)
        implementation(compose.foundation)
        implementation(compose.material3)
        implementation(compose.runtime)
        implementation(compose.materialIconsExtended)
        implementation(compose.material)
        implementation(compose.components.resources)
        implementation(libs.material.window.size)
        implementation(libs.russhwolf.settings)
        implementation(libs.russhwolf.settings.coroutines)

        implementation(libs.kotlin.corotines)
        implementation(libs.kotlin.datetime)
        implementation(libs.ktor.core)
        implementation(libs.ktor.serialization)
        implementation(libs.ktor.logging)
        implementation(libs.ktor.content.negotiation)

        implementation(libs.koin.core)
        implementation(libs.molecule.runtime)
        implementation(libs.koin.compose)
        implementation(libs.kstore)

        implementation(libs.qdsfdhvh.image.loader)
        implementation(libs.google.services.map)
        implementation(libs.decompose.compose)
        implementation(libs.calendar.compose.basis)
        implementation(libs.calendar.compose.ranges) // includes basis
        implementation(libs.calendar.compose.pager) // includes basis
        implementation(libs.calendar.compose.datepicker) // includes pager + ranges
//        implementation(libs.material.window.size)

    }

    sourceSets.androidMain.dependencies {
        implementation(libs.androidx.activity.compose)
        implementation(libs.ktor.client.android)
        implementation(libs.ktor.client.okhttp)
        implementation(libs.kstore.file)
        implementation(libs.androidx.appcompat)
    }

    sourceSets.jvmMain.dependencies {
        implementation(compose.desktop.currentOs)
        implementation(libs.ktor.client.cio)
        implementation(libs.kstore.file)
        implementation(libs.okio)
    }

    sourceSets.iosMain.dependencies {
        implementation(libs.kstore.file)
        implementation(libs.ktor.client.ios)
    }
}

/* Android Configuration */
android {
    compileSdk = 34
    namespace = "${App.packageName}.android"

    defaultConfig {
        minSdk = 28
    }
}

/* iOS Configuration */
kotlin.targets.withType<KotlinNativeTarget>().configureEach {
    binaries.framework {
        baseName = App.appName
        isStatic = true

        export(projects.decomposeRouter)
    }
}

buildkonfig {
    packageName = "${App.packageName}.app"

    defaultConfigs {
        val apiKey: String = gradleLocalProperties(projectRootDir = rootDir, providers = providers)
            .getProperty("apiKey")

        require(apiKey.isNotEmpty()) {
            "Register your api key from free store and place it in local.properties as `apiKey`"
        }

        buildConfigField(STRING, "API_KEY", apiKey)
    }
}

object App {
    object Versions {
        val major: Int = 1
        val minor: Int = 3
        val patch: Int = 0

        val suffix: String? = null

        val name = "$major.$minor.$patch"

        val fullname: String = name
            .let { version -> if (suffix.isNullOrEmpty()) version else "$version-$suffix" }
    }

    val packageName: String = "com.greenrobotdev.favily"
    val organisation: String = "greenrobotdev"
    val appName: String = "Favily"
}
