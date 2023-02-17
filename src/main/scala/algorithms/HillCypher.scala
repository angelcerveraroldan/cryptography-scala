package algorithms

import math.Matrix

import java.io.{File, FileWriter}
import scala.io.Source
import scala.util.Using
import scala.language.postfixOps

case class HillCypher(providedKey: Option[Matrix] = None)
    extends EncryptionAlgo {
  val defaultKey: Matrix = Matrix(2, 2, List(List(3, 3), List(2, 5)))
  val key = providedKey.getOrElse(defaultKey)

  def encryptString(s: String): String = {
    s
      .grouped(2)
      .flatMap(chars => encryptVec(chars.toList))
      .foldRight("")(_ + _)
  }

  def encryptFile(path: String, to: String): Unit = {
    val fileWriter = new FileWriter(new File(to))
    Using(Source.fromFile(path)) { reader =>
      reader
        .grouped(2)
        .foreach(chars => {
          fileWriter.write(encryptVec(chars.toList).toArray)
        })

      fileWriter.close()
    }
  }

  def decrypt(path: String, to: String): Unit =
    HillCypher(
      Some(
        Matrix(
          2,
          2,
          List(
            List(85, 63),
            List(42, 32)
          )
        )
      )
    ).encryptFile(path, to)

  /*
   * If the pair has a special character such as newline or break-line
   * then we will not encrypt it
   * */
  private def encryptVec(vec: List[Char]): List[Char] = vec match {
    case list if list.exists(_.toInt < 32) => list
    case List(x) =>
      (key * List(' ', x).map(_.toInt - 32))
        .map(encryptedAsciiVal =>
          (Math.floorMod(encryptedAsciiVal, (95)) + 32).toChar
        )
        .tail
    case _ =>
      (key * vec.map(_.toInt - 32))
        .map(encryptedAsciiVal =>
          (Math.floorMod(encryptedAsciiVal, (95)) + 32).toChar
        )
  }
}
