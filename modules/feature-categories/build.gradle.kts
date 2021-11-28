plugins {
  id(GradlePlugins.AndroidLibrary)
  id(GradlePlugins.Kotlin)
  id(GradlePlugins.AppPlugin)
  kotlin(GradlePlugins.Kapt)
  id(GradlePlugins.DaggerHilt)
}

dependencies {
  implementation(project(":modules:core"))
  implementation(libs.bundles.android)
  implementation(libs.navigator)
  implementation(libs.retrofitConverterMoshi) // automatically pulls moshi
  implementation(libs.autoBindingsRoom)
  implementation(libs.autoBindingsRoomNoOp)
  kapt(libs.autoBindingsCompiler)

  implementation(libs.hiltAndroid)
  kapt(libs.hiltCompiler)

  testImplementation(libs.bundles.unitTests)
  androidTestImplementation(libs.bundles.androidTests)
}