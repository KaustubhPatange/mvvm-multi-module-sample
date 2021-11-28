import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
  id(GradlePlugins.AndroidLibrary)
  id(GradlePlugins.Kotlin)
  id(GradlePlugins.AppPlugin)
  kotlin(GradlePlugins.Kapt)
  id(GradlePlugins.Parcelize)
  id(GradlePlugins.DaggerHilt)
}

android {
  buildFeatures.buildConfig = true
  defaultConfig {
    buildConfigField("String", "NewsApiKey", gradleLocalProperties(rootDir).getProperty("newsApiKey"))
  }
}

dependencies {
  implementation(project(":modules:core"))
  implementation(libs.bundles.android)
  implementation(libs.navigator)
  implementation(libs.swipeRefreshLayout)
  implementation(libs.retrofit)
  implementation(libs.retrofitConverterMoshi)
  implementation(libs.paging2)
  implementation(libs.glide)

  implementation(libs.epoxyCore)
  implementation(libs.epoxyPaging)
  kapt(libs.epoxyCompiler)

  implementation(libs.hiltAndroid)
  kapt(libs.hiltCompiler)

  testImplementation(libs.bundles.unitTests)
  androidTestImplementation(libs.bundles.androidTests)
}