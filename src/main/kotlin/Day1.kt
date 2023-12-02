fun main() {
	val partOne = partOne@{ lines: List<String> ->
		lines.sumOf { line ->
			val first = line.first { char -> char.isDigit() }.digitToInt()
			val last = line.last { char -> char.isDigit() }.digitToInt()
			first * 10 + last
		}
	}

	val partTwo = partTwo@{ lines: List<String> ->
		val letterDigits = listOf(
			"zero" to 0,
			"one" to 1,
			"two" to 2,
			"three" to 3,
			"four" to 4,
			"five" to 5,
			"six" to 6,
			"seven" to 7,
			"eight" to 8,
			"nine" to 9
		)
		lines.sumOf { line ->
			var firstLetterIndex = Int.MAX_VALUE
			var firstLetterValue = 0

			var lastLetterIndex = Int.MIN_VALUE
			var lastLetterValue = 0

			letterDigits.forEach { (letter, value) ->
				val firstIndex = line.indexOf(letter)
				if (firstIndex != -1 && firstIndex < firstLetterIndex) {
					firstLetterIndex = firstIndex
					firstLetterValue = value
				}

				val lastIndex = line.lastIndexOf(letter)
				if (lastIndex != -1 && lastIndex > lastLetterIndex) {
					lastLetterIndex = lastIndex
					lastLetterValue = value
				}
			}

			val firstDigitIndex = line.indexOfFirst { char -> char.isDigit() }.takeIf { it != -1 } ?: Int.MAX_VALUE
			val firstDigitValue = if (firstDigitIndex != Int.MAX_VALUE) line[firstDigitIndex].digitToInt() else 0

			val lastDigitIndex = line.indexOfLast { char -> char.isDigit() }
			val lastDigitValue = if (lastDigitIndex != -1) line[lastDigitIndex].digitToInt() else 0

			val first = if (firstLetterIndex < firstDigitIndex) firstLetterValue else firstDigitValue
			val last = if (lastLetterIndex > lastDigitIndex) lastLetterValue else lastDigitValue

			first * 10 + last
		}
	}

	val testInputOne = readInputLines("day_1_test_1")
	checkEquals(partOne(testInputOne), 142)

	val testInputTwo = readInputLines("day_1_test_2")
	checkEquals(partTwo(testInputTwo), 281)

	val input = readInputLines("day_1")
	println("Part one: ${partOne(input)}")
	println("Part two: ${partTwo(input)}")
}
