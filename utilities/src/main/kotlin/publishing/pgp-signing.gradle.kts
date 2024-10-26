plugins {
  id("io.deepmedia.tools.deployer")
}

deployer {
  signing {
    key = secret("SIGNING_PGP_KEY")
    password = secret("SIGNING_PGP_PASSWORD")
  }
}
