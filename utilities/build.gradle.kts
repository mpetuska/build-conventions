plugins {
  `kotlin-dsl`
  alias(libs.plugins.build.konfig)
}

repositories {
  gradlePluginPortal()
  mavenCentral()
}

dependencies {
  implementation(project(":utils"))
  implementation(libs.plugin.detekt)
  implementation(libs.plugin.versions)
  implementation(libs.plugin.versions.update)
  compileOnly(libs.plugin.kotlin)
}

buildConfig {
  useKotlinOutput {
    internalVisibility = true
  }
  packageName("konfig.utils")
  forClass("Libs") {
    buildConfigField("detektFormatting", libs.detekt.formatting.map(MinimalExternalModuleDependency::toString))
  }
}
