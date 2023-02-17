package algorithms

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.PrivateMethodTester

class HillCypherTest
    extends AnyWordSpec
    with Matchers
    with PrivateMethodTester {
  "encrypt pair" should {
    val encryptVec = PrivateMethod[HillCypher](Symbol("encryptVec"))

    "encrypt List('h', 'e')" in {
      (HillCypher() invokePrivate encryptVec(
        List('h', 'e')
      )) shouldBe List('K', '.')
    }

    "only one char remains" in {
      (HillCypher() invokePrivate encryptVec(
        List('a')
      )) shouldBe List('H')
    }

    "with special characters" in {
      (HillCypher() invokePrivate encryptVec(
        List(0, 1).map(_.toChar)
      )) shouldBe List(0, 1).map(_.toChar)
    }
  }

  "encrypt string" should {
    val h = HillCypher()

    "encrypt hello" in {
      h.encryptString("They don't know;") shouldBe "wt~-.W{isVCz{lY8"
    }
  }

  "encrypt file" should {
    val h = HillCypher()

    /*
     * Encrypt a file, and save the results
     * */
    "Simple file" in {
      h.encryptFile(
        "src/test/scala/txt.txt",
        "src/test/scala/enc_txt.txt"
      )

      val source = scala.io.Source
        .fromFile("src/test/scala/enc_txt.txt")

      val lines =
        try source.mkString
        finally source.close()

      lines shouldBe "wt~-.W{isVCz{lY8;\nwt~-.W{isVCz{lY8"
    }

    // Encrypting 4459 lines of txt takes 131 milliseconds
    "Hamlet" in {
      val time = System.currentTimeMillis()

      h.encryptFile(
        "src/test/scala/hamlet.txt",
        "src/test/scala/enc_hamlet.txt"
      )

      println(System.currentTimeMillis() - time)

      h.decrypt(
        "src/test/scala/enc_hamlet.txt",
        "src/test/scala/dec_hamlet.txt"
      )
    }

  }
}
