package net.codebot.application

import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.input.MouseEvent
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.CornerRadii
import javafx.scene.layout.HBox
import javafx.scene.layout.HBox.setHgrow
import javafx.scene.layout.Priority
import javafx.scene.paint.Color

class Note(private val model : Model, title : String, group : String, body : String) {
    private var oldtitle = title

    val titleField = TextField(title).apply {
        padding = Insets(5.0)
        prefWidth = 50.0
        alignment = Pos.CENTER
        textProperty().addListener { _, oldVal, newVal ->
            updateButton.isDisable = false
        }
    }

    val groupField = TextField(group).apply {
        padding = Insets(5.0)
        prefWidth = 50.0
        alignment = Pos.CENTER
        textProperty().addListener { _, _, _ ->
            updateButton.isDisable = false
        }
    }

    private val bodyDisplay = Label().apply {
        if (body.length >= 50) {
            text = body.substring(0,50)
        } else if (body.isEmpty()){
            text = "New Note: Please edit"
        }
        else {
            text = body
        }
        setHgrow(this, Priority.ALWAYS)
    }

    private val editButton = Button("Edit").apply {
        padding = Insets(5.0)
        prefWidth = 75.0
    }

    private val deleteButton = Button("Delete").apply {
        padding = Insets(5.0)
        prefWidth = 75.0
    }

    private val updateButton = Button("Update").apply {
        padding = Insets(5.0)
        prefWidth = 75.0
        isDisable = true
    }

    // create hbox for each of the fields
    private val titleBlock = HBox(titleField).apply {
        padding = Insets(5.0)
    }

    private val groupBlock = HBox(groupField).apply {
        padding = Insets(5.0)
    }

    private val bodyDisplayBlock = HBox(bodyDisplay).apply {
        padding = Insets(5.0)
        setHgrow(this, Priority.ALWAYS)
    }

    private val editBlock = HBox(editButton).apply {
        padding = Insets(5.0)
    }

    private val  updateBlock = HBox(updateButton).apply {
        padding = Insets(5.0)
    }

    private val deleteBlock = HBox(deleteButton).apply {
        padding = Insets(5.0)
    }


    private val block = HBox(titleBlock, groupBlock, bodyDisplayBlock,
        editBlock, updateBlock, deleteBlock).apply {
        setHgrow(this, Priority.ALWAYS)
        maxWidth = Double.MAX_VALUE
        padding = Insets(10.0)
        background = Background(BackgroundFill(Color.LIGHTYELLOW, CornerRadii(5.00), Insets(5.0)))
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