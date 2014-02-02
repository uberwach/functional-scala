object Ch6 {
	import random._
	val rnd = RNG.simple(1)                   //> rnd  : random.RNG = random.RNG$$anon$1@26b20a31
	RNG.positiveInt(rnd)._1                   //> res0: Int = 384748
	RNG.double(rnd)._1                        //> res1: Double = 1.79162249052507E-4
	RNG.double3(rnd)._1                       //> res2: (Double, Double, Double) = (1.79162249052507E-4,0.5360936464444239,0.2
                                                  //| 558267895392267)
   
  RNG.ints(10)(rnd)                               //> res3: (List[Int], random.RNG) = (List(384748, -1151252339, -549383847, 16129
                                                  //| 66641, -883454042, 1563994289, 1331515492, -234691648, 672332705, -203912839
                                                  //| 0),random.RNG$$anon$1@6fdbad5b)
  RNG.positiveMax(4)(rnd)                         //> res4: (Int, random.RNG) = (3,random.RNG$$anon$1@27feffac)
  RNG.withMap.double(rnd)                         //> res5: (Double, random.RNG) = (1.79162249052507E-4,random.RNG$$anon$1@498e2a4
                                                  //| 2)
  
  RNG.withMap2.intDouble(rnd)                     //> res6: ((Int, Double), random.RNG) = ((384748,0.5360936464444239),random.RNG$
                                                  //| $anon$1@37b28fb5)
  RNG.withMap2.doubleInt(rnd)                     //> res7: ((Double, Int), random.RNG) = ((0.5360936464444239,384748),random.RNG$
                                                  //| $anon$1@19877096)
  
 	import RNG.positiveInt
 	RNG.sequence(List(positiveInt,positiveInt,positiveInt))(rnd)
                                                  //> res8: (List[Int], random.RNG) = (List(549383847, 1151252339, 384748),random.
                                                  //| RNG$$anon$1@2b008d54)
  RNG.withSequence.ints(10)(rnd)                  //> res9: (List[Int], random.RNG) = (List(-2039128390, 672332705, -234691648, 13
                                                  //| 31515492, 1563994289, -883454042, 1612966641, -549383847, -1151252339, 38474
                                                  //| 8),random.RNG$$anon$1@2f4bcf99)
  RNG.withFlatMap.positiveInt(rnd)                //> res10: (Int, random.RNG) = (384748,random.RNG$$anon$1@4932f4f0)
	RNG.withFlatMap.map2(positiveInt, positiveInt)(_ - _)(rnd)
                                                  //> res11: (Int, random.RNG) = (-1150867591,random.RNG$$anon$1@d233caf)
  import RNG._
  
   val gen = for {
   	x <- positiveMax(4)
   	y <- int
   	xs <- ints(x)
   	} yield xs.map{_ % y}                     //> gen  : data.State[random.RNG,List[Int]] = State(<function1>)
   	
		gen(rnd)                          //> res12: (List[Int], random.RNG) = (List(-549383847, 461714302, -883454042),ra
                                                  //| ndom.RNG$$anon$1@3881bb1)
}