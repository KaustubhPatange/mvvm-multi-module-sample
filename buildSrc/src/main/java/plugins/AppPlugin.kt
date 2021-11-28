package plugins

import org.gradle.api.Plugin
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.gradle.api.Project
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.JavaVersion
import org.gradle.kotlin.dsl.findByType
import org.gradle.kotlin.dsl.withType

class AppPlugin : Plugin<Project> {
  override fun apply(project : Project) = with(project) {
    configureDefaults()
    configureAndroidApp()
    configureAndroidLibrary()
  }

  private fun Project.configureDefaults() {
    extensions.findByType<BaseExtension>()?.apply {
      compileSdkVersion(BaseConfig.CompileVersionSdk)

      defaultConfig {
        vectorDrawables.useSupportLibrary = true
        minSdk = BaseConfig.MinSdk
        targetSdk = BaseConfig.TargetSdk

        versionCode = BaseConfig.VersionCode
        versionName = BaseConfig.VersionName

        testInstrumentationRunner = BaseConfig.TestRunner
      }

      testOptions {
        unitTests.isIncludeAndroidResources = true
      }

      compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
      }

      buildTypes {
        named("release") {
          isMinifyEnabled = false
          proguardFiles(
            getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
          )
        }
      }
    }
    project.tasks.withType<KotlinCompile>().configureEach {
      kotlinOptions {
        jvmTarget = "11"
        freeCompilerArgs += listOf("-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi")
      }
    }
  }

  private fun Project.configureAndroidApp() {
    if (plugins.hasPlugin("com.android.application")) {
      extensions.findByType<BaseAppModuleExtension>()?.apply {
        buildFeatures.viewBinding = true
        defaultConfig {
          applicationId = BaseConfig.ApplicationId
        }
      }
    }
  }

  private fun Project.configureAndroidLibrary() {
    if (plugins.hasPlugin("com.android.library")) {
      extensions.findByType<LibraryExtension>()?.apply {
        buildFeatures.viewBinding = true
        buildFeatures.buildConfig = false
      }
    }
  }
}
