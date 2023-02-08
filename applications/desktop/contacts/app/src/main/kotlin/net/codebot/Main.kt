package net.codebot

import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.control.Tab
import javafx.scene.control.TabPane
import javafx.stage.Stage
import net.codebot.domain.ContactModel
import net.codebot.presentation.ContactList
import net.codebot.presentation.ContactView

class Main : Application() {
    override fun start(stage: Stage) {
        // setup model and views
        val model = ContactModel()
        val tab1 = Tab("List", ContactList(model))
        val tab2 = Tab("Add", ContactView(model))
        val tabPane = TabPane(tab1, tab2)

        // restore old data if it exists
        model.restore()

        // setup and show window
        stage.scene= Scene(tabPane, 235.0, 170.0)
        stage.title = "Contacts"
        stage.isResizable = false
        stage.setOnCloseRequest { System.exit(0) }
        stage.show()
    }
}