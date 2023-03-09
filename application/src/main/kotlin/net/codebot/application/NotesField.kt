package net.codebot.application

import javafx.scene.control.Button
import javafx.scene.control.ToolBar
import javafx.scene.web.HTMLEditor
import javafx.util.Duration
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.io.FileOutputStream
import java.io.ObjectOutputStream

class NotesField(private val model: Model) : HTMLEditor(), IView {
    override fun update() {
    }
    init {
        model.createView(this)
    }
}