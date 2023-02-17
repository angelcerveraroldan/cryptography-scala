package math

import org.scalatest.flatspec._
import org.scalatest.matchers.should._

class MatrixTest extends AnyFlatSpec with Matchers {

  val matrix1 = Matrix(2, 2, List(List(1, 2), List(3, 0)))
  val matrix2 = Matrix(2, 2, List(List(1, 1), List(2, 4)))
  val matrix3 = Matrix(2, 2, List(List(2, 5), List(12, 15)))

  behavior of "Matrix x Scalar => Matrix"

  it should "Matrix times scalar" in {
    (matrix1 * 2) shouldBe Matrix(
      2,
      2,
      rows = List(
        List(2, 4),
        List(6, 0)
      )
    )
  }

  it should "Matrix plus scalar" in {
    (matrix1 + 2) shouldBe Matrix(
      2,
      2,
      List(
        List(3, 4),
        List(5, 2)
      )
    )
  }

  it should "Matrix mod 4" in {
    (matrix2 % 4) shouldBe Matrix(
      2,
      2,
      List(
        List(1, 1),
        List(2, 0)
      )
    )
  }

  it should "Negative in matrix" in {
    (Matrix(2, 2, List(List(1, 8), List(-8, 2))) % 4) shouldBe Matrix(
      2,
      2,
      List(
        List(1, 0),
        List(0, 2)
      )
    )
  }

  behavior of "Matrix x Matrix => Matrix"

  it should "add matrices" in {
    (matrix1 + matrix2) shouldBe Matrix(2, 2, List(List(2, 3), List(5, 4)))
  }

  behavior of "Matrix x Vector => Vector"

  it should "vector product" in {
    matrix3 * List(32, 12) shouldBe List(124, 564)
  }
}
