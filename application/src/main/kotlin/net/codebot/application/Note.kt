package net.codebot.application

import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.input.MouseEvent
import javafx.scene.layout.HBox

class Note(private val model : Model, title : String, group : String, body : String) {
    private var oldtitle = title

    val titleField = TextField(title).apply {
        padding = Insets(5.0)
        // maxWidth = 100.0
        alignment = Pos.CENTER
        textProperty().addListener { _, oldVal, newVal ->
            updateButton.isDisable = false
        }
    }

    val groupField = TextField(group).apply {
        padding = Insets(5.0)
        // maxWidth = 100.0
        alignment = Pos.CENTER
        textProperty().addListener { _, _, _ ->
            updateButton.isDisable = false
        }
    }

    private val bodyDisplay = Label().apply {
        if (body.length >= 50) {
            text = body.substring(0,50)
        } else {
            text = body
        }
    }

    private val editButton = Button("Edit").apply {
        padding = Insets(5.0)
        prefWidth = 40.0
    }

    private val deleteButton = Button("Delete").apply {
        padding = Insets(5.0)
        prefWidth = 40.0
    }

    private val updateButton = Button("Update").apply {
        padding = Insets(5.0)
        prefWidth = 40.0
        isDisable = true
    }

    // create hbox for each of the fields
    private val titleBlock = HBox(titleField).apply {
        padding = Insets(5.0)
    }

    private val block = HBox(titleField, groupField, bodyDisplay, editButton, updateButton, deleteButton).apply {
        maxWidth = Double.MAX_VALUE
    }

    fun getBox(): HBox {
        return block
    }
    init {
        editButton.addEventHandler(MouseEvent.MOUSE_CLICKED) {
            //TODO: Open the HTML editor for the current note
        }

        updateButton.addEventHandler(MouseEvent.MOUSE_CLICKED) {
            updateButton.isDisable = true
            model.updateNote(oldtitle, titleField.text,groupField.text)
        }

        deleteButton.addEventHandler(MouseEvent.MOUSE_CLICKED) {
            model.deleteNote(title)
        }
    }
}