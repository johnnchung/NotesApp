package net.codebot.application
import javafx.scene.layout.VBox

class NoteView(private val model: Model): VBox(), IView {
    override fun update() {
        this.children.clear()
        var notelist = model.getNotesList()
        notelist.forEach {
            this.children.add(it.getBox())
        }
    }
    init {
        model.createView(this)
    }
}