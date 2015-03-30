/**
 * @author yaroslav.gryniuk
 */
trait Algorithm {
  def apply(system: SystemOfDiophantineEquations): List[List[Int]]
}
