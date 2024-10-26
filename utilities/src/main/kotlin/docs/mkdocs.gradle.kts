plugins {
  id("ru.vyarus.mkdocs")
}

mkdocs {
  strict = false
  sourcesDir = layout.buildDirectory.dir("mkdocsSrc/$name").get().asFile.absolutePath
}

tasks {
  val mkdocsMergeConfig by registering {
    inputs.dir(layout.projectDirectory.dir("src/config/"))
    val outputFile = layout.buildDirectory.file("$name/mkdocs.yml")
    outputs.file(outputFile)
    doLast {
      val output = outputFile.get().asFile
      inputs.files.forEach {
        output.appendText(it.readText())
      }
    }
  }
  val mkdocsAssemble by registering(Sync::class) {
    destinationDir = File(mkdocs.sourcesDir)
    into("docs") {
      from(layout.projectDirectory.dir("src/docs/"))
      from(rootProject.layout.projectDirectory.file("CHANGELOG.md"))
      from(rootProject.layout.projectDirectory.file("README.md")) {
        rename("README.md", "index.md")
      }
      from(rootProject.layout.projectDirectory.file("LICENSE")) {
        rename("LICENSE", "LICENSE.md")
      }
      into(".github") {
        from(rootProject.layout.projectDirectory.file(".github/CONTRIBUTING.md"))
      }
    }
    into("theme") {
      from(layout.projectDirectory.dir("src/theme/"))
    }
    from(mkdocsMergeConfig)
  }
  mkdocsBuild {
    dependsOn(mkdocsAssemble)
  }
  mkdocsServe {
    dependsOn(mkdocsBuild)
  }
}
