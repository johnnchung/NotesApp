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
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import java.io.FileInputStream
import java.io.FileOutputStream
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

@Serializable
data class dataClass(val width : Double, val height : Double,
                      val xCoord : Double, val yCoord : Double,
                      val titles : List<String>, val groups : List<String>, val contents : List<String>) {}


class Main : Application() {
    override fun start(stage: Stage) {
        val model = Model()

        // Json reading
        var fileInput = FileInputStream("data.json")
        val readInput = Json.decodeFromStream<dataClass>(fileInput)
        var height = -1.0 // readInput.height
        var width =  -1.0// readInput.width
        var stageX =  -1.0// readInput.xCoord
        var stageY =  -1.0// readInput.yCoord
        val notesView = NoteView(model)

        // Database reading
        var conn: Connection? = null
        fun connect() {
            try {
                val url = "jdbc:sqlite:ProjectDatabase.db"
                conn = DriverManager.getConnection(url)
                Database.connect(url)
                println("Connection established.")
            } catch (e: SQLException) {
                println(e.message)
            }
        }
        connect()

        if(conn != null) {
            transaction {
                height = screenData.select{screenData.id eq 1}.single()[screenData.heightVal]
                width = screenData.select{screenData.id eq 1}.single()[screenData.widthVal]
                stageX = screenData.select{screenData.id eq 1}.single()[screenData.xCoordVal]
                stageY = screenData.select{screenData.id eq 1}.single()[screenData.yCoordVal]
            }
        }

        val toolbar = ToolBarClass(model).apply {
            HBox.setHgrow(this, Priority.ALWAYS)
        }

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
                //val path = System.getProperty("user.dir") + "\\data.json"
                val file = FileOutputStream("data.json")
                val dataClassWhole = dataClass(width, height, stage.x, stage.y, model.titleList, model.groupList, model.contentList)
                val string = Json.encodeToString(dataClassWhole)
                file.write(string.toByteArray())

                transaction {
                    if(screenData.selectAll().count() == 0.0.toLong()) {
                        screenData.insert {
                            println("Create new instance of data")
                            it[widthVal] = width
                            it[heightVal] = height
                            it[xCoordVal] = stage.x
                            it[yCoordVal] = stage.y
                        }
                    } else {
                        println("Update Data Only")
                        screenData.update({screenData.id eq 1}) {
                            it[widthVal] = width
                            it[heightVal] = height
                            it[xCoordVal] = stage.x
                            it[yCoordVal] = stage.y
                        }
                    }

                    SchemaUtils.drop(Users)
                    SchemaUtils.create(Users)
                    for (notes in model.getNotesList()) {
                        val matchingUser = Users.select { Users.title eq notes.getTitle() }.firstOrNull()
                        if (matchingUser == null) {
                            Users.insert {
                                it[title] = notes.getTitle()
                                it[group] = notes.getGroup()
                                it[content] = notes.getContent()
                            }
                            println("Inserted")
                        } else {
                            Users.update({ Users.title eq notes.getTitle() }) {
                                it[group] = notes.getGroup()
                                it[content] = notes.getContent()
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                Platform.exit()
            }
        }
    }
}