import utils.Libs
import io.gitlab.arturbosch.detekt.Detekt
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

plugins {
  java
  id("io.gitlab.arturbosch.detekt")
}

dependencies {
  detektPlugins(Libs.detektFormatting)
}

tasks {
  if (project == rootProject) {
    register<Detekt>("detektAll") {
      description = "Runs detekt all all workspace sources"

      reports {
        sarif.required = true
      }

      fun Project.addSources() {
        pluginManager.withPlugin("org.jetbrains.kotlin.multiplatform") {
          project.the<KotlinMultiplatformExtension>().sourceSets.all {
            source(kotlin)
          }
        }
      }
      project.addSources()
      subprojects.forEach {
        it.addSources()
      }

      include("**/*.kt")
      include("**/*.kts")
    }
  }
  withType<Detekt> {
    config.from(rootDir.resolve("gradle/detekt.yml"))
    parallel = true
    buildUponDefaultConfig = true
  }
}
