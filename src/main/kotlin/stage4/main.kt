package stage4

import java.lang.Integer.parseInt
import java.util.Scanner

fun main(args: Array<String>) {
    when {
        args.contains("-sortingType") && args.contains("byCount") && args.contains("long") -> long(true)
        args.contains("-sortingType") && args.contains("byCount") && args.contains("line") -> line(true)
        args.contains("-sortingType") && args.contains("byCount") && args.contains("word") -> word(true)
        (args.contains("-sortingType") || args.contains("-dataType")) && args.contains("long") -> long()
        (args.contains("-sortingType") || args.contains("-dataType")) && args.contains("line") && !args.contains("byCount") -> line()
        (args.contains("-sortingType") || args.contains("-dataType")) && args.contains("word") -> word()
    }
       // else -> long(true)
}
// -sortingType byCount -dataType line
// -sortingType
// natural
// byCount

fun long(byCount: Boolean = false) {
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

    val list2 = mutableListOf<Int>()    // список с числами
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
    if (byCount) {
        // println("Total numbers: ${list2.size}")
        val map = mutableMapOf<Int, Int>()
        var temp = 0
        var n = 0
        for (i in list2) {
            temp = i
            n = 0
            for (j in list2) {
                if (j == temp) {
                    n++
                }
            }
            map += temp to n
        }
        val sortedMap1 = map.toSortedMap()
        var sortedMap2: MutableMap<Int,Int> = LinkedHashMap()
        sortedMap1.entries.sortedBy { it.value }.forEach{sortedMap2[it.key] = it.value} //.thenBy { it }

        for ((k, v) in sortedMap2) {
            println("$k: $v time(s), ${ v / list2.size * 100 }%")
        }
    } else {
        // println("Total numbers: ${list2.size}.")
        println("Sorted data: ${list2.sorted().joinToString(" ")}")
    }
}

fun line(byCount: Boolean = false) {
    val tokens = mutableListOf<String>()
    val scanner = Scanner(System.`in`)
    var str = ""
    while (scanner.hasNextLine()) {
        val lineScanner = Scanner(scanner.nextLine())
        tokens.add(lineScanner.nextLine())
        while (lineScanner.hasNext()) {
            str += lineScanner.nextLine().trim()
        }
        // println(str)
        tokens.add(str)
        str = ""
        // println(tokens)
        lineScanner.close()
    }
    scanner.close()
    // println(tokens)
    //tokens.remove


    println("Total numbers: ${tokens.size}.")
    if (byCount) {
        val map = mutableMapOf<String, Int>()
        var temp = ""
        var n = 0
        for (i in tokens) {
            temp = i
            n = 0
            for (j in tokens) {
                if (j == temp) {
                    n++
                }
            }
            map += temp to n
        }
        val sortedMap1 = map.toSortedMap()
        var sortedMap2: MutableMap<String,Int> = LinkedHashMap()
        sortedMap1.entries.sortedBy { it.value }.forEach{sortedMap2[it.key] = it.value}

        for ((k, v) in sortedMap2) {
            println("$k: $v time(s), ${ (v.toDouble() / tokens.size * 100).toInt() }%")
        }

    } else {
        println("Sorted data:")
        val words2 = tokens.sortedByDescending { it.length }
        for (i in words2) {
            println(i)
        }
    }
}

fun word(byCount: Boolean = false) {
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

    if (byCount) {
        println("Total numbers: ${words.size}.")
        val map = mutableMapOf<String, Int>()
        var temp = ""
        var n = 0
        for (i in words) {
            temp = i
            n = 0
            for (j in words) {
                if (j == temp) {
                    n++
                }
            }
            map += temp to n
        }
        val sortedMap1 = map.toSortedMap()
        var sortedMap2: MutableMap<String,Int> = LinkedHashMap()
        sortedMap1.entries.sortedBy { it.value }.forEach{sortedMap2[it.key] = it.value}

        for ((k, v) in sortedMap2) {
            println("$k: $v time(s), ${ (v.toDouble() / words.size * 100).toInt() }%")
        }
    } else {
        println("Total words: ${words.size}.")
        println("Sorted data: ${words.sortedBy {it.length}.joinToString(" ")}")
    }
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

// You can sort the map by values like this:
//
//map.toList().sortedBy { it.second }.toMap())

/*
val numbersGrval =
    numbers.groupingBy { it }.eachCount().toList().sortedBy { it.first }.sortedBy { it.second }
println(numbersGrouped.joinToString("\n") { "${it.first}: ${it.second} time(s), ${it.second * 100 / numbers.size}%" })

 */