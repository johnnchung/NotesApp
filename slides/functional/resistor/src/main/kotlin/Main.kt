// Demo from https://www.youtube.com/watch?v=nUzctvXb30I
// which was in turn taken from an exercise on https://exercism.org

// resistor colors: each color has an associated ordinal value e.g. BLACK=0, RED=2
enum class Color { BLACK, BROWN, RED, ORANGE, YELLOW, GREEN, BLUE, VIOLET, GRAY, WHITE }

// Converts a pair of colors to their ordinal values e.g. (BLACK, BROWN) -> (01)
object ResistorColorDuo {
    // imperative approach: builds an intermediate string
    fun value(vararg colors: Color): Int {
        var result = StringBuilder()
        for (color in colors) {
            result.append(color.ordinal)
        }
        return result.take(2).toString().toInt()
    }

    // functional approach: same result without using intermediate string
    fun fvalue(vararg colors: Color): Int {
        return colors
            .take(2)
            .joinToString("") { it.ordinal.toString() }
            .toInt()
    }
}

fun main() {
    val ivalue = ResistorColorDuo.value(Color.BLUE, Color.ORANGE)
    println("ivalue is ${ivalue}")

    val fvalue = ResistorColorDuo.fvalue(Color.BLUE, Color.ORANGE)
    println("fvalue is ${fvalue}")
}