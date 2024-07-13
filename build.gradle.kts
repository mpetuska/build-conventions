plugins {
  `kotlin-dsl`
  alias(libs.plugins.versions)
  alias(libs.plugins.versions.update)
  alias(libs.plugins.build.konfig)
}

repositories {
  gradlePluginPortal()
  google()
  mavenCentral()
}

versionCatalogUpdate {
  keep {
    keepUnusedVersions = true
    keepUnusedLibraries = true
    keepUnusedPlugins = true
  }
}

sourceSets {
  main {
    kotlin.srcDir(projectDir.resolve("src/main/utils"))
  }
}

gradlePlugin {
  plugins {
    create("settings") {
      id = "settings"
      implementationClass = "settings.SettingsPlugin"
    }
  }
}

dependencies {
  implementation(libs.plugin.git.hooks)
  implementation(libs.plugin.develocity)
  implementation(libs.plugin.detekt)
  implementation(libs.plugin.kotlin)
  implementation(libs.plugin.kotlin.serialization)
  implementation(libs.plugin.versions)
  implementation(libs.plugin.versions.update)
  implementation(libs.plugin.android.library)
}

buildConfig {
  useKotlinOutput {
    internalVisibility = true
  }
  packageName("utils")
  forClass("Libs") {
    buildConfigField("detektFormatting", libs.detekt.formatting.map(MinimalExternalModuleDependency::toString))
  }
}
