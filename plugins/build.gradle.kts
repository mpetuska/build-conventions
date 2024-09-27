plugins {
  `kotlin-dsl`
}

repositories {
  gradlePluginPortal()
  mavenCentral()
  google()
}

dependencies {
  implementation(rootProject)
  implementation(project(":android"))
  implementation(project(":kotlin"))
  implementation(libs.plugin.kotlin)
}
