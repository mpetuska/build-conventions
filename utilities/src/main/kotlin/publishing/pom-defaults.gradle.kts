plugins {
  id("io.deepmedia.tools.deployer")
}

deployer {
  projectInfo {
    url = "https://github.com/mpetuska/${rootProject.name.lowercase()}"
    description = provider { project.description }
    license {
      name = "Unlicense"
      url = "https://unlicense.org"
    }
    developer {
      name = "Martynas Petuška"
      email = "martynas@petuska.dev"
    }
    scm {
      fromGithub("mpetuska", rootProject.name.lowercase())
    }
  }
}
