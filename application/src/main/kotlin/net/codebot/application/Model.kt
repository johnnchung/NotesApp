package net.codebot.application

class Model {
    fun addMenuBarView(view: MenuBarView) {
        view.updateMenuBarView()
    }

    fun addToolBarView(view: ToolBarView) {
        view.updateToolBarView()
    }
}