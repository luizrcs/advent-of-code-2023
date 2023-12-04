fun main() {
	fun String.mapToInts() = splitToSequence(" ").filter { it.isNotBlank() }.map { it.toInt() }

	val countWinning = { line: String ->
		val (winning, ours) = line.split(": ", " | ").drop(1).map { it.mapToInts() }
		ours.count { it in winning }
	}

	val partOne = partOne@{ lines: List<String> ->
		lines.sumOf { line ->
			val count = countWinning(line)
			if (count == 0) 0 else 2.pow(count - 1) // 2^(count - 1) points
		}
	}

	val partTwo = partTwo@{ lines: List<String> ->
		val cards = IntArray(lines.size) { 1 } // Start with 1 of each card
		lines.forEachIndexed { index, line ->
			val count = countWinning(line)
			repeat(cards[index]) { // For each copy of the current card...
				(1 .. count).forEach { cards[index + it] += 1 } // ...add a copy to the next 'count' cards
			}
		}
		cards.sum()
	}

	val testInput = readInputLines(true)
	checkEquals(partOne(testInput), 13)
	checkEquals(partTwo(testInput), 30)

	val input = readInputLines()
	println("Part one: ${partOne(input)}")
	println("Part two: ${partTwo(input)}")
}