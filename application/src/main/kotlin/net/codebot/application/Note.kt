package net.codebot.application

import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.input.MouseEvent
import javafx.scene.layout.*
import javafx.scene.layout.HBox.setHgrow
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.FontWeight
import java.time.LocalDateTime
import org.controlsfx.glyphfont.Glyph
import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.GlyphFontRegistry
import java.util.*

// This class is how we create a note instance that goes into  the model's notesList and NotesMap.
class Note(private val model: Model, title: String, group: String, body: String, time: LocalDateTime) {
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
    var pureText = model.convertToPure(body)
    private val random = Random()

    // Getters and setters for note properties
    fun getTitle() : String {
        return newTitle
    }
    fun getGroup() : String {
        return newGroup
    }
    fun getContent() : String {
        return pureText
    }
    fun getBox(): HBox {
        return block
    }
    fun setContent(str: String) {
        pureText = str
    }

    // Returns the time in LocalDateTime format of when a Note instance was created or modified.
    fun getTime(): LocalDateTime? {
        return modtime
    }
    /*
        Icons for a singular note box
    */
    private val titleEditIcon: Glyph = GlyphFontRegistry.font("FontAwesome").create(FontAwesome.Glyph.PENCIL).apply {
        padding = Insets(9.5, 0.0, 0.0, 0.0)
        alignment = Pos.CENTER_LEFT
    }
    private val groupEditIcon: Glyph = GlyphFontRegistry.font("FontAwesome").create(FontAwesome.Glyph.PENCIL).apply {
        padding = Insets(6.5, 0.0, 0.0, 0.0)
        alignment = Pos.CENTER_LEFT
    }
    private val trashIcon: Glyph = GlyphFontRegistry.font("FontAwesome").create(FontAwesome.Glyph.TRASH_ALT).apply {
        fontSize = 22.5
        padding = Insets(10.0, 10.0, 0.0, 0.0)
    }

    /* This is our title field where the title of the note is displayed.
        If you edit this field, it will enable the update button.
     */
    val titleField = TextField(title).apply {
        font = Font.font("System",FontWeight.BOLD, 18.0)
        alignment = Pos.CENTER_LEFT
        border = Border.EMPTY
        background = null
        maxWidth = 130.0
        val list = model.getNotesMap()
        textProperty().addListener { _, curVal, newVal ->
            if(newVal.isNotEmpty()) {
                for (notes in list) {
                    // We look for the old title in our map, and set the title to update
                    if (curVal == notes.key) {
                        oldTitle = curVal
                    }
                }
                newTitle = newVal
            }
            model.updateNote(oldTitle, newTitle, groupField.text)
        }
    }

    /* This is our group field where the group of the note is displayed.
        If you edit this field, it will enable the update button.
     */
    val groupField = TextField(group).apply {
        font = Font.font("System", 14.0)
        alignment = Pos.CENTER_LEFT
        border = Border.EMPTY
        maxWidth = 75.0
        background = null
        textProperty().addListener { _, currVal, newVal ->
            if(newVal.isNotEmpty()) {
                oldGroup = currVal
                newGroup = newVal
            }
        }
        model.updateNote(oldTitle, newTitle, newGroup)
    }

    /* This is our bodyText field where the body of the note is displayed.
        If there is nothing in our bodyText, we will display "New Note: Please edit."
        NOTE: Currently, we have a max character limit of 50. The text will be truncated if it's length >= 50.
     */
    val bodyDisplay = Label().apply {
        text = pureText.ifEmpty {
            "New Note: Please edit"
        }
        padding = Insets(0.0, 0.0, 0.0, 15.0)
        setHgrow(this, Priority.ALWAYS)
    }


    // create HBox for each of the fields
    private val bodyDisplayBlock = HBox(bodyDisplay).apply {
        padding = Insets(0.0, 0.0, 5.0, -5.0)
    }
    private val titleFieldBlock = HBox(titleField, titleEditIcon)
    private val groupFieldBlock = HBox(groupField, groupEditIcon)

    private val combineTitleGroup = VBox(titleFieldBlock, groupFieldBlock, bodyDisplayBlock).apply {
        alignment = Pos.CENTER_LEFT
    }
    private val deleteBlock = HBox(trashIcon).apply {
        padding = Insets(5.0)
    }

    // This joins all the buttons and text fields into one HBox to display.
    private val block = HBox(combineTitleGroup, deleteBlock).apply {
        setHgrow(this, Priority.ALWAYS)
        setHgrow(combineTitleGroup, Priority.ALWAYS)
        maxWidth = Double.MAX_VALUE
        maxHeight = Double.MAX_VALUE
        padding = Insets(12.5)
        val color = Color.rgb(random.nextInt(96) + 160, random.nextInt(96) + 160, random.nextInt(96) + 160)
        background = Background(BackgroundFill(color, CornerRadii(15.0), Insets(10.0)))
        border = Border(BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii(25.0), BorderWidths(2.0)))
        setOnMouseClicked {
            model.updateNotesPage(newTitle, newGroup, pureText)
        }
    }

    init {
        /* This event handler for the delete button will delete the note instance
           for which the delete button was clicked.
        */
        trashIcon.addEventHandler(MouseEvent.MOUSE_CLICKED) {
            model.deleteNote(newTitle)
        }
    }
}