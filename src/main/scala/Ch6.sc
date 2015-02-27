object Ch6 {
	import random._
	val rnd = RNG.simple(1)                   
	RNG.positiveInt(rnd)._1                   
	RNG.double(rnd)._1                        
	RNG.double3(rnd)._1                       
                                                  
   
  RNG.ints(10)(rnd)                               
                                                  
                                                  
  RNG.positiveMax(4)(rnd)                         
  RNG.withMap.double(rnd)                         
                                                  
  
  RNG.withMap2.intDouble(rnd)                     
                                                  
  RNG.withMap2.doubleInt(rnd)                     
                                                  
  
 	import RNG.positiveInt
 	RNG.sequence(List(positiveInt,positiveInt,positiveInt))(rnd)
                                                  
                                                  
  RNG.withSequence.ints(10)(rnd)                  
                                                  
                                                  
  RNG.withFlatMap.positiveInt(rnd)                
	RNG.withFlatMap.map2(positiveInt, positiveInt)(_ - _)(rnd)
                                                  
  import RNG._
  
   val gen = for {
   	x <- positiveMax(4)
   	y <- int
   	xs <- ints(x)
   	} yield xs.map{_ % y}                     
   	
		gen(rnd)                          
                                                  
  import data.State._
  import data.State
  
  val x = get[Int]                                
  val f: Int => Int = x => 2*x                    
  modify(f)(3)                                    
  
  import data.stateExample._
  import data.List._
  import data.List
  val machineState = simulateMachine(List[Input](Coin,Coin,Turn,Coin,Turn,Turn))
                                                  
                                                  
  val machine = Machine(true,5,0)                 
  machineState(machine)                           
}