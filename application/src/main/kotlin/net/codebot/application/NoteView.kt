package net.codebot.application

import javafx.scene.layout.HBox
import javafx.scene.layout.VBox

class NoteView(private val model: Model): VBox()  ,IView{
    private var courselist = VBox()
    init {
        model.createView(this)
    }

    override fun update() {
        this.children.clear()
        var notelist = model.getNoteslist()
        notelist.forEach {
            this.children.add(it.getBox())
        }

    }


}