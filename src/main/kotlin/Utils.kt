@file:Suppress("NOTHING_TO_INLINE")

import java.nio.file.*
import kotlin.io.path.*

val dayRegex = "Day(\\d+)Kt".toRegex()
val inputDir = Path("input")

fun dayPath(test: Boolean, index: Int?): Path {
	val className = Throwable().stackTrace[1].className
	val (day) = dayRegex.find(className)?.destructured ?: error("$className doesn't follow the naming convention")
	val fileName = if (test) "day_${day}_test" else "day_$day"
	val suffix = if (index != null) "_$index" else ""
	return (inputDir / "$fileName$suffix.txt").apply { if (!exists()) writeText("") }
}

inline fun readInput(test: Boolean = false, index: Int? = null) = dayPath(test, index).readText()
inline fun readInputLines(test: Boolean = false, index: Int? = null) = dayPath(test, index).readLines()

fun <T> checkEquals(value: T, expected: Int) =
	check((value == Unit && expected == 0) || value as Int == expected) { "Expected $expected, got $value" }