package net.codebot.application

import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.input.KeyCode
import javafx.scene.input.MouseEvent
import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox
import javafx.scene.text.Font
import javafx.scene.text.FontWeight
import org.controlsfx.glyphfont.FontAwesome
import org.controlsfx.glyphfont.Glyph
import org.controlsfx.glyphfont.GlyphFontRegistry

class SideToolBar(private val model: Model): BorderPane(), IView {
    // Create title for application
    private val notesHeader = Label("Notes").apply {
        font = Font.font("System", FontWeight.BOLD,25.0)
        padding = Insets(10.0, 10.0, 10.0, 12.5)
        alignment = Pos.TOP_CENTER
    }

    private val createButton: Glyph = GlyphFontRegistry.font("FontAwesome").create(FontAwesome.Glyph.PLUS_SQUARE_ALT).apply {
        padding = Insets(0.0, 0.0, 0.0, 5.0)
        fontSize = 40.0
    }

    private val displayCreateButton = HBox(createButton).apply {
        this.alignment = Pos.CENTER
    }

    override fun update() {
    }

    init {
        setAlignment(this, Pos.CENTER)
        this.prefWidth = 100.0
        this.top = notesHeader
        this.center = displayCreateButton
        /* We add a listener to the create button. When it is clicked, we
           let our model know that there is a new note instance to be made only if our titleField is not empty.
        */
        createButton.addEventHandler(MouseEvent.MOUSE_CLICKED) {
            // checks to see if the course entered is a valid course
            model.createNote("Untitled " + "${model.getNotesList().size}", "Group " + "${model.getNotesList().size}", "")
        }

        // Allows the user to hit enter to create the note if they do not want to move the mouse to hit the create button
        createButton.setOnKeyPressed { event ->
            if (event.code == KeyCode.ENTER) {
                model.createNote("Untitled " + "${model.getNotesList().size}", "Group " + "${model.getNotesList().size}", "")
            }
        }
    }
}