package net.codebot.application
import javafx.application.Application
import javafx.application.Platform
import javafx.scene.Scene
import javafx.scene.control.ScrollPane
import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority
import javafx.stage.Stage
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

// Localized schema to store data in JSON
@Serializable
data class DataClass(val width : Double, val height : Double,
                     val xCoord : Double, val yCoord : Double,
                     val titles : List<String>, val groups : List<String>,
                     val contents : List<String>) {}

class Main : Application() {
    override fun start(stage: Stage) {
        val model = Model()

        // Read JSON file from a GET request the moment the application is started
        val getNotes = model.get()
        val notesData = Json.decodeFromString<DataClass>(getNotes)
        var height = notesData.height
        var width =  notesData.width
        var stageX =  notesData.xCoord
        var stageY =  notesData.yCoord
        val notesView = NoteView(model)

        val toolBarTop = TopToolBar(model).apply {
            HBox.setHgrow(this, Priority.ALWAYS)
        }
        val toolBarLeft = SideToolBar(model)
        val homePage = BorderPane().apply {
            center = ScrollPane(notesView).apply{
                isFitToWidth = true
            }
            HBox.setHgrow(this, Priority.ALWAYS)
        }
        val notesDisplay = TabPaneNotes(model, notesData, homePage)
        val rightBorderPane = BorderPane().apply {
            top = toolBarTop
            center = notesDisplay
        }

        val root = BorderPane().apply {
            left = toolBarLeft
            center = rightBorderPane
        }

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
                // When the application closes, push new changes made to our applications data into JSON file
                val dataClassWhole = DataClass(width, height, stage.x, stage.y, model.titleList, model.groupList, model.contentList)
                model.put(dataClassWhole)
            } catch (e: Exception) {
                Platform.exit()
            }
        }
    }
}