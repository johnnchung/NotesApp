package net.codebot.application

import javafx.scene.input.KeyCode
import javafx.scene.web.HTMLEditor

class NotesTextEditor(private val model: Model, private val title: String) : HTMLEditor(), IView {
    override fun update() {
        if(model.savedNotes) {
            if(htmlText != "<html dir=\"ltr\"><head></head><body contenteditable=\"true\"></body></html>") {
                model.saveNotesContent(model.editedNote, htmlText)
                model.savedNotes = false
            }
        }
    }

    init {
        // Tap into css styling of HTMLEditor to set a pref height
        this.lookup(".web-view").style = "-fx-pref-height: 1000;"
        this.heightProperty().addListener {_, _, newHeight ->
            minHeight = newHeight.toDouble()
        }
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