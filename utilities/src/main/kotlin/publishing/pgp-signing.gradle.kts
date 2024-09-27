plugins {
  publishing
  signing
}

signing {
  val signingKey = providers.systemProperty("signing.pgp.key")
    .orElse(providers.gradleProperty("signing.pgp.key"))
    .orElse(providers.environmentVariable("SIGNING_PGP_KEY"))
    .orNull
  val signingPassword = providers.systemProperty("signing.pgp.password")
    .orElse(providers.gradleProperty("signing.pgp.password"))
    .orElse(providers.environmentVariable("SIGNING_PGP_PASSWORD"))
    .orNull
  if (signingKey != null && signingPassword != null) {
    useInMemoryPgpKeys(signingKey, signingPassword)
    sign(publishing.publications)
  }
}
