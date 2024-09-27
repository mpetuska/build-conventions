plugins {
  `maven-publish`
}

publishing {
  repositories {
    maven("https://maven.pkg.github.com/mpetuska/${rootProject.name.lowercase()}") {
      name = "GitHub"
      credentials {
        username = providers.systemProperty("repository.github.username")
          .orElse(providers.gradleProperty("repository.github.username"))
          .orElse(providers.environmentVariable("REPOSITORY_GITHUB_USERNAME"))
          .orNull
        password = providers.systemProperty("repository.github.password")
          .orElse(providers.gradleProperty("repository.github.password"))
          .orElse(providers.environmentVariable("REPOSITORY_GITHUB_PASSWORD"))
          .orNull
      }
    }
  }
}
