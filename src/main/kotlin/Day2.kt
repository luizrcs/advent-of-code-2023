fun main() {
	val partOne = partOne@{ lines: List<String> ->
		val amounts = mapOf("red" to 12, "green" to 13, "blue" to 14)
		val gameRegex = "Game (\\d+):".toRegex()

		lines.sumOf game@{ line ->
			line.substringAfter(": ")
				.split("; ")
				.forEach { set ->
					set.split(", ")
						.forEach { cubes ->
							val space = cubes.indexOf(" ")
							val amount = cubes.substring(0, space).toInt()
							val color = cubes.substring(space + 1)
							if (amount > amounts[color]!!) return@game 0 // Do not sum this game id
						}
				}

			gameRegex.find(line)!!.groupValues[1].toInt() // Sum this game id
		}
	}

	val partTwo = partTwo@{ lines: List<String> ->
		lines.sumOf { line ->
			var red = 0
			var green = 0
			var blue = 0

			line.substringAfter(": ")
				.split("; ")
				.forEach { set ->
					set.split(", ")
						.forEach { cubes ->
							val space = cubes.indexOf(" ")
							val amount = cubes.substring(0, space).toInt()
							val color = cubes.substring(space + 1)
							when (color) {
								"red" -> if (amount > red) red = amount
								"green" -> if (amount > green) green = amount
								"blue" -> if (amount > blue) blue = amount
							}
						}
				}

			red * green * blue
		}
	}

	val testInput = readInputLines(true)
	checkEquals(partOne(testInput), 8)
	checkEquals(partTwo(testInput), 2286)

	val input = readInputLines()
	println("Part one: ${partOne(input)}")
	println("Part two: ${partTwo(input)}")
}
