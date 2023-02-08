package net.codebot.mvc

import javafx.application.Application
import javafx.scene.Scene
import javafx.stage.Stage

class Main : Application() {
    override fun start(stage: Stage?) {
        val view = View()
        val model = Model()
        model.addObserver(view)

        val scene = Scene(view, 400.0, 250.0)
        stage?.scene = scene
        stage?.title = "Demo"
        stage?.width = 500.0
        stage?.height = 400.0
        stage?.show()
    }
}