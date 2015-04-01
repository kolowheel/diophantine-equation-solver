package org.ua.diophantine.impl

import org.scalatest._
import org.ua.diophantine.SystemOfDiophantineEquations

/**
 * @author yaroslav.gryniuk
 */
class AlgorithmImplTest extends FlatSpec {
  "System" should "be solved properly" in {
    val system = SystemOfDiophantineEquations(List(Map(1 -> 2, 2 -> -3, 3 -> 1), Map(1 -> 5, 2 -> 2, 3 -> -4)))
    assert(AlgorithmImpl(system) === List(List(10, 13, 19)))
  }
}
