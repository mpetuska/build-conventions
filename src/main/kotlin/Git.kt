import java.nio.charset.Charset

object Git {
  val headCommitHash by lazy {
    val child = Runtime.getRuntime().exec(arrayOf("git", "rev-parse", "--verify", "HEAD"))
    child.waitFor()
    child.inputStream.readAllBytes().toString(Charset.defaultCharset()).trim()
  }
}
