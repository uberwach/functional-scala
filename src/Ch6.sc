object Ch6 {
	import random._
	val rnd = RNG.simple(1)                   //> rnd  : random.RNG = random.RNG$$anon$1@1b2c4d8f
	RNG.positiveInt(rnd)._1                   //> res0: Int = 384748
	RNG.double(rnd)._1                        //> res1: Double = 1.79162249052507E-4
	RNG.double3(rnd)._1                       //> res2: (Double, Double, Double) = (1.79162249052507E-4,0.5360936464444239,0.2
                                                  //| 558267895392267)
   
  RNG.ints(10)(rnd)                               //> res3: (List[Int], random.RNG) = (List(384748, -1151252339, -549383847, 16129
                                                  //| 66641, -883454042, 1563994289, 1331515492, -234691648, 672332705, -203912839
                                                  //| 0),random.RNG$$anon$1@7fae4426)
  RNG.positiveMax(4)(rnd)                         //> res4: (Int, random.RNG) = (3,random.RNG$$anon$1@5575b132)
}