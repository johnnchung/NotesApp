package net.codebot.application

class Model {
    fun addMenuBarView(view: MenuBarView) {
        view.updateMenuBarView()
    }

    fun addNotesView(view: NotesView) {
        view.updateNotesView()
    }
}