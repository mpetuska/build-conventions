import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

plugins {
  id("com.github.ben-manes.versions")
  id("nl.littlerobots.version-catalog-update")
}

versionCatalogUpdate {
  keep {
    keepUnusedVersions = false
    keepUnusedLibraries = false
    keepUnusedPlugins = false
  }
}

tasks {
  withType<DependencyUpdatesTask> {
    gradleReleaseChannel = "current"
    rejectVersionIf {
      fun isNonStable(version: String): Boolean {
        val stableKeyword = setOf("RELEASE", "FINAL", "GA").any { version.uppercase().contains(it) }
        val regex = Regex("^[0-9,.v-]+(-r)?$")
        return !stableKeyword && !(version matches regex)
      }
      isNonStable(candidate.version) && !isNonStable(currentVersion)
    }
  }
}
