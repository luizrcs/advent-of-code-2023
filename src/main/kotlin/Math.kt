fun Int.pow(exponent: Int): Int {
	require(exponent >= 0) { "Exponent must be non-negative, was $exponent" }
	return when (exponent) {
		0 -> 1
		1 -> this
		else -> {
			var result = this
			repeat(exponent - 1) { result *= this }
			result
		}
	}
}