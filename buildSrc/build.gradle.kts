plugins {
  `kotlin-dsl`
  `kotlin-dsl-precompiled-script-plugins`
}

gradlePlugin {
  plugins {
    create("appPlugin") {
      id = "com.kpstv.appPlugin"
      implementationClass = "plugins.AppPlugin"
    }
  }
}

repositories {
  google()
  mavenCentral()
}

dependencies {
  implementation("com.android.tools.build:gradle:7.2.0-alpha03")
  implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.31")
  implementation(gradleApi())
}