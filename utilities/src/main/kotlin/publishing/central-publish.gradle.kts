plugins {
  id("io.deepmedia.tools.deployer")
  id("pgp-signing")
}

deployer {
  centralPortalSpec {
    allowMavenCentralSync = false
    auth {
      user = secret("REPOSITORY_CENTRAL_USERNAME")
      password = secret("REPOSITORY_CENTRAL_PASSWORD")
    }
  }
}
