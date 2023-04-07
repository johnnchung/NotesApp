package net.codebot.application

import javafx.scene.control.Tab
import javafx.scene.control.TabPane
import javafx.scene.layout.BorderPane

// readInput: Users
class TabPaneNotes(private val model: Model, readInput: DataClass, homePage: BorderPane): TabPane(), IView {
    override fun update() {
        // checks for the dark mode theme and sets the colors
        val header = this.lookup(".tab-header-area")
        if (model.defaultDarkMode && header != null) {
            val background = header.lookup(".tab-header-background")
            background.style = "-fx-background-color: #A9A9A9;"
        } else {
            if (header != null) {
                val background = header.lookup(".tab-header-background")
                background.style = "-fx-background-color: lightgrey;"
            }
        }
        if(model.openedNotes) {
            for(note in model.getNotesList()) {
                if(note.getTitle() == model.editedNote) {
                    val existingTab = tabs.find { it.text == note.getTitle() }
                    if (existingTab != null) {
                        // If the tab already exists, select it and update its content
                        selectionModel.select(existingTab)
                        model.notePage[0].notes.htmlText = note.getContent()
                    } else if(tabs.size < 2) {
                        // If the tab doesn't exist, create a new tab and add it to the TabPane
                        val newTab = Tab(note.getTitle(), model.notePage[0]).apply {
                            setOnCloseRequest {
                                if(model.autoSave) {
                                    model.saveNotes()
                                }
                                model.notePage[0].notes.htmlText = ""
                            }
                        }
                        tabs.add(newTab)
                        // Select the new tab
                        selectionModel.select(newTab)
                    }
                }
            }
            model.openedNotes = false
        }
    }


    init {
        this.apply {
            tabs.add(Tab("Home", homePage))
             for(i in 0 until readInput.titles.size) {
                 model.createNote(readInput.titles.elementAt(i), readInput.groups.elementAt(i), readInput.contents.elementAt(i))
             }
            val homeTab = tabs[0]
            homeTab.isClosable = false
        }
        model.createView(this)
    }
}