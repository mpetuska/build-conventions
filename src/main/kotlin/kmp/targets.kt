package kmp

import org.gradle.api.Action
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinAndroidTarget
import org.jetbrains.kotlin.gradle.targets.jvm.KotlinJvmTarget
import org.jetbrains.kotlin.konan.target.KonanTarget

private val blockingKonanTargets: Set<KonanTarget> = setOf(
  KonanTarget.ANDROID_ARM32,
  KonanTarget.ANDROID_ARM64,
  KonanTarget.ANDROID_X64,
  KonanTarget.ANDROID_X86,
  KonanTarget.IOS_ARM64,
  KonanTarget.IOS_SIMULATOR_ARM64,
  KonanTarget.IOS_X64,
  KonanTarget.LINUX_ARM64,
  KonanTarget.LINUX_X64,
  KonanTarget.MACOS_ARM64,
  KonanTarget.MACOS_X64,
  KonanTarget.MINGW_X64,
  KonanTarget.TVOS_ARM64,
  KonanTarget.TVOS_SIMULATOR_ARM64,
  KonanTarget.TVOS_X64,
  KonanTarget.WATCHOS_ARM32,
  KonanTarget.WATCHOS_ARM64,
  KonanTarget.WATCHOS_DEVICE_ARM64,
  KonanTarget.WATCHOS_SIMULATOR_ARM64,
  KonanTarget.WATCHOS_X64,
)

@Suppress("UnusedPrivateMember")
private fun KotlinTarget.isBlocking(): Boolean = (this is KonanTarget && this in blockingKonanTargets) ||
  this is KotlinJvmTarget ||
  this is KotlinAndroidTarget

/**
 * Register all targets with blocking coroutines capabilities.
 */
fun KotlinMultiplatformExtension.blockingTargets(config: Action<KotlinTarget> = Action {}) {
  setOf(
    jvm(),
    androidTarget(),
    androidNativeArm32(),
    androidNativeArm64(),
    androidNativeX64(),
    androidNativeX86(),
    iosArm64(),
    iosSimulatorArm64(),
    iosX64(),
    linuxArm64(),
    linuxX64(),
    macosArm64(),
    macosX64(),
    mingwX64(),
    tvosArm64(),
    tvosSimulatorArm64(),
    tvosX64(),
    watchosArm32(),
    watchosArm64(),
    watchosDeviceArm64(),
    watchosSimulatorArm64(),
    watchosX64(),
  ).onEach(config::execute)
}
