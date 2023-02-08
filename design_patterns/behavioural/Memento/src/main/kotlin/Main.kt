/*
 * Memento Design Pattern
 * (c) 2022 Jeff Avery
 * Save and restore multiple versions of an application's state
 */

fun main() {
    val book = Book(title = "The Hobbit", "JRR Tolkien", 1937)
    println(book)

    book.save()
    book.title = "Lord of the Rings"
    book.year = 1954
    println(book)

    book.restore()
    println(book)
}

class Book(var title: String, var author: String, var year: Int) {
    // mementos are not accessible to other classes
    private data class Memento(val title: String, val author: String, val year: Int)

    private object UndoManager {
        private val mementos = mutableListOf<Memento>()
        fun save(title: String, author: String, year: Int) {
            mementos.add(Memento(title = title, author = author, year = year))
        }
        fun restore() = mementos.last()
    }

    fun save() {
        UndoManager.save(title = this.title, author = this.author, year = this.year)
    }

    fun restore() {
        val memento = UndoManager.restore()
        this.title = memento.title
        this.author = memento.author
        this.year = memento.year
    }

    override fun toString(): String {
        return "$title, $author, $year"
    }
}