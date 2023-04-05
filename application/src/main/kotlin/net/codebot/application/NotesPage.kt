package net.codebot.application

import javafx.scene.input.KeyCode
import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority
import javafx.scene.layout.VBox

class NotesPage(private var model : Model, private val title: String): BorderPane(), IView {
    private var noteTitle = title
    var notes = NotesTextEditor(model, noteTitle)

    override fun update() {
    }

    init {
        this.apply {
            HBox.setHgrow(this, Priority.ALWAYS)
        }
        this.apply {
            center = notes
        }
        val zoomInField = this.center.lookup(".web-view")
        val menuBar = MenuBarClass(model, zoomInField)
        this.apply {
            top = VBox(menuBar)
        }
        model.createView(this)
    }
}