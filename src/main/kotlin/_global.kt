@file:Suppress("EmptyKtFile")

import org.gradle.api.provider.Provider
import org.gradle.api.provider.ProviderFactory

/**
 * Chains a property lookup in the following order:
 * 1. Environment variable (uppercased and `.` replaced with `_`)
 * 2. System property
 * 3. Gradle property
 */
fun ProviderFactory.flowProperty(
  key: String,
  default: String? = null,
): Provider<String> {
  val prop = environmentVariable(key.uppercase().replace(".", "_"))
    .orElse(systemProperty(key))
    .orElse(gradleProperty(key))
  return if (default != null) {
    prop.orElse(default)
  } else {
    prop
  }
}
