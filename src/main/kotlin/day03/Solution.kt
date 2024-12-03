package day03

fun main() {
    val input = prepareInput()
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}

private fun part1(input: List<String>) = input.asSequence()
    .map { legalTextPart1.findAll(it) }
    .flatMap { it.toList() }
    .map { it.value }
    .map(::calculate)
    .sum()

private fun part2(input: List<String>): Int {
    val commandSequence = input
        .map { legalTextPart2.findAll(it) }
        .flatMap { it.toList() }
        .map { it.value }

    var doIt = true
    var sum = 0
    for (command in commandSequence) {
        when (command) {
            "do()" -> doIt = true
            "don't()" -> doIt = false
            else -> if (doIt) sum += calculate(command)
        }
    }
    return sum
}


private val legalTextPart1 = "mul\\(\\d+,\\d+\\)".toRegex()
private val legalTextPart2 = "(mul\\(\\d+,\\d+\\)|don't\\(\\)|do\\(\\))".toRegex()

private fun calculate(multiplication: String): Int {
    val startParen = multiplication.indexOf('(') + 1
    val endParen = multiplication.indexOf(')')
    val (first, second) = multiplication.substring(startParen, endParen).split(",")
    return first.toInt() * second.toInt()
}

private fun prepareInput() = object{}::class.java.getResourceAsStream("/day03/input")
    ?.bufferedReader()
    ?.readLines()
    ?.filter { it.isNotBlank() }
    ?: throw RuntimeException("couldn't read input")