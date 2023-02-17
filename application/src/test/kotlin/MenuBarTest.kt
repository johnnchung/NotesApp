package net.codebot.application

import javafx.application.Platform
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

internal class MenuBarTest {
    private val model: Model = Model()
    private val menuBarTest: MenuBarClass = MenuBarClass(model)

    @Test
    fun testGetFileText() {
        menuBarTest.getFileOptions().items.find {
            it.text == "New"
        }!!.fire()

        val expectedText = "New"
        val actualText = menuBarTest.getFileText()
        assertEquals(expectedText, actualText, "getFileText should return the correct selected file option text")
    }

    @Test
    fun testEditFileText() {
        menuBarTest.getEditOptions().items.find {
            it.text == "Delete"
        }!!.fire()

        val expectedText = "Delete"
        val actualText = menuBarTest.getEditText()
        assertEquals(expectedText, actualText, "getEditText should return the correct selected edit option text")
    }

    @Test
    fun testViewFileText() {
        menuBarTest.getViewOptions().items.find {
            it.text == "Zoom In"
        }!!.fire()

        val expectedText = "Zoom Out"
        val actualText = menuBarTest.getViewText()
        assertNotEquals(expectedText, actualText, "getViewText should return the correct selected view option text")
    }

    companion object {
        @JvmStatic
        @BeforeAll
        fun initJfxRuntime(): Unit {
            Platform.startup { }
        }
    }
}

