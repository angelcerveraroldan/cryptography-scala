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
  val inverseOfKey = key
    .invert2x2(Some(95))
    .getOrElse(throw new Exception("Please use a key with an inverse mod 95"))

  def encrypt(toEncrypt: String, encryptionType: Encrypt): Unit =
    println("running hill cypher")

  def encryptString(s: String): String = {
    s.grouped(2)
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
    }

    fileWriter.close()
  }

  def decrypt(path: String, to: String): Unit =
    HillCypher(providedKey = Some(inverseOfKey)).encryptFile(path, to)

  /*
   * If the pair has a special character such as newline or break-line
   * then we will not encrypt it
   * */
  private def encryptVec(vec: List[Char]): List[Char] = vec match {
    // If the pair of chars has a character that we cannot encrypt, we will just leave those two characters un-encrypted
    case list if list.exists(_.toInt < 32) => list
    // If the list only contains one element, we will add a space at the beggining
    case List(x) =>
      (key * List(' ', x).map(_.toInt - 32))
        .map(encryptedAsciiVal =>
          (Math.floorMod(encryptedAsciiVal, (95)) + 32).toChar
        )
        .tail
    case l =>
      /*
       * This will happen at max one time, so the tail works well. If we change the algorithm to group string into n
       * chars, then this should change, but for that we also need to add a lot of features to the Matrix
       * */

      if (l.length == 1) encryptVec(' ' +: vec).tail
      else
        (key * vec.map(_.toInt - 32))
          .map(encryptedAsciiVal =>
            (Math.floorMod(encryptedAsciiVal, (95)) + 32).toChar
          )
  }
}
