package net.codebot.application

import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.input.KeyCode
import javafx.scene.input.MouseEvent
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.CornerRadii
import javafx.scene.layout.HBox
import javafx.scene.layout.HBox.setHgrow
import javafx.scene.layout.Priority
import javafx.scene.paint.Color
import java.time.LocalDateTime

// This class is how we create a note instance that goes into  the model's notesList and NotesMap.
class Note(private val model : Model, title : String, group : String, body : String,time:LocalDateTime) {
    // We save the old title and group for update purposes.
    private var oldTitle = title
    private var oldGroup = group
    // We save the new title and group for delete purposes.
    private var newTitle = title
    private var newGroup = group
    // This is the time that was recorded when the note instance was created.
    private var modtime =  time
    // The bodyText of our note instance.
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

    // Returns the time in LocalDateTime format of when a Note instance was created or modified.
    fun getTime(): LocalDateTime? {
        return modtime
    }

    /* This is our title field where the title of the note is displayed.
        If you edit this field, it will enable the update button.
     */
    val titleField = TextField(title).apply {
        padding = Insets(5.0)
        prefWidth = 50.0
        alignment = Pos.CENTER
        val list = model.getNotesMap()
        textProperty().addListener { _, curVal, newVal ->
            updateButton.isDisable = false
            if(newVal.isNotEmpty()) {
                for (notes in list) {
                    // we look for the old title in our map, and set the title to update
                    if (curVal == notes.key) {
                        oldTitle = curVal
                    }
                }
                newTitle = newVal
            }
        }
    }

    /* This is our group field where the group of the note is displayed.
        If you edit this field, it will enable the update button.
     */
    val groupField = TextField(group).apply {
        padding = Insets(5.0)
        prefWidth = 50.0
        alignment = Pos.CENTER
        textProperty().addListener { _, currVal, newVal ->
            updateButton.isDisable = false
            if(newVal.isNotEmpty()) {
                oldGroup = currVal
                newGroup = newVal
            }
        }
    }

    /* This is our bodytext field where the body of the note is displayed.
        If there is nothing in our bodytext, we will display "New Note: Please edit."
        NOTE: Currently, we have a max character limit of 50. The text will be truncated if it's length >= 50.
     */
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

    // This is the editButton for a note instance. When clicked, it opens a new tab pane with the clicked note instance.
    private val editButton = Button("Edit").apply {
        padding = Insets(5.0)
        prefWidth = 40.0
        // TODO: Create an event listener where when the button is called, we open a new tab pane
    }

    /* This is the delete button for a note instance.
       When clicked, it deletes the note instance.
     */
    private val deleteButton = Button("Delete").apply {
        padding = Insets(5.0)
        prefWidth = 75.0
    }

    /* This is the update button for a note instance.
       When clicked and when it is enabled, it will update the note instance.
     */
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

    // This joins all the buttons and text fields into one HBox to display.
    private val block = HBox(titleBlock, groupBlock, bodyDisplayBlock,
        editBlock, updateBlock, deleteBlock).apply {
        setHgrow(this, Priority.ALWAYS)
        maxWidth = Double.MAX_VALUE
        padding = Insets(10.0)
        background = Background(BackgroundFill(Color.LIGHTYELLOW, CornerRadii(5.00), Insets(5.0)))
        // adds some keyboard functionality to udpate/edit/delete note
    }

    init {
        /* This event handler for the edit button will create a new tab pane with the note instance
           for which edit button was clicked.
         */
        editButton.addEventHandler(MouseEvent.MOUSE_CLICKED) {
            model.updateNotesPage(newTitle, newGroup, bodyText)
        }
        /* This event handler for the update button will update the title and/or group of the note instance
           for which the update button was clicked. Before we call updateNote in our model, we update
           the time of the note instance to the current time. Then, we disable the update button.
        */
        updateButton.addEventHandler(MouseEvent.MOUSE_CLICKED) {
            modtime = LocalDateTime.now()
            updateButton.isDisable = true

            model.updateNote(oldTitle, titleField.text, groupField.text)
        }
        /* This event handler for the delete button will delete the note instance
           for which the delete button was clicked.
        */
        deleteButton.addEventHandler(MouseEvent.MOUSE_CLICKED) {
            model.deleteNote(newTitle)
        }

        // After changing the title/group you can press enter to update the note
        // instead of dragging mouse over to the update button
        block.setOnKeyPressed { event ->
            if (event.code == KeyCode.ENTER) {
                if (!updateBlock.isDisable) {
                    modtime = LocalDateTime.now()
                    updateButton.isDisable = true

                    model.updateNote(oldTitle, titleField.text, groupField.text)
                }
            }
        }
    }
}