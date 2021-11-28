plugins {
  id(GradlePlugins.AndroidLibrary)
  id(GradlePlugins.Kotlin)
  id(GradlePlugins.AppPlugin)
}

dependencies {
  implementation(libs.bundles.android)
  implementation(libs.glide)
}