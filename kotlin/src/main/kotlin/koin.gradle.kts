import konfig.kotlin.Libs

plugins {
  id("ksp")
  id("koin-bom")
}

ksp {
  arg("KOIN_CONFIG_CHECK", "true")
  arg("KOIN_DEFAULT_MODULE", "false")
  arg("KOIN_USE_COMPOSE_VIEWMODEL", "true")
}

dependencies {
  configurations.all {
    if (name.startsWith("ksp")) {
      add(name, platform(Libs.koinAnnotationsBom))
      add(name, Libs.koinCompiler)
    }
  }
}
