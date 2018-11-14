import scala.io.StdIn.{readLine, readInt}
import scala.math._
import scala.collection.mutable.ArrayBuffer
import java.io.PrintWriter
import scala.io.Source

object ScalaTutotial{
	def main(args: Array[String]){

		/* for loop */ 
		val randLetters = "SASFHNASLSFNCEJ Q2E;2BNEOPNDOQW;FN Q;LFNQLFNQLWFJNQWLA";
		// to ~ [a,b] , until ~ [a,b)  
		for(i <- 0 until randLetters.length)
			print(randLetters(i)+" ")
		println()
		
		/*for yield*/
		var eventList = for{ i <- 1 to 100 if i%5 == 0; if(i%2 !=0) }yield i
		for(i <- eventList){
			print(i+" ")
		}
		
		/*  : _*  */
		def sum(args: Int*) = {
			var result = 0
			for (arg <- args) result += arg
			result
		}
		println(sum(1 to 5: _*)) //将 1 to 5转化为参数序列, 1 to 5 ---> 1, 2, 3, 4, 5
		
		/*double loop*/
		// i=0 then j=100,101,102,103...  i=1 then j=100,101,102....
		for(i <- 0 to 10; j <- 100 to 110)
			println("i="+i+",j="+j)
		
		
		/*scala has no break or continue stuff...*/
		// just use functions instead of loops..
		def printPrimes(){
			val primeList = List(1,2,3,5,7,11)
			for(i <- primeList){
				if(i == 11){
					return
				}
				if(i != 1){
					print(i+" ")
				}
			}
		}
		printPrimes()
		println()
	
	
		/* input from cmdline*/
		println("input something")
		var someInput = readLine
		// readInt  readLine.toInt
		println("your input:"+someInput)
		
		/*print format*/
		val name = "Derek"
		val age = 12
		val weight = 59
		// s, f 实际上是一个函数，s用于字符串中插值，f用于格式化输出
		//还有raw，表示这是一个纯字符串，类似于python中的r
		println(s"Hello $name")
		println(f"I am ${age+1} and weigh $weight%.2f")
		printf("'%.5f'\n",3.1415)
		printf("'%-5d'\n",33)
		printf("'%5d'\n",33)
		
		/*function*/
		def getSum(num1 : Int =1, num2 : Int=2): Int = {
			return num1 + num2
		}
		def sayHey(): Unit = {
			println("Hey!")
		}
		def getSum(args : Int*): Int = {
			var sum: Int = 0
			for(num <- args)
				sum += num
			return sum
		}
	}

}
