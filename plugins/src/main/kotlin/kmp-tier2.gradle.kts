plugins {
  id("kmp-tier1")
}

// https://kotlinlang.org/docs/native-target-support.html#tier-2
kotlin {
  linuxX64()
  linuxArm64()
  watchosSimulatorArm64()
  watchosX64()
  watchosArm32()
  watchosArm64()
  tvosSimulatorArm64()
  tvosX64()
  tvosArm64()
  iosArm64()
}
