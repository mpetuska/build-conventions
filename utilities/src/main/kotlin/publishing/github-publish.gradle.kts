plugins {
  id("io.deepmedia.tools.deployer")
}

deployer {
  githubSpec {
    owner = "mpetuska"
    repository = rootProject.name.lowercase()
    auth {
      user = secret("REPOSITORY_GITHUB_USERNAME")
      token = secret("REPOSITORY_GITHUB_PASSWORD")
    }
  }
}
