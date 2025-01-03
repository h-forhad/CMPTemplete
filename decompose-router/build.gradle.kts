import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
  alias(libs.plugins.kotlinMultiplatform)
  alias(libs.plugins.androidLibrary)
  alias(libs.plugins.compose.compiler)
  alias(libs.plugins.jetbrainsCompose)
}

kotlin {
  androidTarget {
    publishLibraryVariants("release", "debug")
  }

  iosX64()
  iosArm64()
  iosSimulatorArm64()

  jvm("desktop") {
    compilations.all {
      kotlinOptions {
        jvmTarget = "17"
      }
    }
  }

  js(IR) {
    browser()
  }

  wasmJs {
    browser()
  }

  sourceSets {
    val commonMain by getting {
      dependencies {
        api(libs.decompose)
        implementation(compose.runtime)
        implementation(compose.foundation)
        implementation(libs.decompose.compose)
        implementation(libs.kotlinx.serialization.core)
      }
    }

    val commonTest by getting {
      dependencies {
        implementation(kotlin("test"))
      }
    }

    val iosX64Main by getting
    val iosArm64Main by getting
    val iosSimulatorArm64Main by getting
    val iosMain by creating {
      dependsOn(commonMain)
      iosX64Main.dependsOn(this)
      iosArm64Main.dependsOn(this)
      iosSimulatorArm64Main.dependsOn(this)
      dependencies {
      }
    }

    val androidMain by getting {
      dependencies {
        implementation(compose.material3)
        implementation(libs.decompose)
        implementation(libs.decompose.compose)
        implementation(libs.androidx.activity.ktx)
        implementation(libs.androidx.activity.compose)
        implementation(libs.androidx.fragment.ktx)
      }
    }

    val androidInstrumentedTest by getting {
      dependencies {
        implementation(libs.compose.ui.junit4)
      }
    }

    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    sourceSets.invokeWhenCreated("androidDebug") {
      dependencies {
        implementation(libs.compose.ui.test.manifest)
      }
    }

    jvmMain.dependencies {

      implementation(compose.desktop.currentOs)
    }

    val jsMain by getting {
      dependencies {
        implementation(libs.kotlin.browser)
      }
    }
  }
}

android {
  namespace = "io.github.xxfast.decompose.router"
  compileSdk = 34
  defaultConfig {
    minSdk = 25
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }

  testOptions {
    unitTests {
      isIncludeAndroidResources = true
    }
  }
}
