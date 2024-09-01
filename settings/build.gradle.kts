plugins {
  `kotlin-dsl`
}

repositories {
  gradlePluginPortal()
  mavenCentral()
}

gradlePlugin {
  plugins {
    create("settings") {
      id = "settings"
      implementationClass = "SettingsPlugin"
    }
  }
}

dependencies {
  implementation(libs.plugin.git.hooks)
  implementation(libs.plugin.develocity)
}
