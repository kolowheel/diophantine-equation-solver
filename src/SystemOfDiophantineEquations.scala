/**
 * @author yaroslav.gryniuk
 * @param coefficients key is index, value is value
 */
case class SystemOfDiophantineEquations(coefficients: List[Map[Int, Int]]) {
  def apply(roots: List[Int]) = {
    coefficients.map {
      equation =>
        roots.zipWithIndex.foldLeft(0) {
          case (res, (root, index)) =>
            res + (equation(index + 1) * root)
        }
    }
  }
}
