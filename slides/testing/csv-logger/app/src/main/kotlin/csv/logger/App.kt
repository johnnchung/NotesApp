import java.io.BufferedWriter
import java.io.FileWriter
import java.text.SimpleDateFormat
import java.util.*

var file: FileWriter? = null
var writer: BufferedWriter? = null

// Data class to represent a row
data class Entry(val type: TYPE, val description: String, val value: Int) {
    private val datetime = getCurrentDateTime().toString()
    override fun toString(): String = "$datetime, $type, $description, $value"
}
enum class TYPE { DEBUG, LOG}

// Date formatting to capture the datetime
fun Date.toString(format: String): String = SimpleDateFormat(format, Locale.getDefault()).format(this)
fun getCurrentDateTime(): Date = Calendar.getInstance().time

// Logging functions
fun open(filename: String) {
    file = FileWriter(filename)
    writer = BufferedWriter(file)
}

// Logging functions
fun debug(description: String, value: Int) = save(Entry(TYPE.DEBUG, description, value))
fun log(description: String, value: Int) = save(Entry(TYPE.LOG, description, value))
private fun save(entry: Entry) = writer?.write(entry.toString() + "\n")

fun close() {
    writer?.close()
    file?.close()
}