plugins {
  `kotlin-dsl`
}

repositories {
  gradlePluginPortal()
  mavenCentral()
  google()
}

dependencies {
  implementation(project(":android"))
  implementation(project(":kotlin"))
  implementation(project(":utils"))
  implementation(libs.plugin.kotlin)
}
