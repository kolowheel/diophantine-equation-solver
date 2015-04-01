package org.ua.diophantine

import org.ua.diophantine.impl.{DefaultDiophantineEquationsParser, AlgorithmImpl}


object Main {

  def main(args: Array[String]) = {
    val systemOfEquations = """
                              |2x1 - 3x2 + x3 = 0
                              |5x1 + 2x2 - 4x3 = 0
                            """.stripMargin
    println(flow(AlgorithmImpl, DefaultDiophantineEquationsParser, systemOfEquations))


  }

  def flow[A](a: Algorithm, p: DiophantineEquationsParser[A], input: A) = {
    a(p.parse(input))
  }
}


