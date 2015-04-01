package org.ua.diophantine.impl

import org.scalatest.FlatSpec
import org.ua.diophantine.SystemOfDiophantineEquations

/**
 * @author yaroslav.gryniuk
 */
class DefaultDiophantineEquationsParserTest extends FlatSpec {
  "System" should "be parsed properly" in {
    val systemOfEquations = """
                              |2x1 - 3x2 + x3 = 0
                              |5x1 + 2x2 - 4x3 = 0
                            """.stripMargin
    assert(DefaultDiophantineEquationsParser.parse(systemOfEquations) == SystemOfDiophantineEquations(List(Map(1 -> 2, 2 -> -3, 3 -> 1), Map(1 -> 5, 2 -> 2, 3 -> -4))))
  }
}
