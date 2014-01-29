My solutions + playground for the exercises of the book "Functional Programming in Scala MEAP v10". 

## Summary of cool stuff
Forcing tailrecursion by compiler
```scala
 @annotation.tailrec
    def FibAcc(n : Int, a : Int, b: Int) : Int = 
```

Reversing a List by using a fold (exercise 12)
```scala
def reverse[A](as : List[A]) : List[A] = foldLeft(as,Nil : List[A])((b,a) => Cons(a,b))
```