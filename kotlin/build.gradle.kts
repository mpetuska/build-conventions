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
  implementation(libs.plugin.kotlin)
  implementation(libs.plugin.kotlin.serialization)
  implementation(libs.plugin.ksp)
}

buildConfig {
  useKotlinOutput {
    internalVisibility = true
  }
  packageName("konfig.kotlin")
  forClass("Libs") {
    buildConfigField("koinBom", libs.koin.bom.map(MinimalExternalModuleDependency::toString))
    buildConfigField("koinAnnotationsBom", libs.koin.annotations.bom.map(MinimalExternalModuleDependency::toString))
    buildConfigField("koinCompiler", libs.koin.compiler.map(MinimalExternalModuleDependency::toString))
  }
}
