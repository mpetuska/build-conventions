import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl

plugins {
  id("kmp-android")
}

// https://kotlinlang.org/docs/native-target-support.html#tier-1
@OptIn(ExperimentalWasmDsl::class)
kotlin {
  jvm()
  js(IR) {
    browser()
    nodejs()
  }
//  wasmJs {
//    browser()
//  }
//  wasmWasi {
//    nodejs()
//  }
  macosX64()
  macosArm64()
  iosSimulatorArm64()
  iosX64()
}
