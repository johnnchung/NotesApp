package net.codebot.application

import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority
import javafx.scene.layout.VBox

class NotesPage(private var model : Model, private val title: String): BorderPane(), IView {
    private var noteTitle = title
    private var noteGroup: String? = null
    private var noteContent: String? = null
    var notes = NotesField(model, noteTitle)

    init {
        val menuBar = MenuBarClass(model).apply {
            HBox.setHgrow(this, Priority.ALWAYS)
        }
        this.apply {
            top = VBox(menuBar)
            center = notes
        }
        model.createView(this)
    }

    override fun update() {
    }
}