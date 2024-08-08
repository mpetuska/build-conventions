import kmp.blockingTargets

plugins {
  id("kmp")
  id("kmp-android")
}

// https://kotlinlang.org/docs/native-target-support.html#tier-3
kotlin {
  blockingTargets()
}
