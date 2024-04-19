plugins {
  id("org.jetbrains.kotlin.multiplatform")
  id("org.jetbrains.kotlin.plugin.serialization")
}

kotlin {
  jvmToolchain(21)
  sourceSets {
    commonTest {
      dependencies {
        implementation(kotlin("test"))
      }
    }
  }
}

tasks {
  withType<Test> {
    useJUnitPlatform()
  }
}
