package stage3

import java.lang.Integer.parseInt
import java.util.Scanner

fun main(args: Array<String>) {
    when {
        args[0] == "-dataType" && args[1] == "long" && args.size == 2 -> long(false)
        args[0] == "-dataType" && args[1] == "line" && args.size == 2 -> line()
        args[0] == "-dataType" && args[1] == "word" && args.size == 2 -> word()
        args[0] == "-sortIntegers" -> long(true)
        else -> long(true)
    }
}

fun long(sort: Boolean) {
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
    if (sort == false) {
        println("The greatest number: $max ($n time(s)).")
    } else {
        println("Sorted data: ${list2.sorted().joinToString(" ")}")
    }
}


fun line() {
    val tokens = mutableListOf<String>()
    val scanner = Scanner(System.`in`)
    var str = ""
    while (scanner.hasNextLine()) {
        val lineScanner = Scanner(scanner.nextLine())
        tokens.add(lineScanner.nextLine())
        while (lineScanner.hasNext()) {
            str += lineScanner.nextLine()
        }
        println(str)
        tokens.add(str)
        str = ""
        println(tokens)
        lineScanner.close()
    }
    scanner.close()

    val longest =  tokens.sortedBy {it.length} .last()
    val n = (tokens.filter {it == longest  }).count()
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

    val longest =  words.sortedBy {it.length} .last()
    // val longest = words.maxByOrNull { it.length }!!
    val n = (words.filter {it == longest  }).count()
    val percentage = n / words.size * 100

    println("Total words: ${words.size}.")
    println("The longest word: $longest ($n time(s), $percentage%).")
}


/*
val numbers = IntArray(5) { readln().toInt() } // on each line single numbers from 1 to 5
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