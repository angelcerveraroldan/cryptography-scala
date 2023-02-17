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
  def generate(): (Matrix, Matrix) = ???

  // Generate a matrix with an inversse mod an int
  def generate(mod: Int): (Matrix, Matrix) = ???
}
