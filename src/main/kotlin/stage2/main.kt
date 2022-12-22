package stage2

import java.lang.Integer.parseInt
// import java.util.Locale.filter
import java.util.Scanner

fun main(args: Array<String>) {

    when {
        args[0] == "-dataType" && args[1] == "long" -> long()
        args[0] == "-dataType" && args[1] == "line" -> line()
        args[0] == "-dataType" && args[1] == "word" -> word()
    }
}

fun long() {
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

fun line() {
    val tokens = mutableListOf<String>()
    val scanner = Scanner(System.`in`)
    // var index = 0
    var str = ""
    while (scanner.hasNextLine()) {
        val lineScanner = Scanner(scanner.nextLine())
        tokens.add(lineScanner.nextLine())
        while (lineScanner.hasNext()) {
            str += lineScanner.nextLine()

            // tokens.add(lineScanner.next())
        }
        println(str)
        tokens.add(str)
        str = ""
        println(tokens)
        lineScanner.close()
    }
    scanner.close()

    println(tokens)

    // val str = readln().split("\n").map { it }.toTypedArray()
    /*
    val list = mutableListOf<Int>()
    for (i in tokens) {
        list.add(i.length)
    } */

    //val longest = tokens.maxByOrNull { it.length }!!
    val longest =  tokens.sortedBy {it.length} .last()
    val n = (tokens.filter {it == longest  }).count()
    // val max = list.maxOf { it }
   // val n = (list.filter {it == max  }).count()
    val percentage = n.toDouble() / (tokens.size).toDouble() * 100

    println("Total lines: ${tokens.size}")
    println("The longest line: $longest")   //{list.maxOf { it }}")
    println("($n time(s), ${percentage.toInt()}%).")
}


fun word() {
    val words = mutableListOf<String>()
    val scanner = Scanner(System.`in`)
    while (scanner.hasNextLine()) {
        val lineScanner = Scanner(scanner.nextLine())
        while (lineScanner.hasNext()) {
            words.add(lineScanner.next())
        }
        lineScanner.close()
    }
    scanner.close()

    // val longest =  words.sortedBy {it.length} .last()
    val longest = words.maxByOrNull { it.length }!!
    val n = (words.filter {it == longest  }).count()
    val percentage = n / words.size * 100

    println("Total words: ${words.size}.")
    println("The longest word: $longest ($n time(s), $percentage%).")
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