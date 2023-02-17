package math

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import Congruence._

class CongruenceTest extends AnyFlatSpec with Matchers {
  behavior of "Solve congruences"

  it should "return 4" in {
    solve(2, 3, 5) shouldBe Some(4)
  }

  it should "return None" in {
    solve(3, 4, 9) shouldBe None
  }

  behavior of "get inverse"

  it should "return 3" in {
    inverse(9, 26) shouldBe Some(3)
  }

  it should "return 21" in {
    inverse(5, 26) shouldBe Some(21)
  }

  it should "return None" in {
    inverse(51, 324) shouldBe None
  }
}
