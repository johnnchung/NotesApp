package net.codebot.application

import javafx.application.Platform
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

class ToolBarClassTest {
    private val model: Model = Model()
    private val toolBarTest: ToolBarClass = ToolBarClass(model)

    @Test
    fun getBoldButton() {
        val button = toolBarTest.getBoldButton()
        button.fire()
        val expectedState = "Bold Text"
        assertEquals(toolBarTest.getButtonState(), expectedState)
    }

    @Test
    fun getItalicsButton() {
        val button = toolBarTest.getItalicsButton()
        button.fire()
        val expectedState = "Italicize Text"
        assertEquals(toolBarTest.getButtonState(), expectedState)
    }

    @Test
    fun getUnderlineButton() {
        val button = toolBarTest.getUnderlineButton()
        button.fire()
        val expectedState = "Underline Text"
        assertEquals(toolBarTest.getButtonState(), expectedState)
    }

    @Test
    fun getHighlightButton() {
        val button = toolBarTest.getHighlightButton()
        button.fire()
        val expectedState = "Highlight Text"
        assertEquals(toolBarTest.getButtonState(), expectedState)
    }

    @Test
    fun getFontButton() {
        val button = toolBarTest.getFontButton()
        button.fire()
        val expectedState = "Change Text Color"
        assertEquals(toolBarTest.getButtonState(), expectedState)
    }

    companion object {
        @JvmStatic
        @BeforeAll
        fun initJfxRuntime(): Unit {
            // Platform.startup { }
        }
    }
}