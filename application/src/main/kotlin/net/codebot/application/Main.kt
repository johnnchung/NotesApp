package net.codebot.application

import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.scene.layout.StackPane
import javafx.stage.Stage
import net.codebot.shared.SysInfo

class Main : Application() {
    override fun start(stage: Stage) {
        stage.scene = Scene(
            StackPane(Label("Hello ${SysInfo.userName}")),
            800.0,
            600.0)
        stage.title = "Island Boys Notes App"
        stage.show()
    }
}