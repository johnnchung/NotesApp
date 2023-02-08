package net.codebot.mvc

import javafx.scene.control.TextField
import javafx.scene.layout.Pane
import javafx.scene.text.Font
import javafx.scene.text.FontWeight

class View : Pane(), IView {
    val textField = TextField()
    init {
        textField.font = Font.font("Arial", FontWeight.BOLD, 36.0)
        textField.text = "Initial text"
        this.children.add(textField)
    }
    override fun update(timer: Long) {
        textField.text = "$timer"
    }
}