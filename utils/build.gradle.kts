plugins {
  `embedded-kotlin`
}

repositories {
  gradlePluginPortal()
  mavenCentral()
}

dependencies {
  implementation(gradleApi())
  implementation(libs.plugin.kotlin)
}
