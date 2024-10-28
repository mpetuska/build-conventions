import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
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
    kotlin.sourceSets.all {
      source(kotlin)
    }
    source(layout.projectDirectory.file("build.gradle.kts"))
    source(layout.projectDirectory.file("settings.gradle.kts"))

    include("**/*.kt")
    include("**/*.kts")
  }
  withType<DependencyUpdatesTask> {
    gradleReleaseChannel = "current"
    rejectVersionIf {
      fun isNonStable(version: String): Boolean {
        val stableKeyword = setOf("RELEASE", "FINAL", "GA").any { version.contains(it, ignoreCase = true) }
        val unstableKeyword = setOf("alpha", "beta", "rc").any { version.contains(it, ignoreCase = true) }
        val regex = Regex("^[0-9,.v-]+(-r)?$")
        return unstableKeyword || (!stableKeyword && !(version matches regex))
      }
      isNonStable(candidate.version) && !isNonStable(currentVersion)
    }
  }
}
