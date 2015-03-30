import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer

/**
 * @author yaroslav.gryniuk
 */
object AlgorithmImpl extends Algorithm {

  case class DiophantineNode(coefficients: List[Int], roots: List[Int]
                             , left: Option[DiophantineNode] = None, right: Option[DiophantineNode] = None) {
    def *(node: DiophantineNode) = {
      (coefficients.zip(node.coefficients) map {
        case (a, b) => a * b
      }).sum
    }

    def +(node: DiophantineNode) = {
      val zipAndAdd = (first: List[Int], second: List[Int]) => first.zip(second).map {
        case (a, b) => a + b
      }
      DiophantineNode(zipAndAdd(this.coefficients, node.coefficients), zipAndAdd(this.roots, node.roots), Some(this), Some(node))
    }

    override def equals(that: Any) = {
      that match {
        case node: DiophantineNode => node.coefficients == this.coefficients && node.roots == this.roots
        case _ => false
      }
    }
  }

  def calculate(nodes: List[DiophantineNode]) = {
    val newNodes = ListBuffer.empty[DiophantineNode]
    for (i <- 0 to nodes.length - 1) {
      for (j <- 0 to nodes.length - 1) {
        if ((nodes(j) * nodes(i)) < 0 && j != i) {
          val possibleNode = nodes(j) + nodes(i)
          if (!newNodes.contains(possibleNode)) {
            newNodes += possibleNode
          }
        }
      }
    }
    @tailrec
    def iteration(initNodes: List[DiophantineNode], currentNodes: List[DiophantineNode], allNodes: List[DiophantineNode]): DiophantineNode = {
      val newNodes = ListBuffer.empty[DiophantineNode]
      var resultNode: Option[DiophantineNode] = None
      currentNodes foreach {
        node =>
          var add = false
          initNodes foreach {
            initNode =>
              if (node * initNode < 0) {
                val possibleNode = node + initNode
                add = true
                if (!newNodes.contains(possibleNode) && !allNodes.contains(possibleNode)) {
                  newNodes += possibleNode
                }
              }
          }
          if (!add)
            resultNode = Some(node)
      }
      resultNode match {
        case Some(r) => r
        case None => iteration(initNodes, newNodes.toList, allNodes ++ newNodes)
      }
    }
    val newNodesImm = newNodes.toList
    iteration(nodes, newNodesImm, newNodesImm ++ nodes)
  }

  def apply(system: SystemOfDiophantineEquations): List[List[Int]] = {
    val countOfCoefficients: Int = system.coefficients.map {
      equation: Map[Int, Int] =>
        equation maxBy (_._1)
    }.max._1
    val init = ListBuffer.empty[ListBuffer[Int]]
    for (a <- 1 to countOfCoefficients) {
      val current = ListBuffer.fill(countOfCoefficients)(0)
      init += current
      current update(a - 1, 1)
    }
    val c: ListBuffer[DiophantineNode] = init map {
      coef =>
        val listCoef = coef.toList
        DiophantineNode(system(listCoef), listCoef)
    }
    List(calculate(c.toList).roots)
  }
}
