package net.codebot.application

import javafx.event.EventHandler
import javafx.geometry.*
import javafx.scene.control.*
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.input.MouseEvent
import javafx.scene.layout.*
import javafx.scene.paint.*
import javafx.util.Duration

class ToolBarClass(private val model: Model): VBox(), IView {

    // create all the components for note creation toolbar
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

    private val createButton = Button("Create").apply {
        padding = Insets(5.0)
        prefWidth = 50.0
    }

    private val toolbar1 = ToolBar(Label("Note Title:"), noteTitle, Separator(), Label("Group Name:"), group, createButton)

    // create components for the search/sort toolbar
    private val sortLabel = Label("sort by:").apply {
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

        createButton.addEventHandler(MouseEvent.MOUSE_CLICKED) {
            // checks to see if the course entered is a valid course
            if (noteTitle.text.isNotEmpty()) {
                model.createNote(noteTitle.text, group.text)
            }
        }

        model.createView(this)
    }
}