package org.ua.diophantine

/**
 * @author yaroslav.gryniuk
 */
trait DiophantineEquationsParser[A] {
  def parse(representation: A): SystemOfDiophantineEquations
}
