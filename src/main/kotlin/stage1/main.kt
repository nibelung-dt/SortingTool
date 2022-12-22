package stage1

import java.lang.Integer.parseInt
import java.util.Scanner

fun main() {
    // 1 -2   33 4
    /*
    val scanner = Scanner(System.`in`)
    val str = scanner.nextLine()  //.hasNext()  // nextLine()
    println(str)
*/

   // val str = "1 -2   33 4"
    val tokens = mutableListOf<String>()
    val scanner = Scanner(System.`in`)
    while (scanner.hasNextLine()) {
        val lineScanner = Scanner(scanner.nextLine())
        while (lineScanner.hasNext()) {
            tokens.add(lineScanner.next())
            }
        lineScanner.close()
        }
    scanner.close()

    // val list = str.split("\\s+".toRegex()) // .sorted()
    val list2 = mutableListOf<Int>()
    for (i in tokens) {
        list2.add(parseInt(i))
    }
    val list3 = list2.sortedDescending()
    var max = list3[0]
    var n = 0
    for (i in list3) {
        if (i == max) {
            n++
        }
    }

    println("Total numbers: ${ list3.size }")
    println("The greatest number: $max ($n time(s)).")
}

/*
val numbers = IntArray(5) { readln().toInt() } // on each line single numbers from 1 to 5
println(numbers.joinToString()) // 1, 2, 3, 4, 5

// here we have an input string "1 2 3 4 5"
val numbers = readln().split(" ").map { it.toInt() }.toTypedArray()
println(numbers.joinToString()) // 1, 2, 3, 4, 5

val regex = "\\s+".toRegex()
val str = "1 2\t\t3  4\t5  6"
val nums = str.split(regex).map { it.toInt() }.toTypedArray()
println(nums.joinToString()) // 1, 2, 3, 4, 5, 6

println(numbers1.contentEquals(numbers2)) // true сравниваем массивы

 */


/*
  val scanner = Scanner(System.`in`)
    val nums = mutableListOf(scanner.nextInt())
    var max = nums.last()
    while (scanner.hasNext()) {
        nums.add(scanner.nextInt())
        max = maxOf(max, nums.last())
    }
 */