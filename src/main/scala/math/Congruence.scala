package math

object Congruence {
  /*
   * Find the smallest k such that
   * x⋅k ≡ y  (mod n)
   * */
  def solve(x: Int, y: Int, n: Int): Option[Int] =
    (0 to n).find(potentialAnswer => x * potentialAnswer % n == y)

  /*
   * Find the inverse of a number mod, this doesnt always exist, so we use an option#
   *
   * Example:
   * The inverse of 9 mod 26 is 3, this is because
   * 9⋅3 = 27 ≡ 1  (mod 26)
   * */
  def inverse(num: Int, mod: Int): Option[Int] = solve(num, 1, mod)
}
