package cli

import algorithms.{Encrypt, EncryptFile, EncryptString, HillCypher}
import org.backuity.clist._

class HillCypherCommand()
    extends Command(
      name = "hc",
      description = """
        |Hill Cypher algorithm:
        |
        |Notes on how this algo works -  TODO
        |""".stripMargin
    ) {

  var string: Option[String] =
    opt[Option[String]](default = None, description = "String to encrypt")

  var inPath: Option[String] =
    opt[Option[String]](default = None, description = "Path of file to encrypt")

  var outPath: Option[String] =
    opt[Option[String]](
      default = None,
      description = """
          |Where to save encrypted file / string
          |
          |If none is provided, files will be saved under the same name, but ending in .enc, and strings will be printed
          |""".stripMargin
    )

  def run(): Unit = {
    if (!(string.isEmpty ^ inPath.isEmpty)) {
      println("Please enter either a path or a string")
      return
    }

    val (enc: String, encType: Encrypt) =
      (string, inPath) match {
        case (Some(s), None)    => (s, EncryptString)
        case (None, Some(path)) => (path, EncryptFile)
        case (None, None)       => throw new Exception("There has been an error")
        case (Some(s), Some(p)) =>
          throw new Exception("There has been an error")
      }

    encType match {
      case EncryptString => println(HillCypher().encryptString(enc))
      case EncryptFile =>
        HillCypher().encryptFile(
          enc,
          outPath.getOrElse(s"$enc.enc")
        )
      case _ =>
    }
  }
}
