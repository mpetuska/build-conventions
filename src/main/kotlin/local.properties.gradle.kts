import java.util.*

run {
  val loadLocalProperties = { file: File ->
    file.takeIf(File::exists)?.bufferedReader()?.use {
      Properties().apply { load(it) }
    }?.forEach { (key, value) -> project.ext["$key"] = value }
  }
  rootDir.resolve("local.properties").let(loadLocalProperties)
  projectDir.resolve("local.properties").let(loadLocalProperties)
}
