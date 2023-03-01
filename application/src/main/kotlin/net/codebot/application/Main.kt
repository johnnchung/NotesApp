package net.codebot.application

import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority
import javafx.scene.layout.VBox
import javafx.stage.Stage

class Main : Application() {
    override fun start(stage: Stage) {
        val model = Model()
        val menuBar = MenuBarClass(model).apply {
            HBox.setHgrow(this, Priority.ALWAYS)
        }

        val toolBar = ToolBarClass(model).apply {
            HBox.setHgrow(this, Priority.ALWAYS)
        }

        // TODO: Add root.middle here for the text field of our notes application
        val root = BorderPane()
        root.top = VBox(menuBar, toolBar)

        stage.apply {
            title = "Island Boys Notes Application"
            scene = Scene(root, 800.0, 600.0)
        }.show()
    }
}