import io.gitlab.arturbosch.detekt.Detekt
import konfig.utils.Libs
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

plugins {
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
        pluginManager.withPlugin("org.jetbrains.kotlin.jvm") {
          project.the<KotlinJvmProjectExtension>().sourceSets.all {
            source(kotlin)
          }
        }
        source(layout.projectDirectory.file("build.gradle.kts"))
        source(layout.projectDirectory.file("settings.gradle.kts"))
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
