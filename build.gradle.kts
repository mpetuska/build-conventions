import io.gitlab.arturbosch.detekt.Detekt

plugins {
  `kotlin-dsl` apply false
  `embedded-kotlin`
  alias(libs.plugins.versions)
  alias(libs.plugins.versions.update)
  alias(libs.plugins.detekt)
}

repositories {
  mavenCentral()
}

versionCatalogUpdate {
  keep {
    keepUnusedVersions = true
    keepUnusedLibraries = true
    keepUnusedPlugins = false
  }
}

dependencies {
  detektPlugins(libs.detekt.formatting)
  implementation(gradleApi())
  implementation(libs.plugin.kotlin)
}

tasks {
  withType<Detekt> {
    config.from(rootDir.resolve("gradle/detekt.yml"))
    parallel = true
    buildUponDefaultConfig = true
  }
}
