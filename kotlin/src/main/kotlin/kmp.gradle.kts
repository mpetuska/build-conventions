plugins {
  kotlin("multiplatform")
  kotlin("plugin.serialization")
}

kotlin {
  jvmToolchain(21)
  explicitApi()
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
