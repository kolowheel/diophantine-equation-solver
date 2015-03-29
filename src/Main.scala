import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer

object Main {

  case class DiophantineNode(coefficients: List[Int], roots: List[Int]
                             , left: Option[DiophantineNode] = None, right: Option[DiophantineNode] = None) {
    def *(node: DiophantineNode) = {
      (coefficients.zip(node.coefficients) map { case (a, b) => a * b}).sum
    }

    def +(node: DiophantineNode) = {
      val zipAndAdd = (first: List[Int], second: List[Int]) => first.zip(second).map { case (a, b) => a + b}
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


  def main(args: Array[String]) = {
    print(calculate(List(
      DiophantineNode(List(2, 5), List(1, 0, 0)),
      DiophantineNode(List(-3, 2), List(0, 1, 0)),
      DiophantineNode(List(1, -4), List(0, 0, 1)))
    ).roots)
  }
}
