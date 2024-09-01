plugins {
  kotlin("jvm")
  kotlin("plugin.serialization")
}

kotlin {
  jvmToolchain(21)
  explicitApi()
  sourceSets {
    test {
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
