This solver for system of diophantine equation.

Before using this solver ,user should prepare his system of equation.

Example:
```
2x1 - 3x2 + x3 = 0
5x1 + 2x2 - 4x3 = 0
```

should be converted to :
```
DiophantineNode(List(2, 5), List(1, 0, 0))
DiophantineNode(List(-3, 2), List(0, 1, 0))
DiophantineNode(List(1, -4), List(0, 0, 1))
```
where second lists are init coefficients.
So for ```(1,0,0)``` equation system is
```
2*1 + 3*0 + 1*0 => 2 
5*1 + 3*0 + 1*0 => 5
```

