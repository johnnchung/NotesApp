package net.codebot.async

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage

class AsyncApplication : Application() {
    override fun start(stage: Stage) {
        val fxmlLoader = FXMLLoader(AsyncApplication::class.java.getResource("async-view.fxml"))
        val scene = Scene(fxmlLoader.load(), 320.0, 240.0)
        stage.title = "Fetch Data"
        stage.scene = scene
        stage.show()
    }
}

fun main() {
    Application.launch(AsyncApplication::class.java)
}