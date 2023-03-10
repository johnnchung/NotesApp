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
    private var oldTitle = title
    private var newTitle = title
    private var newGroup = group
    var bodyText = body

    // Getters for note properties
    fun getTitle() : String {
        return newTitle
    }
    fun getGroup() : String {
        return newGroup
    }
    fun getContent() : String {
        return bodyText
    }
    fun getBox(): HBox {
        return block
    }

    val titleField = TextField(title).apply {
        padding = Insets(5.0)
        prefWidth = 50.0
        alignment = Pos.CENTER
        textProperty().addListener { _, _, newVal ->
            updateButton.isDisable = false
            if(newVal.isNotEmpty()) {
                newTitle = newVal
            }
        }
    }

    val groupField = TextField(group).apply {
        padding = Insets(5.0)
        prefWidth = 50.0
        alignment = Pos.CENTER
        textProperty().addListener { _, _, newVal ->
            updateButton.isDisable = false
            if(newVal.isNotEmpty()) {
                newGroup = newVal
            }
        }
    }

    val bodyDisplay = Label().apply {
        text = if (text.length >= 50) {
            text.substring(0,50)
        } else if (text.isEmpty()){
            "New Note: Please edit"
        } else {
            text
        }
        setHgrow(this, Priority.ALWAYS)
    }

    private val editButton = Button("Edit").apply {
        padding = Insets(5.0)
        prefWidth = 40.0
        // TODO: Create an event listener where when the button is called, we open a new tab pane
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

    // create hBox for each of the fields
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

    init {
        editButton.addEventHandler(MouseEvent.MOUSE_CLICKED) {
            model.updateNotesPage(newTitle, newGroup, bodyText)
        }

        updateButton.addEventHandler(MouseEvent.MOUSE_CLICKED) {
            updateButton.isDisable = true
            model.updateNote(oldTitle, titleField.text, groupField.text)
        }

        deleteButton.addEventHandler(MouseEvent.MOUSE_CLICKED) {
            model.deleteNote(title)
        }
    }
}