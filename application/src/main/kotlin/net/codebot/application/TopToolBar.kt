package net.codebot.application

import javafx.geometry.*
import javafx.scene.Cursor
import javafx.scene.control.*
import javafx.scene.layout.*
import javafx.scene.text.Text
import javafx.scene.text.TextAlignment
import org.controlsfx.glyphfont.FontAwesome
import org.controlsfx.glyphfont.Glyph
import org.controlsfx.glyphfont.GlyphFontRegistry

class TopToolBar(private val model: Model): VBox(), IView {
    // create components for the search/sort toolbar
    private val searchIcon: Glyph = GlyphFontRegistry.font("FontAwesome").create(FontAwesome.Glyph.SEARCH).apply {
        fontSize = 20.0
        padding = Insets(0.0, 3.0, 0.0, 10.0)
    }

    private val searchBar = TextField().apply {
        promptText = "Search Notes"
        padding = Insets(5.0)
        prefWidth = 300.0
        prefHeight = 30.0
        maxWidth = Double.MAX_VALUE
    }

    /*  We add a listener to the Menu Items. We check if there is a change in our value, then
        let our model know that there is a change of state for the model's ordering.
    */
    // TODO: Fix the alignment of the text to the left
    private val sortByTitle = MenuItem().apply {
        graphic = Text("Sort By Title").apply {
            textAlignment = TextAlignment.LEFT
        }
        setOnAction {
            model.sortNotify("Title")
        }
    }
    private val sortByDate = MenuItem().apply {
        graphic = Text("Sort By Date").apply {
            textAlignment = TextAlignment.LEFT
        }
        setOnAction {
            model.sortNotify("Date")
        }
    }
    private val groupDropdown = MenuItem().apply {
        graphic = Text("Include Grouping").apply {
            textAlignment = TextAlignment.LEFT
        }
        setOnAction {
            model.groupNotes(true)
        }
    }

    override fun update() {
    }

    private val ellipseButton: Glyph = GlyphFontRegistry.font("FontAwesome").create(FontAwesome.Glyph.ELLIPSIS_V).apply {
        fontSize = 20.0
        cursor = Cursor.HAND
        setOnMouseClicked {
            val contextMenu = ContextMenu()
            contextMenu.items.addAll(sortByTitle, sortByDate, groupDropdown)
            contextMenu.show(this, Side.BOTTOM, 0.0, 0.0)
        }
        padding = Insets(0.0, 10.0, 0.0, 10.0)
        nodeOrientation = NodeOrientation.RIGHT_TO_LEFT
    }
    private val toolBarTop = ToolBar(searchBar, searchIcon, ellipseButton).apply {
        maxWidth = Double.MAX_VALUE
        minHeight = 60.0
        HBox.setHgrow(searchBar, Priority.ALWAYS)
    }

    init {
        this.children.add(toolBarTop)
        /* We add a listener to the searchBar button. We check if there is a change in our value, then
           let our model know that there is a change of state for the model's search val.
        */
        searchBar.apply {
            textProperty().addListener { _, _, newVal ->
                model.searchQuery(newVal)
            }
        }
        model.createView(this)
    }
}