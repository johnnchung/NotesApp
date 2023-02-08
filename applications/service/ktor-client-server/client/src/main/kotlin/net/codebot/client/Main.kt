package net.codebot.client

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.scene.layout.StackPane
import javafx.stage.Stage
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import net.codebot.shared.SysInfo

class Main : Application() {
    override fun start(stage: Stage) {
        var result: String = "empty"
        runBlocking {
            launch {
                result = query()
            }
        }
        stage.scene = Scene(StackPane(Label(result)), 250.0, 150.0)
        stage.isResizable = false
        stage.title = "Ktor-client-server"
        stage.show()
    }
}

suspend fun query(): String {
    val client = HttpClient(CIO)
    val response: HttpResponse = client.get("https://ktor.io/")
    client.close()
    return("${SysInfo.hostname}: ${response.status}")
}