import kotlin.io.path.*

fun readInput(name: String) = Path("input/$name.txt").readText()
fun readInputLines(name: String) = Path("input/$name.txt").readLines()

fun <T> checkEquals(value: T, expected: T) = check(value == expected) { "Expected $expected, got $value" }

inline fun <T> List<T>.splitOn(predicate: (T) -> Boolean): List<List<T>> {
	val result = mutableListOf<List<T>>()
	var current = mutableListOf<T>()
	forEach { item ->
		if (predicate(item)) {
			result.add(current)
			current = mutableListOf()
		} else current.add(item)
	}
	result.add(current)
	return result
}

fun <T> Collection<T>.intersect(vararg collections: Collection<T>): Set<T> {
	val intersection = mutableSetOf<T>()
	forEach { item -> if (collections.all { it.contains(item) }) intersection.add(item) }
	return intersection
}

fun IntRange.contains(other: IntRange) = first <= other.first && last >= other.last

fun IntRange.overlaps(other: IntRange) = first <= other.last && last >= other.first

fun IntArray.product(): Int {
	var product = 1
	for (element in this) product *= element
	return product
}