package day02

import day02.Direction.DEC
import day02.Direction.INC

fun main() {
    val safeReportsPart1= prepareInput()
        .filter { isSafePart1(it) }
        .size
    val safeReportsPart2= prepareInput()
        .filter { isSafePart2(it) }
        .size
    println(safeReportsPart1)
    println(safeReportsPart2)
}

enum class Direction { INC, DEC }

typealias SafetyCheck = (List<Int>, Direction) -> Boolean

fun isSafePart1(report: List<Int>) = (allInTheSameDirection(report, INC) && safeDistances(report, INC))
        || (allInTheSameDirection(report, DEC) && safeDistances(report, DEC))

fun isSafePart2(report: List<Int>): Boolean {
    var isSafe = isSafePart1(report)
    var idxToRemove = 0
    while (!isSafe && idxToRemove < report.size) {
        val mutable = report.toMutableList()
        mutable.removeAt(idxToRemove)
        isSafe = isSafePart1(mutable)
        idxToRemove++
    }
    return isSafe
}

val allInTheSameDirection: SafetyCheck = { report, direction ->
    var isSafe = true
    for (i in 0..report.size - 2) {
        val indices = if (direction == INC) (i + 1 to i) else (i to i + 1)
        if (report[indices.first] <= report[indices.second]) {
            isSafe = false
            break
        }
    }
    isSafe
}

val safeDistances: SafetyCheck = { report, direction ->
    var isSafe = true
    for (i in 0..report.size - 2) {
        val indices = if (direction == INC) (i + 1 to i) else (i to i + 1)
        val diff = report[indices.first] - report[indices.second]
        if (diff !in 1..3) {
            isSafe = false
            break
        }
    }
    isSafe
}

private fun prepareInput() = object{}::class.java.getResourceAsStream("/day02/input")
    ?.bufferedReader()
    ?.readLines()
    ?.filter { it.isNotBlank() }
    ?.map { it.map { it.toInt() } }
    ?: throw RuntimeException("couldn't read input")