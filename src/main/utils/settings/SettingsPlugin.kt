package settings

import com.gradle.develocity.agent.gradle.DevelocityConfiguration
import com.gradle.develocity.agent.gradle.DevelocityPlugin
import org.danilopianini.gradle.git.hooks.GitHooksExtension
import org.danilopianini.gradle.git.hooks.GradleGitHooksPlugin
import org.gradle.api.Plugin
import org.gradle.api.initialization.Settings
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.the

@Suppress("unused")
class SettingsPlugin : Plugin<Settings> {
  override fun apply(target: Settings) {
    target.plugins.apply(DevelocityPlugin::class)
    target.plugins.apply(GradleGitHooksPlugin::class)

    target.dependencyResolutionManagement {
      @Suppress("UnstableApiUsage")
      repositories {
        google()
        mavenCentral()
      }
    }
    target.the<DevelocityConfiguration>().apply {
      buildScan {
        publishing.onlyIf { false }
        termsOfUseUrl.set("https://gradle.com/terms-of-service")
        termsOfUseAgree.set("yes")
      }
    }
    target.the<GitHooksExtension>().apply {
      commitMsg { conventionalCommits() }
      preCommit {
        tasks("detekt --auto-correct")
      }
      hook("pre-push") {
        tasks("detekt")
      }
      createHooks(overwriteExisting = true)
    }
  }
}
