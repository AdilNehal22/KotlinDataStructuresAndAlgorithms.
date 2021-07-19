package main.kotlin


fun euclideanAlgo(a: Long,b: Long):Long{
    var aPrime: Long = 0
    if (b==0L) {
        return  a
    }else{
        aPrime = a/b
        return euclideanAlgo(b,aPrime)
    }

}


fun main(){
  println(euclideanAlgo(30,18))
}