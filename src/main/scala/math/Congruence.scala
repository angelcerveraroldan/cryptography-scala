package math

object Congruence {
  /*
   * Find the smallest k such that
   * x⋅k ≡ y  (mod n)
   *
   * TODO: Optimize
   * */
  def solve(x: Int, y: Int, n: Int): Option[Int] =
    (0 to n).find { num =>
      x * num % n == y
    }

  def inverse(num: Int, mod: Int): Option[Int] = solve(num, 1, mod)
}
