buildscript {
  repositories {
    gradlePluginPortal()
    google()
    mavenCentral()
  }

  dependencies {
    classpath(libs.androidGradlePlugin)
    classpath(libs.kotlinGradlePlugin)
    classpath(libs.hiltGradlePlugin)
    classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.31")
  }
}

tasks {
  val clean by registering(Delete::class) {
    delete(buildDir)
  }
}