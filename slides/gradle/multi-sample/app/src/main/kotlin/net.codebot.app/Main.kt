package net.codebot.app

import javafx.application.Application
import javafx.stage.Stage
import javafx.scene.control.Label
import javafx.scene.layout.StackPane
import javafx.scene.Scene
import net.codebot.lib.SysInfo

class Main() : Application() {
    override fun start(stage: Stage) {
        val scene = Scene(StackPane(Label("Hello ${SysInfo.userName}")), 300.0, 200.0)
        stage.scene = scene
        stage.title = "JavaFX Sample"
        stage.show()
    }
}