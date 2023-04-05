package net.codebot.application

import javafx.scene.control.Tab
import javafx.scene.control.TabPane
import javafx.scene.layout.BorderPane

// readInput: Users
class TabPaneNotes(private val model: Model, readInput: DataClass, homePage: BorderPane): TabPane(), IView {
    override fun update() {
        if(model.openedNotes) {
            for(note in model.getNotesList()) {
                if(note.getTitle() == model.editedNote) {
                    tabs.add(Tab(note.getTitle(), model.notePage[0]).apply {
                        setOnCloseRequest {
                            model.notePage[0].notes.htmlText = ""
                        }
                    })
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