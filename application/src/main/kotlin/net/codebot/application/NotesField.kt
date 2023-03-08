package net.codebot.application

import javafx.scene.control.Button
import javafx.scene.control.ToolBar
import javafx.scene.web.HTMLEditor
import javafx.util.Duration

class NotesField(private val model: Model) : HTMLEditor(), IView {
    override fun update() {

    }
    init {
        model.createView(this)
    }
}