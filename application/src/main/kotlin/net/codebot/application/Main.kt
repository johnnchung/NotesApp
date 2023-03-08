package net.codebot.application

import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.control.ScrollPane
import javafx.scene.control.Tab
import javafx.scene.control.TabPane
import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority
import javafx.scene.layout.VBox
import javafx.stage.Stage
import javafx.scene.web.HTMLEditor

class Main : Application() {
    override fun start(stage: Stage) {
        val model = Model()
        val menuBar = MenuBarClass(model).apply {
            HBox.setHgrow(this, Priority.ALWAYS)
        }

        val toolbar = ToolBarClass(model).apply {
            HBox.setHgrow(this, Priority.ALWAYS)
        }

        val notes = NotesField(model)
        val notesView = NoteView(model)
        val notePage = BorderPane().apply {
            top = VBox(menuBar)
            center = notes
        }

        val homePage = BorderPane().apply {
            top = toolbar
            center = ScrollPane(notesView)
        }

        // TODO: Add root.middle here for the text field of our notes application
        val root = TabPane().apply {
            tabs.add(Tab("Home",homePage))
            tabs.add(Tab("Note", notePage))

        }

        stage.apply {
            title = "Island Boys Notes Application"
            scene = Scene(root, 800.0, 600.0)
        }.show()
    }
}