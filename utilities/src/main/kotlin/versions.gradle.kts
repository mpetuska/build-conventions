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
        val stableKeyword = setOf("RELEASE", "FINAL", "GA").any { version.contains(it, ignoreCase = true) }
        val unstableKeyword = setOf("alpha", "beta", "rc").any { version.contains(it, ignoreCase = true) }
        val regex = Regex("^[0-9,.v-]+(-r)?$")
        return unstableKeyword || (!stableKeyword && !(version matches regex))
      }
      isNonStable(candidate.version) && !isNonStable(currentVersion)
    }
  }
}
