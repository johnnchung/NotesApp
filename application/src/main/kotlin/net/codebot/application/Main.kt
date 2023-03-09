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
import javafx.scene.layout.VBox
import javafx.stage.Stage
import javafx.scene.web.HTMLEditor
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectOutputStream
import java.io.File

@Serializable
data class dataClass(val width : Double, val height : Double,
                      val xCoord : Double, val yCoord : Double,
                      val titles : List<String>, val groups : List<String>, val contents : List<String>) {}


class Main : Application() {
    override fun start(stage: Stage) {

        val model = Model()
        val menuBar = MenuBarClass(model).apply {
            HBox.setHgrow(this, Priority.ALWAYS)
        }

        val toolbar = ToolBarClass(model).apply {
            HBox.setHgrow(this, Priority.ALWAYS)
        }

        var fileInput = FileInputStream("data.json")
        val readInput = Json.decodeFromStream<dataClass>(fileInput)
        var height = readInput.height
        var width = readInput.width
        var stageX = readInput.xCoord
        var stageY = readInput.yCoord


        val notes = NotesField(model)
        val notesView = NoteView(model)
        val notePage = BorderPane().apply {
            top = VBox(menuBar)
            center = notes
        }

        val homePage = BorderPane().apply {
            top = toolbar
            center = ScrollPane(notesView).apply{
                isFitToWidth = true
            }
        }

        // TODO: Add root.middle here for the text field of our notes application
        val root = TabPane().apply {
            tabs.add(Tab("Home",homePage))
            for(i in 0 until readInput.titles.size) {
//                model.titleList.add(readInput.titles.elementAt(i))
//                model.groupList.add(readInput.groups.elementAt(i))
//                model.contentList.add(readInput.contents.elementAt(i))
                //tabs.add(Tab(readInput.titles.elementAt(i)))
                model.createNote(readInput.titles.elementAt(i), readInput.groups.elementAt(i))
            }
        }

        stage.apply {
            title = "Island Boys Notes Application"
            scene = Scene(root, width.toDouble(), height.toDouble())
            stage.x = stageX.toDouble()
            stage.y = stageY.toDouble()
        }.show()

        stage.heightProperty().addListener { _, _, newHeight ->
            height = newHeight.toDouble()
        }

        stage.widthProperty().addListener { _, _, newWidth ->
            width = newWidth.toDouble()
        }

        stage.setOnCloseRequest {
            val file = File("data.json")
            file.writeText("")
            val dataClassWhole = dataClass(width, height, stage.x, stage.y, model.titleList, model.groupList, model.contentList)
            val string = Json.encodeToString(dataClassWhole)
            file.appendText(string)
        }
    }
}