object Ch6 {
	import random._
	val rnd = RNG.simple(1)                   //> rnd  : random.RNG = random.RNG$$anon$1@400ae511
	RNG.positiveInt(rnd)._1                   //> res0: Int = 384748
	RNG.double(rnd)._1                        //> res1: Double = 1.79162249052507E-4
	RNG.double3(rnd)._1                       //> res2: (Double, Double, Double) = (1.79162249052507E-4,0.5360936464444239,0.2
                                                  //| 558267895392267)
   
  RNG.ints(10)(rnd)                               //> res3: (List[Int], random.RNG) = (List(384748, -1151252339, -549383847, 16129
                                                  //| 66641, -883454042, 1563994289, 1331515492, -234691648, 672332705, -203912839
                                                  //| 0),random.RNG$$anon$1@c2909a1)
  RNG.positiveMax(4)(rnd)                         //> res4: (Int, random.RNG) = (3,random.RNG$$anon$1@1a4788f3)
  RNG.withMap.double(rnd)                         //> res5: (Double, random.RNG) = (1.79162249052507E-4,random.RNG$$anon$1@4ee6da7
                                                  //| b)
  
  RNG.withMap2.intDouble(rnd)                     //> res6: ((Int, Double), random.RNG) = ((384748,0.5360936464444239),random.RNG$
                                                  //| $anon$1@5a08145f)
  RNG.withMap2.doubleInt(rnd)                     //> res7: ((Double, Int), random.RNG) = ((0.5360936464444239,384748),random.RNG$
                                                  //| $anon$1@16352be6)
  
 	import random.RNG._
 	sequence(List(positiveInt(_),positiveInt(_),positiveInt(_)))(rnd)
                                                  //> res8: (List[Int], random.RNG) = (List(549383847, 1151252339, 384748),random.
                                                  //| RNG$$anon$1@675dd521)
  withSequence.ints(10)(rnd)                      //> res9: (List[Int], random.RNG) = (List(-2039128390, 672332705, -234691648, 13
                                                  //| 31515492, 1563994289, -883454042, 1612966641, -549383847, -1151252339, 38474
                                                  //| 8),random.RNG$$anon$1@5763d118)
}