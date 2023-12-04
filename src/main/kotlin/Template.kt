fun main() {
	val partOne = partOne@{ lines: List<String> ->
	}

	val partTwo = partTwo@{ lines: List<String> ->
	}

	val testInput = readInputLines(true)
	checkEquals(partOne(testInput), 0)
	checkEquals(partTwo(testInput), 0)

	val input = readInputLines()
	println("Part one: ${partOne(input)}")
	println("Part two: ${partTwo(input)}")
}
