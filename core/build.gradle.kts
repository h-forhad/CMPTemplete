import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING

plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.buildkonfig)
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {

    jvm()

    androidTarget()

    jvmToolchain(17)

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    )

    sourceSets.commonMain.dependencies {
        api(projects.decomposeRouter)
        implementation(compose.ui)
        implementation(compose.foundation)
        implementation(compose.material3)
        implementation(compose.material)
        implementation(compose.runtime)
        implementation(compose.materialIconsExtended)
        implementation(libs.material.window.size)
        implementation(compose.components.resources)
        implementation(libs.russhwolf.settings)
        implementation(libs.russhwolf.settings.coroutines)
        implementation(libs.russhwolf.settings.noarg)

        implementation(libs.kotlin.corotines)
        implementation(libs.kotlin.datetime)
        implementation(libs.ktor.core)
        implementation(libs.ktor.content.negotiation)
        implementation(libs.ktor.serialization)
        implementation(libs.ktor.logging)

        implementation(libs.koin.core)
        implementation(libs.molecule.runtime)
        implementation(libs.kstore)

    }

    sourceSets.androidMain.dependencies {
        implementation(libs.ktor.client.android)
        implementation(libs.ktor.client.okhttp)
        implementation(libs.kstore.file)
        implementation(libs.androidx.activity.ktx)
        implementation(libs.androidx.appcompat)
    }

    sourceSets.jvmMain.dependencies {
        implementation(compose.desktop.currentOs)
    }

    sourceSets.iosMain.dependencies {
        implementation(libs.kstore.file)
        implementation(libs.ktor.client.ios)
        implementation(libs.kstore.file)
        implementation(libs.ktor.client.ios)
    }
}

android {
    namespace = "com.greenrobotdev.planner"
    compileSdk = 34

    defaultConfig {
        minSdk = 28
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

buildkonfig {
    packageName = "com.greenrobotdev.wanderwise.core"

    defaultConfigs {
        val apiKey: String = gradleLocalProperties(projectRootDir = rootDir, providers = providers)
            .getProperty("apiKey")

        require(apiKey.isNotEmpty()) {
            "Register your api key from free store and place it in local.properties as `apiKey`"
        }

        buildConfigField(STRING, "API_KEY", apiKey)
    }
}