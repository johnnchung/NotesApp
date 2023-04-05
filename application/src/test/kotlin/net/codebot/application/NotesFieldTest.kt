package net.codebot.application

import javafx.application.Platform
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

class NotesFieldTest {
    private val model: Model = Model()

    @Test
    fun getText() {
        val text = "Hello, world!"
        val updated = Runnable {
            val notesFieldTest = NotesTextEditor(model, "hello")
            notesFieldTest.htmlText = text
            assertEquals(text, notesFieldTest.htmlText)
        }
        Platform.runLater(updated)
    }

    companion object {
        @JvmStatic
        @BeforeAll
        fun initJfxRuntime(): Unit {
            // Platform.startup { }
            // Platform.runLater { }
        }
    }
}