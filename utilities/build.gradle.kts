plugins {
  `kotlin-dsl`
  alias(libs.plugins.build.konfig)
}

repositories {
  gradlePluginPortal()
  mavenCentral()
}

dependencies {
  implementation(rootProject)
  implementation(libs.plugin.detekt)
  implementation(libs.plugin.versions)
  implementation(libs.plugin.versions.update)
  implementation(libs.plugin.maven.deployer)
  implementation(libs.plugin.mkdocs)
  implementation(libs.plugin.dokkatoo)
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
