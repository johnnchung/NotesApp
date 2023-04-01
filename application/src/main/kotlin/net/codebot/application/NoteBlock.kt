package net.codebot.application

import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.input.KeyCode
import javafx.scene.input.MouseEvent
import javafx.scene.layout.*
import javafx.scene.layout.HBox.setHgrow
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.FontWeight
import org.controlsfx.glyphfont.FontAwesome
import org.controlsfx.glyphfont.Glyph
import org.controlsfx.glyphfont.GlyphFontRegistry
import org.jsoup.Jsoup
import java.time.LocalDateTime
import java.util.*

// This class is how we create a note instance that goes into the model's notesList and NotesMap.
class NoteBlock(private val model: Model, title: String, group: String, body: String, time: LocalDateTime): IView {
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
        return bodyText
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
    // Convert pure text to body html text
    private fun localConversion(htmlString : String): String {
        val doc = Jsoup.parse(htmlString)

        // Find the body element using a CSS selector
        val body = doc.select("body").first()

        // Extract the text content of the body element
        val bodyContent = body?.text()
        if(bodyContent != null) {
            return bodyContent
        } else {
            return htmlString
        }
    }
    // Icons for a singular note box
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
    private val openIcon: Glyph = GlyphFontRegistry.font("FontAwesome").create(FontAwesome.Glyph.EDIT).apply {
        fontSize = 22.5
        padding = Insets(0.0, 10.0, 10.0, 0.0)
    }
    // Keep track of editing state for title and group
    private var editTitle = false
    private var editGroup = false

    // This is our title field where the title of the note is displayed.
    // If you edit this field, it will enable the update button.
    val titleField = TextField(title).apply {
        font = Font.font("System",FontWeight.BOLD, 18.0)
        alignment = Pos.CENTER_LEFT
        border = Border.EMPTY
        background = null
        maxWidth = 130.0
        isEditable = false
    }

    // This is our group field where the group of the note is displayed.
    // If you edit this field, it will enable the update button.
    val groupField = TextField(group).apply {
        font = Font.font("System", 14.0)
        alignment = Pos.CENTER_LEFT
        border = Border.EMPTY
        maxWidth = 75.0
        background = null
        isEditable = false
    }

    // This is our bodyText field where the body of the note is displayed.
    // If there is nothing in our bodyText, we will display "New Note: Please edit."
    // NOTE: Currently, we have a max character limit of 50. The text will be truncated if it's length >= 50.
    val bodyDisplay = Label().apply {
        if(pureText.isEmpty()) {
            text = "New Note: Please edit"
        } else {
            // We will take the substring up to the 3rd word to display on the front
            var endIndex = pureText.length
            if(pureText.length > 10) {
                endIndex = 5
            }
            text = pureText.substring(0, endIndex) + "...";
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
    private val deleteOpenBlock = VBox(trashIcon, openIcon).apply {
        spacing = 35.0
        padding = Insets(5.0)
    }

    // This joins all the buttons and text fields into one HBox to display.
    private val block = HBox(combineTitleGroup, deleteOpenBlock).apply {
        setHgrow(this, Priority.ALWAYS)
        setHgrow(combineTitleGroup, Priority.ALWAYS)
        maxWidth = Double.MAX_VALUE
        maxHeight = Double.MAX_VALUE
        padding = Insets(12.5)
        val color = Color.rgb(random.nextInt(96) + 160, random.nextInt(96) + 160, random.nextInt(96) + 160)
        background = Background(BackgroundFill(color, CornerRadii(15.0), Insets(10.0)))
        border = Border(BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii(25.0), BorderWidths(2.0)))
    }

    override fun update() {
        pureText = localConversion(bodyText)
        bodyDisplay.apply {
            if(pureText.isEmpty()) {
                text = "New Note: Please edit"
            } else {
                // We will take the substring up to the 3rd word to display on the front
                var endIndex = pureText.length
                if(pureText.length > 10) {
                    endIndex = 5
                }
                text = pureText.substring(0, endIndex) + "...";
            }
            padding = Insets(0.0, 0.0, 0.0, 15.0)
            setHgrow(this, Priority.ALWAYS)
        }
    }

    init {
        // This event handler for the delete will delete the note instance
        // for which the delete button was clicked.
        trashIcon.addEventHandler(MouseEvent.MOUSE_CLICKED) {
            model.deleteNote(newTitle)
        }
        openIcon.addEventHandler(MouseEvent.MOUSE_CLICKED) {
            model.updateNotesPage(newTitle, newGroup, pureText)
        }
        titleEditIcon.addEventHandler(MouseEvent.MOUSE_CLICKED) {
            titleField.apply {
                isEditable = true
                trashIcon.isDisable = true
                openIcon.isDisable = true
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
                    editTitle = !list.contains(newTitle)
                }

                // Only edit the title field again if the user hits enter
                setOnKeyPressed { event ->
                    if (event.code == KeyCode.ENTER && (editTitle || newTitle == oldTitle)) {
                        isEditable = false
                        if(!editGroup) {
                            trashIcon.isDisable = false
                            openIcon.isDisable = false
                        }
                        editTitle = false
                        model.updateNote(oldTitle, newTitle, groupField.text)
                        oldTitle = newTitle
                        modtime = LocalDateTime.now()
                    }
                }
            }
        }
        groupEditIcon.addEventHandler(MouseEvent.MOUSE_CLICKED) {
            groupField.apply {
                isEditable = true
                trashIcon.isDisable = true
                openIcon.isDisable = true
                textProperty().addListener { _, currVal, newVal ->
                    if(newVal.isNotEmpty()) {
                        oldGroup = currVal
                        newGroup = newVal
                    }
                    editGroup = true
                }
                // Only edit the group field again if the user hits enter
                setOnKeyPressed { event ->
                    if (event.code == KeyCode.ENTER) {
                        isEditable = false
                        if(!editTitle) {
                            trashIcon.isDisable = false
                            openIcon.isDisable = false
                        }
                        editGroup = false
                        model.updateNote(oldTitle, newTitle, newGroup)
                        modtime = LocalDateTime.now()
                    }
                }
            }
        }
    }
}