plugins {
  publishing
}

publishing {
  publications {
    withType<MavenPublication> {
      pom {
        name = project.name
        url = "https://github.com/mpetuska/${rootProject.name.lowercase()}"
        description = provider { project.description }

        licenses {
          license {
            name = "Unlicense"
            url = "https://unlicense.org"
          }
        }

        developers {
          developer {
            id = "mpetuska"
            name = "Martynas Petuška"
            email = "martynas@petuska.dev"
          }
        }

        scm {
          connection = "scm:git:git://github.com/mpetuska/${rootProject.name.lowercase()}.git"
          developerConnection = "scm:git:git@github.com:mpetuska/${rootProject.name.lowercase()}.git"
          url = "https://github.com/mpetuska/${rootProject.name.lowercase()}"
          tag = provider { Git.headCommitHash }
        }
      }
    }
  }
}
