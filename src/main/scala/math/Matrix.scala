package math

/*
 * Matrix
 * */

case class Matrix(rowCount: Int, colCount: Int, rows: List[List[Int]]) {
  /*
   * Functions in the form (Matrix x Int) -> Matrix
   * */

  def mapElements(f: Int => Int): Matrix =
    this.copy(rows = this.rows.map(_.map(elem => f(elem))))

  def *(x: Int): Matrix = mapElements(_ * x)

  def +(x: Int): Matrix = mapElements(_ + x)

  def %(x: Int): Matrix = mapElements(Math.floorMod(_, x))

  /*
   * Functions in the form (Matrix x Matrix) -> Matrix
   */

  def zipMap(rhs: Matrix, f: (Int, Int) => Int): Matrix =
    this.copy(rows = rows.zip(rhs.rows).map { case (rowLhs, rowRhs) =>
      rowLhs.zip(rowRhs).map { case (x, y) => f(x, y) }
    })

  def +(x: Matrix): Matrix = zipMap(x, _ + _)

  /*
   * Functions in the form (Matrix x Vector) -> Vector
   * */

  def *(vector: List[Int]): List[Int] =
    rows.map(row => row.zip(vector).map(x => x._1 * x._2).sum)

  // Generate a matrix with an inverse
  def generate2x2(mod: Int): (Matrix, Matrix) = ???

  // Invert the 2x2 matrix
  def invert2x2(mod: Option[Int]): Option[Matrix] = {
    val determinant = rows(0)(0) * rows(1)(1) - rows(0)(1) * rows(1)(0)

    val determinantsInverse: Int = mod match {
      case None =>
        throw new Exception(
          "Only modular matrices are supported for now, so only integers can be stored in them"
        )
      case Some(mod) =>
        Congruence
          .solve(determinant, 1, mod)
          .getOrElse(return None)
    }

    Some(
      (this.copy(
        rows = List(
          List(rows(1)(1), -rows(0)(1)),
          List(-rows(1)(0), rows(0)(0))
        )
      ) * determinantsInverse) % mod.get
    )
  }
}
