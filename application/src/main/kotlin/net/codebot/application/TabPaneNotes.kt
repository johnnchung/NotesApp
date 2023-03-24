package net.codebot.application

import javafx.scene.control.Tab
import javafx.scene.control.TabPane
import javafx.scene.layout.BorderPane
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

// readInput: Users
class TabPaneNotes(private val model: Model, readInput: dataClass, homePage: BorderPane): TabPane(), IView {
    init {
        this.apply {
            tabs.add(Tab("Home", homePage))

             // redo Logic
//             for(i in 0 until readInput.titles.size) {
//                 model.createNote(readInput.titles.elementAt(i), readInput.groups.elementAt(i), readInput.contents.elementAt(i))
//             }

            transaction {
                for (notes in Users.selectAll()) {
                    model.createNote(notes[Users.title], notes[Users.group], notes[Users.content])
                }
            }

            val homeTab = tabs[0]
            homeTab.isClosable = false
        }
        model.createView(this)
    }

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
}