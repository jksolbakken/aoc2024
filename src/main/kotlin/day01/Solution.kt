package day01

import kotlin.math.absoluteValue

fun main() {
    val input = readInputFile().map { it.split("\\s+".toRegex()) }
        .map { it[0].toInt() to it[1].toInt() }
        .unzip()
    val firstGroup = input.first.sorted()
    val secondGroup = input.second.sorted()
    val answerPart1 = part1(firstGroup, secondGroup)
    val answerPart2 = part2(firstGroup, secondGroup)
    println("Part1: $answerPart1, part 2: $answerPart2")
}

fun part1(firstGroup: List<Int>, secondGroup: List<Int>) =
    firstGroup.zip(secondGroup) { first, second->
        (first - second).absoluteValue
    }.sum()

fun part2(firstGroup: List<Int>, secondGroup: List<Int>): Int {
    val grouped = secondGroup.groupBy { it }
    return firstGroup.map { it * (grouped[it]?.size ?: 0) }.sum()
}

private fun readInputFile() = object{}::class.java.getResourceAsStream("/day01/input")
    ?.bufferedReader()
    ?.readLines()
    ?.filter { it.isNotBlank() }
    ?: throw RuntimeException("couldn't read input")