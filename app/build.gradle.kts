plugins {
  id(GradlePlugins.AndroidApp)
  id(GradlePlugins.Kotlin)
  id(GradlePlugins.AppPlugin)
  kotlin(GradlePlugins.Kapt)
  id(GradlePlugins.DaggerHilt)
}

dependencies {
  implementation(project(":modules:core"))
  implementation(project(":modules:feature-categories"))
  implementation(project(":modules:feature-list"))
  implementation(libs.bundles.android)
  implementation(libs.constaintLayout)
  implementation(libs.navigator)
  implementation(libs.navigatorExtensions)

  implementation(libs.hiltAndroid)
  kapt(libs.hiltCompiler)

  testImplementation(libs.bundles.unitTests)
  androidTestImplementation(libs.bundles.androidTests)
}