package stage6

import java.io.File
import java.lang.Integer.parseInt
import java.util.Scanner

fun main(args: Array<String>) {

    for (i in args) {
        if (i != "-sortingType" && i != "byCount" && i != "-dataType" && i != "long" && i != "line" && i != "word"
            && i != "natural" && i != "-inputFile" && i != "-outputFile" && !i.contains(".txt") && !i.contains(".dat")
        ) {
            println("$i is not a valid parameter. It will be skipped")
        }
    }
// ищем названия файлов
    var inputFile = ""
    var outputFile = ""
    for (i in args.indices) {
        if (args[i] == "-inputFile") {
        inputFile = args[i+1]
            }
        if (args[i] == "-outputFile") {
        outputFile = args[i+1]
        }
    }
    // проверяем, существует ли файл input
    try {
        val str = File(inputFile).readText()
    } catch (e: Exception) {
        println("File is not exist")
        inputFile = ""
    }

    when {
        args.contains("-sortingType") && !(args.contains("long") || args.contains("line") || args.contains("word")) -> println("No sorting type defined!")
        args.contains("-dataType") && !(args.contains("long") ||  args.contains("line") || args.contains("word")) -> println("No data type defined!")
        args.contains("-sortingType") && args.contains("byCount") && args.contains("long") -> long(true, inputFile, outputFile)
        args.contains("-sortingType") && args.contains("byCount") && args.contains("line") -> line(true, inputFile, outputFile)
        args.contains("-sortingType") && args.contains("byCount") && args.contains("word") -> word(true, inputFile, outputFile)
        (args.contains("-sortingType") || args.contains("-dataType")) && args.contains("long") -> long(false, inputFile, outputFile)
        (args.contains("-sortingType") || args.contains("-dataType")) && args.contains("line") && !args.contains("byCount") -> line(false, inputFile, outputFile)
        (args.contains("-sortingType") || args.contains("-dataType")) && args.contains("word") -> word(false, inputFile, outputFile)
    }
}

fun long(byCount: Boolean = false, inputFile: String = "", outputFile: String = "") {
    var tokens = mutableListOf<String>()
    if (inputFile.isEmpty()) {
        val scanner = Scanner(System.`in`)
        while (scanner.hasNextLine()) {
            val lineScanner = Scanner(scanner.nextLine())
            while (lineScanner.hasNext()) {
                tokens.add(lineScanner.next())
            }
            lineScanner.close()
        }
        scanner.close()
    } else {
        val lines = File(inputFile).readText()
        tokens = lines.split(" ").toMutableList()       // "\r?\n|\r".toRegex()
    }

    val list2 = mutableListOf<Int>()    // список с числами
    for (i in tokens) {
        try {
            list2.add(parseInt(i))
        } catch(e: Exception) {
            println("$i is not a long. It will be skipped.")
        }
    }

    val list3 = list2.sortedDescending()
    var max = list3[0]
    var n = 0
    for (i in list3) {
        if (i == max) {
            n++
        }
    }

    var sortedMap2: MutableMap<Int,Int> = LinkedHashMap()
        if (byCount) {
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
            sortedMap1.entries.sortedBy { it.value }.forEach{sortedMap2[it.key] = it.value} //.thenBy { it }
            if (outputFile.isEmpty()) {
                println("Total numbers: ${ list3.size }")
                for ((k, v) in sortedMap2) {
                    println("$k: $v time(s), ${ v / list2.size * 100 }%")
                }
            } else {
                File(outputFile).writeText("Total numbers: ${ list3.size }")
                for ((k, v) in sortedMap2) {
                    File(outputFile).appendText("$k: $v time(s), ${ v / list2.size * 100 }%")
                }
            }
        } else {
            if (outputFile.isEmpty()) {
                println("Total numbers: ${ list3.size }")
                println("Sorted data: ${list2.sorted().joinToString(" ")}")
            } else {
                File(outputFile).writeText("Total numbers: ${ list3.size }")
                File(outputFile).appendText("Sorted data: ${list2.sorted().joinToString(" ")}")
            }
        }
}

fun line(byCount: Boolean = false, inputFile: String = "", outputFile: String = "") {
    var tokens = mutableListOf<String>()
    if (inputFile.isEmpty()) {
        val scanner = Scanner(System.`in`)
        var str = ""
        var index = 0
        while (scanner.hasNextLine()) {
            val lineScanner = Scanner(scanner.nextLine())
            tokens.add(lineScanner.nextLine())
            while (lineScanner.hasNext()) {
                str += lineScanner.nextLine().trim()
            }
            tokens.add(str)
            index++
            tokens.removeAt(index)
            str = ""
            lineScanner.close()
        }
        scanner.close()
    } else {
        val lines = File(inputFile).readText()
        tokens = lines.split("\r?\n|\r".toRegex()).toMutableList()
    }


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

            if (outputFile.isEmpty()) {
                println("Total numbers: ${tokens.size}.")
                for ((k, v) in sortedMap2) {
                    println("$k: $v time(s), ${ (v.toDouble() / tokens.size * 100).toInt() }%")
                }
            } else {
                File(outputFile).writeText("Total numbers: ${tokens.size}.")
                for ((k, v) in sortedMap2) {
                    File(outputFile).appendText("$k: $v time(s), ${ (v.toDouble() / tokens.size * 100).toInt() }%")
                }
            }

        } else {
            if (outputFile.isEmpty()) {
                println("Total numbers: ${tokens.size}.")
                println("Sorted data:")
                val words2 = tokens.sortedByDescending { it.length }
                for (i in words2) {
                    println(i)
                }
            } else {
                File(outputFile).writeText("Total numbers: ${tokens.size}.")
                File(outputFile).appendText("Sorted data:")
                val words2 = tokens.sortedByDescending { it.length }
                for (i in words2) {
                    File(outputFile).appendText(i)
                }
            }
        }
}

fun word(byCount: Boolean = false, inputFile: String = "", outputFile: String = "") {
    var words = mutableListOf<String>()
    if (inputFile.isEmpty()) {
        val scanner = Scanner(System.`in`)
        while (scanner.hasNextLine()) {
            val lineScanner = Scanner(scanner.nextLine())
            while (lineScanner.hasNext()) {
                words.add(lineScanner.next())
            }
            lineScanner.close()
        }
        scanner.close()
    } else {
        val lines = File(inputFile).readText()
        words = lines.split(" ").toMutableList()
    }

    if (byCount) {
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

        if (outputFile.isEmpty()) {
            println("Total numbers: ${words.size}.")
            for ((k, v) in sortedMap2) {
                println("$k: $v time(s), ${ (v.toDouble() / words.size * 100).toInt() }%")
            }
        } else {
            File(outputFile).writeText("Total numbers: ${words.size}.")
            for ((k, v) in sortedMap2) {
                File(outputFile).appendText("$k: $v time(s), ${ (v.toDouble() / words.size * 100).toInt() }%")
            }
        }

    } else {
        if (outputFile.isEmpty()) {
            println("Total words: ${words.size}.")
            println("Sorted data: ${words.sortedBy {it.length}.joinToString(" ")}")
        } else {
            File(outputFile).writeText("Total numbers: ${words.size}.")
            File(outputFile).appendText("Sorted data: ${words.sortedBy {it.length}.joinToString(" ")}")
        }
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



// чтение из файла
    /*
    val fileName = "src/words_sequence.txt"
    val lines = File(fileName).readText()
    val list = lines.split("\r?\n|\r".toRegex()).toTypedArray()
    val list2 = list.sortedBy { it.length}
    //println(list.joinToString())
    println(list2.joinToString())
    println(list2[0])
    println(list2.last().length)
*/