import java.io.File
import kotlin.properties.Delegates

// Text listener based on example from
// https://github.com/dbacinski/Design-Patterns-In-Kotlin#observer--listener

interface TextChangedListener {
    fun onTextChanged(oldText: String, newText: String)
}

class PrintingTextChangedListener : TextChangedListener {
    override fun onTextChanged(oldText: String, newText: String) {
        println("Text is changed: $oldText -> $newText")
    }
}

class LoggingTextChangedListener(val filename: String) : TextChangedListener {
    override fun onTextChanged(oldText: String, newText: String) {
        File(filename).appendText("\n$oldText -> $newText")
    }
}

class TextField {
    val listeners = mutableListOf<TextChangedListener>()
    var text: String by Delegates.observable("<empty>") { _, old, new ->
        listeners.forEach { it.onTextChanged(old, new) }
    }
}