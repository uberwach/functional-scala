object Ch6 {
	import random._
	val rnd = RNG.simple(1)                   //> rnd  : random.RNG = random.RNG$$anon$1@511bfc28
	RNG.positiveInt(rnd)._1                   //> res0: Int = 384748
	RNG.double(rnd)._1                        //> res1: Double = 1.79162249052507E-4
	RNG.double3(rnd)._1                       //> res2: (Double, Double, Double) = (1.79162249052507E-4,0.5360936464444239,0.2
                                                  //| 558267895392267)
   
  RNG.ints(10)(rnd)                               //> res3: (List[Int], random.RNG) = (List(384748, -1151252339, -549383847, 16129
                                                  //| 66641, -883454042, 1563994289, 1331515492, -234691648, 672332705, -203912839
                                                  //| 0),random.RNG$$anon$1@38ea5fcd)
  RNG.positiveMax(4)(rnd)                         //> res4: (Int, random.RNG) = (3,random.RNG$$anon$1@498654ae)
  RNG.withMap.double(rnd)                         //> res5: (Double, random.RNG) = (1.79162249052507E-4,random.RNG$$anon$1@38f4f22
                                                  //| 2)
  
  RNG.withMap2.intDouble(rnd)                     //> res6: ((Int, Double), random.RNG) = ((384748,0.5360936464444239),random.RNG$
                                                  //| $anon$1@206fcd32)
  RNG.withMap2.doubleInt(rnd)                     //> res7: ((Double, Int), random.RNG) = ((0.5360936464444239,384748),random.RNG$
                                                  //| $anon$1@11a7845f)
  
 	import RNG.positiveInt
 	RNG.sequence(List(positiveInt,positiveInt,positiveInt))(rnd)
                                                  //> res8: (List[Int], random.RNG) = (List(549383847, 1151252339, 384748),random.
                                                  //| RNG$$anon$1@4afaddb2)
  RNG.withSequence.ints(10)(rnd)                  //> res9: (List[Int], random.RNG) = (List(-2039128390, 672332705, -234691648, 13
                                                  //| 31515492, 1563994289, -883454042, 1612966641, -549383847, -1151252339, 38474
                                                  //| 8),random.RNG$$anon$1@46e337b2)
  RNG.withFlatMap.positiveInt(rnd)                //> res10: (Int, random.RNG) = (384748,random.RNG$$anon$1@4f124609)
	RNG.withFlatMap.map2(positiveInt, positiveInt)(_ - _)(rnd)
                                                  //> res11: (Int, random.RNG) = (-1150867591,random.RNG$$anon$1@35f5e42b)
  import RNG._
  
   val gen = for {
   	x <- positiveMax(4)
   	y <- int
   	xs <- ints(x)
   	} yield xs.map{_ % y}                     //> gen  : data.State[random.RNG,List[Int]] = State(<function1>)
   	
		gen(rnd)                          //> res12: (List[Int], random.RNG) = (List(-549383847, 461714302, -883454042),ra
                                                  //| ndom.RNG$$anon$1@12d04843)
  import data.State._
  import data.State
  
  val x = get[Int]                                //> x  : data.State[Int,Int] = State(<function1>)
  val f: Int => Int = x => 2*x                    //> f  : Int => Int = <function1>
  modify(f)(3)                                    //> res13: (Unit, Int) = ((),6)
  
  import data.stateExample._
  import data.List._
  import data.List
  val machineState = simulateMachine(List[Input](Coin,Coin,Turn,Coin,Turn,Turn))
                                                  //> machineState  : data.State[data.stateExample.Machine,Int] = State(<function1
                                                  //| >)
  val machine = Machine(true,5,0)                 //> machine  : data.stateExample.Machine = Machine(true,5,0)
  machineState(machine)                           //> res14: (Int, data.stateExample.Machine) = (2,Machine(true,3,2))
}