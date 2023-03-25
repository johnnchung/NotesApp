package net.codebot.application

import javafx.scene.input.KeyCode
import javafx.scene.web.HTMLEditor

class NotesField(private val model: Model, private val title: String) : HTMLEditor(), IView {
    override fun update() {
        if(model.savedNotes) {
            if(htmlText != "<html dir=\"ltr\"><head></head><body contenteditable=\"true\"></body></html>") {
                model.saveNotesContent(model.editedNote, htmlText)
                model.savedNotes = false
            }
        }
    }

    init {
        for(note in model.getNotesList()) {
            if(note.getTitle() == title) {
                this.htmlText = note.bodyText
            }
        }
        model.createView(this)

        // Allows the user to use CTRL + S to save the note content
        this.setOnKeyPressed { event ->
            if (event.isShortcutDown && event.code == KeyCode.S) {
                model.saveNotes()
            }
        }
    }
}