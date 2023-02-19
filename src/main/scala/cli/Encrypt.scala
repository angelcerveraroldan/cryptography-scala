package cli

import algorithms.HillCypher
import org.backuity.clist._

class EncryptionType();

case class EncryptString() extends EncryptionType

case class EncryptFile() extends EncryptionType

class Encrypt() extends Command(description = "Encrypt a file or a string") {
  var algo = arg[String](description = """
      |Algorithm
      |- HillCypher (hc)
      |""".stripMargin)

  var string = opt[Option[String]](description = "String to encrypt")

  var inPath =
    opt[Option[String]](description = "Path of file to encrypt")

  var outPath =
    opt[Option[String]](
      description = """
          |Where to save encrypted file / string
          |If none is given, a file will be created with the same name as the inPath, but with a .enc at the end
          |""".stripMargin
    )

  var (toEncrypt: String, encryptionType: EncryptionType) =
    (string, inPath) match {
      case (Some(string), None) => (string, EncryptString)
      case (None, Some(path))   => (path, EncryptFile)
      case (Some(_), Some(_))   => ???
      case (None, None)         => ???
    }

  def run(): Unit = algo.toLowerCase match {
    case "hc" | "hill cypher" => HillCypher()
  }

}
