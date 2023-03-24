package net.codebot.application

import javafx.geometry.*
import javafx.scene.control.*
import javafx.scene.input.KeyCode
import javafx.scene.input.MouseEvent
import javafx.scene.layout.*
import org.controlsfx.glyphfont.FontAwesome
import org.controlsfx.glyphfont.Glyph
import org.controlsfx.glyphfont.GlyphFontRegistry

class ToolBarClass(private val model: Model): VBox(), IView {
    // Create all the components for note creation toolbar
    private val noteTitle = TextField().apply {
        padding = Insets(5.0)
        maxWidth = 100.0
        alignment = Pos.CENTER
    }

    private val group = TextField().apply {
        padding = Insets(5.0)
        maxWidth = 100.0
        alignment = Pos.CENTER
    }

    private val createButton: Glyph = GlyphFontRegistry.font("FontAwesome").create(FontAwesome.Glyph.PLUS_CIRCLE).apply {
        padding = Insets(0.0, 0.0, 0.0, 5.0)
        fontSize = 20.0
    }

    private val toolbar1 = ToolBar(Label("Note Title:"), noteTitle, Separator(), Label("Group Name:"), group, createButton)

    // create components for the search/sort toolbar
    private val sortLabel = Label("Sort by:").apply {
        padding = Insets(10.0)
    }

    private val sortBy = ChoiceBox<String>().apply {
        items.addAll("Title", "Date")
        value = items[0]
    }

    private val searchLabel = Label("Search:").apply {
        padding = Insets(10.0)
    }

    private val searchBar = TextField().apply {
        padding = Insets(5.0)
        prefWidth = 300.0
        maxWidth = Double.MAX_VALUE
    }

    private val includeGrouping = CheckBox("Include Grouping").apply {
        isSelected = false
        padding = Insets(10.0)
    }

    private val toolbar2 = ToolBar(sortLabel, sortBy, Separator(), searchLabel, searchBar,
                                   Separator(), includeGrouping).apply {
        maxWidth = Double.MAX_VALUE
        minHeight = 60.0
        HBox.setHgrow(searchBar, Priority.ALWAYS)
    }

    override fun update() {
    }

    init {
        this.children.addAll(toolbar1, toolbar2)

        /*  We add a listener to the sortBy button. We check if there is a change in our value, then
            let our model know that there is a change of state for the model's default sortoption.
         */
        sortBy.apply {
            valueProperty().addListener {_,_,newVal ->
                model.sortNotify(newVal)
            }
        }
        /*  We add a listener to the Grouping button. We check if there is a change in our value, then
            let our model know that there is a change of state for the model's defaultgroup value.
        */
        includeGrouping.apply{
            selectedProperty().addListener{_,_,newVal ->
                model.groupNotes(newVal)
            }
        }
        /* We add a listener to the searchBar button. We check if there is a change in our value, then
           let our model know that there is a change of state for the model's searchval.
        */
        searchBar.apply {
            textProperty().addListener { _, _, newVal ->
                model.searchQuery(newVal)
            }
        }

        /* We add a listener to the create button. When it is clicked, we
           let our model know that there is a new note instance to be made only if our titleField is not empty.
        */
        createButton.addEventHandler(MouseEvent.MOUSE_CLICKED) {
            // checks to see if the course entered is a valid course
            if (noteTitle.text.isNotEmpty()) {
                model.createNote(noteTitle.text, group.text, "")
            }
        }

        // allows the user to hit enter to create the note if they do not want to move the mouse to hit the create button
        toolbar1.setOnKeyPressed { event ->
            if (event.code == KeyCode.ENTER) {
                if (noteTitle.text.isNotEmpty()) {
                    model.createNote(noteTitle.text, group.text, "")
                }
            }
        }

        model.createView(this)
    }
}