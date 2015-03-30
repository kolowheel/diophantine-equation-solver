
/**
 * @author yaroslav.gryniuk
 */
object DefaultDiophantineEquationsParser extends DiophantineEquationsParser[String] {
  def parse(representation: String): SystemOfDiophantineEquations = {
    val diophantine = representation.split(System.lineSeparator).map((x) => x.replace(" ", "")).filter {
      case str: String => str.trim.length != 0
    } map {
      equation: String =>
        equation.split("=")(0).replace("-", "+-").split("\\+").map {
          operand =>
            val temp = operand.split("x")
            if (temp(0).isEmpty)
              (Integer.parseInt(temp(1)), 1)
            else
              (Integer.parseInt(temp(1)), Integer.parseInt(temp(0)))
        }.toMap
    }
    SystemOfDiophantineEquations(diophantine.toList)
  }

}
