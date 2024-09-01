import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinProjectExtension
import konfig.kotlin.Libs

with(pluginManager) {
  fun KotlinProjectExtension.addMainBom() {
    sourceSets.all {
      dependencies {
        implementation(project.dependencies.platform(Libs.koinBom))
        implementation(project.dependencies.platform(Libs.koinAnnotationsBom))
      }
    }
  }
  withPlugin("org.jetbrains.kotlin.multiplatform") {
    project.the<KotlinMultiplatformExtension>().addMainBom()
  }
  withPlugin("org.jetbrains.kotlin.jvm") {
    project.the<KotlinJvmProjectExtension>().addMainBom()
  }
  withPlugin("org.jetbrains.kotlin.android") {
    project.the<KotlinAndroidProjectExtension>().addMainBom()
  }
}
