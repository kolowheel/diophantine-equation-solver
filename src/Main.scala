
object Main {

  def main(args: Array[String]) = {
    val systemOfEquations = """
                              |2x1 - 3x2 + x3 = 0
                              |5x1 + 2x2 - 4x3 = 0
                            """.stripMargin
    flow(AlgorithmImpl, DefaultDiophantineEquationsParser, systemOfEquations) match {
      case List(List(10, 13, 19)) =>
        println("Working good")
        println("Result is (x1=%d ; x2=%d ; x3=%d)".format(10, 13, 19))
    }
  }

  def flow[A](a: Algorithm, p: DiophantineEquationsParser[A], input: A) = {
    a(p.parse(input))
  }
}


