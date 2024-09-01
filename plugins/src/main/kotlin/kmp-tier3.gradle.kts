plugins {
  id("kmp-tier2")
}

// https://kotlinlang.org/docs/native-target-support.html#tier-3
kotlin {
  androidNativeArm32()
  androidNativeArm64()
  androidNativeX86()
  androidNativeX64()
  mingwX64()
  watchosDeviceArm64()
}
