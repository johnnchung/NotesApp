package net.codebot.application
import javafx.application.Application
import javafx.application.Platform
import javafx.scene.Scene
import javafx.scene.control.ScrollPane
import javafx.scene.input.KeyCode
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
                     val contents : List<String>, val darkMode: Boolean,
                     val autoSave: Boolean) {}

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
        model.defaultDarkMode = notesData.darkMode
        model.autoSave = notesData.autoSave

        val notesView = NoteView(model).apply {
            prefHeight = height - 90.0
        }

        val toolBarTop = TopToolBar(model, notesData).apply {
            HBox.setHgrow(this, Priority.ALWAYS)
        }
        val toolBarLeft = SideToolBar(model)
        val homePage = BorderPane().apply {
            center = ScrollPane(notesView).apply{
                isFitToWidth = true
                setOnKeyPressed { event ->
                    if (event.isShortcutDown && event.code == KeyCode.N) {
                        var title = "Untitled " + "${model.getNotesList().size}"
                        // checks to see if the course entered is a valid course
                        for(note in model.getNotesList()) {
                            if(note.getTitle() == title) {
                                var counter = 1
                                title = "Untitled " + "${model.getNotesList().size + 1}"
                                while(title in model.titleList) {
                                    counter += 1
                                    title = "Untitled " + "${model.getNotesList().size + counter}"
                                }
                            }
                        }
                        model.createNote(title, "Group " + "${model.getNotesList().size}", "")
                    }
                }
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

        // since our TabPane only gets initialized after stage.show, we check for dark mode in the main
        notesDisplay.apply {
            val header = this.lookup(".tab-header-area")
            if (notesData.darkMode && header != null) {
                val background = header.lookup(".tab-header-background")
                background.style = "-fx-background-color: #A9A9A9;"
            }
        }

        stage.heightProperty().addListener { _, _, newHeight ->
            height = newHeight.toDouble()
            notesView.prefHeight = newHeight.toDouble() - 90.0
        }

        stage.widthProperty().addListener { _, _, newWidth ->
            width = newWidth.toDouble()
        }
        stage.setOnCloseRequest {
            try {
                if(model.autoSave) {
                    model.saveNotes()
                }
                // When the application closes, push new changes made to our applications data into JSON file
                val dataClassWhole = DataClass(width, height, stage.x, stage.y, model.titleList, model.groupList,
                    model.contentList, model.defaultDarkMode, model.autoSave)
                model.put(dataClassWhole)
            } catch (e: Exception) {
                Platform.exit()
            }
        }
    }
}