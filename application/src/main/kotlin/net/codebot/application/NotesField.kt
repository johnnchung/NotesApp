package net.codebot.application
import javafx.scene.web.HTMLEditor

class NotesField(private val model: Model, private val title: String) : HTMLEditor(), IView {
    init {
        model.createView(this)
    }
    override fun update() {
        if(model.savedNotes) {
            if(htmlText != "<html dir=\"ltr\"><head></head><body contenteditable=\"true\"></body></html>") {
                model.saveNotesContent(model.editedNote, htmlText)
                println(model.editedNote)
                println(htmlText)
                model.savedNotes = false
            }
        }
    }
}