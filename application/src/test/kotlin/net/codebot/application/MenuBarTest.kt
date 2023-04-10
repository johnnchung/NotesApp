package net.codebot.application

import javafx.application.Platform
import javafx.scene.Node
import javafx.scene.layout.BorderPane
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

internal class MenuBarTest {
    private val model: Model = Model()
    private val homePage: Node = BorderPane()
    private val menuBarTest: MenuBarClass = MenuBarClass(model, homePage)

    @Test
    fun saveNotesInstance() {
        menuBarTest.getFileOptions().items.find {
            it.text == "Save"
        }!!.fire()

        val expectedSavedState = true
        val actualSavedState = model.savedNotes
        assertEquals(
            expectedSavedState,
            actualSavedState,
            "selected save should trigger the model.savedNotes to be true."
        )
        model.savedNotes = false
    }

    @Test
    fun zoomInInstance() {
        menuBarTest.getViewOptions().items.find {
            it.text == "Zoom In"
        }!!.fire()

        val expectedHomepageScaleX = 1.1
        val expectedHomepageScaleY = 1.1
        //val expectedSavedState = true
        val actualHomepageScaleX = homePage.scaleX
        val actualHomePageScaleY = homePage.scaleY
        assertEquals(
            expectedHomepageScaleX,
            actualHomepageScaleX,
            "The X scale should be equal after 1  zoom in."
        )
        assertEquals(
            expectedHomepageScaleY,
            actualHomePageScaleY,
            "The Y scale should be equal after 1  zoom in."
        )
    }

    @Test
    fun zoomOutInstance() {
        menuBarTest.getViewOptions().items.find {
            it.text == "Zoom Out"
        }!!.fire()

        val expectedHomepageScaleX = 0.9090909090909091
        val expectedHomepageScaleY = 0.9090909090909091
        val actualHomepageScaleX = homePage.scaleX
        val actualHomePageScaleY = homePage.scaleY
        assertEquals(
            expectedHomepageScaleX,
            actualHomepageScaleX,
            "The X scale should be equal after 1  zoom out."
        )
        assertEquals(
            expectedHomepageScaleY,
            actualHomePageScaleY,
            "The Y scale should be equal after 1  zoom in."
        )
    }

    @Test
    fun MixZoomsInstance() {
        val zoomIn = menuBarTest.getViewOptions().items.find {
            it.text == "Zoom Out"
        }!!
        val zoomOut = menuBarTest.getViewOptions().items.find {
            it.text == "Zoom Out"
        }!!
        // Fire a sequence of zoom commands
        zoomIn.fire()
        zoomOut.fire()
        zoomIn.fire()
        zoomIn.fire()
        zoomIn.fire()
        zoomOut.fire()
        zoomOut.fire()
        zoomIn.fire()
        zoomOut.fire()
        val expectedHomepageScaleX = 0.4240976183724846
        val expectedHomepageScaleY = 0.4240976183724846
        val actualHomepageScaleX = homePage.scaleX
        val actualHomePageScaleY = homePage.scaleY
        assertEquals(
            expectedHomepageScaleX,
            actualHomepageScaleX,
            "The X scale should be equal to 0.424..."
        )
        assertEquals(
            expectedHomepageScaleY,
            actualHomePageScaleY,
            "The Y scale should be equal to 0.424..."
        )

    }

    companion object {
        @JvmStatic
        @BeforeAll
        fun initJfxRuntime(): Unit {
            Platform.startup { }
        }
    }
}