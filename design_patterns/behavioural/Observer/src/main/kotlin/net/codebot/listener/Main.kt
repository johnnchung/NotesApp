/*
 * Observer Design Pattern
 * (c) 2022 Jeff Avery
 */

fun main() {
    val textField = TextField()
    textField.listeners.add(PrintingTextChangedListener())
    textField.listeners.add(LoggingTextChangedListener("debug.log"))

    with(textField) {
        text = "${System.currentTimeMillis()} first value"
        text = "${System.currentTimeMillis()} second value"
    }
}