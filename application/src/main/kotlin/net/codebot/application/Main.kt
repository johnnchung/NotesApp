package net.codebot.application

import javafx.application.Application
import javafx.application.Platform
import javafx.scene.Scene
import javafx.scene.control.ScrollPane
import javafx.scene.control.Tab
import javafx.scene.control.TabPane
import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority
import javafx.stage.Stage
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import java.io.FileInputStream
import java.io.File
import java.io.FileOutputStream
import java.io.PrintWriter
import java.security.spec.ECField

@Serializable
data class dataClass(val width : Double, val height : Double,
                      val xCoord : Double, val yCoord : Double,
                      val titles : List<String>, val groups : List<String>, val contents : List<String>) {}


class Main : Application() {
    override fun start(stage: Stage) {
        val model = Model()

        val toolbar = ToolBarClass(model).apply {
            HBox.setHgrow(this, Priority.ALWAYS)
        }

        var fileInput = FileInputStream("data.json")
        val readInput = Json.decodeFromStream<dataClass>(fileInput)
        var height = readInput.height
        var width = readInput.width
        var stageX = readInput.xCoord
        var stageY = readInput.yCoord
        val notesView = NoteView(model)

        val homePage = BorderPane().apply {
            top = toolbar
            center = ScrollPane(notesView).apply{
                isFitToWidth = true
            }
        }

        // TODO: Add root.middle here for the text field of our notes application
        val root = TabPaneNotes(model, readInput, homePage)

        stage.apply {
            title = "Island Boys Notes Application"
            scene = Scene(root, width, height)
            stage.x = stageX
            stage.y = stageY
        }.show()

        stage.heightProperty().addListener { _, _, newHeight ->
            height = newHeight.toDouble()
        }

        stage.widthProperty().addListener { _, _, newWidth ->
            width = newWidth.toDouble()
        }

        stage.setOnCloseRequest {
            try {
                val path = System.getProperty("user.dir") + "\\data.json"
                val file = FileOutputStream("data.json")
                val dataClassWhole = dataClass(width, height, stage.x, stage.y, model.titleList, model.groupList, model.contentList)
                val string = Json.encodeToString(dataClassWhole)
                file.write(string.toByteArray())
            } catch (e: Exception) {
                Platform.exit()
            }
        }
    }
}