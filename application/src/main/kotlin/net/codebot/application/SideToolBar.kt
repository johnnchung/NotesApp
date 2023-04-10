package net.codebot.application

import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.input.MouseEvent
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.FontWeight
import org.controlsfx.glyphfont.FontAwesome
import org.controlsfx.glyphfont.Glyph
import org.controlsfx.glyphfont.GlyphFontRegistry

class SideToolBar(private val model: Model): BorderPane(), IView {

    // Return create Button
    fun getCreateButton(): Glyph {
        return createButton
    }
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
        // changes theme color
        if (model.defaultDarkMode) {
            this.background = Background(BackgroundFill(Color.DARKGREY, null, null))
        } else if (!model.defaultDarkMode) {
            this.background = Background(BackgroundFill(Color.TRANSPARENT, null, null))
        }
    }

    init {
        setAlignment(this, Pos.CENTER)
        this.prefWidth = 100.0
        this.top = notesHeader
        this.center = displayCreateButton

        // We add a listener to the create button. When it is clicked, we
        // let our model know that there is a new note instance to be made only if our titleField is not empty.
        createButton.addEventHandler(MouseEvent.MOUSE_CLICKED) {
            var title = "Untitled " + "${model.getNotesList().size}"
            // checks to see if the course entered is a valid course
            for(note in model.getNotesList()) {
                if(note.getTitle() == title) {
                    var counter = 1
                    title = "Untitled " + "${model.getNotesList().size + 1}"
                    while(title in model.titleList) {
                        counter += 1
                        title = "Untitled " + "${model.getNotesList().size + counter}"
                    }
                }
            }
            model.createNote(title, "Group " + "${model.getNotesList().size}", "")
        }
        model.createView(this)
    }
}