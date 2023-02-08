/*
 * Strategy Design Pattern
 * Choose a suitable algorithm at runtime (instead of compile-time).
 * (c) 2022 Jeff Avery
 */

// string format functions
val noformat: (String) -> String = { it }
val lowerCase: (String) -> String = { it.lowercase() }
val upperCase: (String) -> String = { it.uppercase() }
val reversed: (String) -> String = { it.reversed() }

fun print(statement: String, format: (String) -> String) {
    println(format(statement))
}

fun main() {
    val message = "TEST message"
    print(message, noformat)
    print(message, lowerCase)
    print(message, upperCase)
    print(message, reversed)
}