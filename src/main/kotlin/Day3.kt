@file:Suppress("NAME_SHADOWING")

fun main() {
    val partOne = partOne@{ lines: List<String> ->
        val lineLength = lines[0].length

        val checkSymbols = checkSymbols@{ i: Int, j: Int ->
            for (k in i - 1..i + 1) {
                for (l in j - 1..j + 1) {
                    if (
                        (k == i && l == j) // Skip current char
                        || (k < 0 || l < 0 || k >= lines.size || l >= lineLength) // Skip out of bounds
                    ) continue

                    val char = lines[k][l]
                    if (!char.isDigit() && char != '.') return@checkSymbols true
                }
            }
            false
        }

        val numbers = mutableListOf<Int>()
        lines.forEachIndexed { i, line ->
            var j = 0
            while (j < lineLength) {
                val char = line[j]
                if (char.isDigit()) {
                    var number = char.toString() // Start of number
                    var hasSymbol = false
                    if (checkSymbols(i, j)) hasSymbol = true
                    while (j + 1 < lineLength && line[j + 1].isDigit()) { // Find end of number
                        if (checkSymbols(i, j + 1)) hasSymbol = true
                        number += line[j + 1]
                        j++
                    }
                    if (hasSymbol) numbers.add(number.toInt())
                }
                j++
            }
        }
        numbers.sum()
    }

    val partTwo = partTwo@{ lines: List<String> ->
        val lineLength = lines[0].length

        val findNumber = findNumber@{ i: Int, j: Int ->
            when {
                i < 0 || j < 0 || i >= lines.size || j >= lineLength -> 0 // Skip out of bounds
                !lines[i][j].isDigit() -> 0 // Skip non-digit
                else -> {
                    var j = j
                    while (j >= 0 && lines[i][j].isDigit()) j-- // Find start of number
                    var number = ""
                    while (j + 1 < lineLength && lines[i][j + 1].isDigit()) { // Find end of number
                        number += lines[i][j + 1]
                        j++
                    }
                    number.toInt()
                }
            }
        }

        val findNumbers = findNumbers@{ i: Int, j: Int ->
            val numbers = mutableListOf<Int>()
            for (k in i - 1..i + 1) {
                for (l in j - 1..j + 1) {
                    if (k == i && l == j) continue // Skip current char
                    val foundNumber = findNumber(k, l)
                    if (
                        foundNumber != 0 // Skip non-digit
                        && (numbers.isEmpty() || numbers.last() != foundNumber) // Skip immediate duplicates
                    ) numbers.add(foundNumber)
                }
            }
            numbers
        }

        val numbers = mutableListOf<Int>()
        lines.forEachIndexed { i, line ->
            line.forEachIndexed { j, char ->
                if (char == '*') {
                    val foundNumbers = findNumbers(i, j)
                    if (foundNumbers.size == 2) numbers.add(foundNumbers[0] * foundNumbers[1])
                }
            }
        }
        numbers.sum()
    }

    val testInput = readInputLines("day_3_test")
    checkEquals(partOne(testInput), 4361)
    checkEquals(partTwo(testInput), 467835)

    val input = readInputLines("day_3")
    println("Part one: ${partOne(input)}")
    println("Part two: ${partTwo(input)}")
}
