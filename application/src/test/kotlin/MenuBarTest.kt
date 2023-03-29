package net.codebot.application

import javafx.application.Platform
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

internal class MenuBarTest {
    private val model: Model = Model()
    private val menuBarTest: MenuBarClass = MenuBarClass(model)

    @Test
    fun testGetFileText() {
        menuBarTest.getFileOptions().items.find {
            it.text == "Save"
        }!!.fire()

        val expectedText = "Save"
        val actualText = menuBarTest.getFileText()
        assertEquals(expectedText, actualText, "getFileText should return the correct selected file option text")
    }

    companion object {
        @JvmStatic
        @BeforeAll
        fun initJfxRuntime(): Unit {
            Platform.startup { }
        }
    }
}

