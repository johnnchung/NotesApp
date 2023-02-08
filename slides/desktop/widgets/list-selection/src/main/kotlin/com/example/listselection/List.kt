package com.example.listselection

import javafx.application.Application
import javafx.beans.value.ChangeListener
import javafx.beans.value.ObservableValue
import javafx.scene.Cursor
import javafx.scene.Scene
import javafx.scene.control.ListView
import javafx.scene.control.SelectionMode
import javafx.scene.control.SelectionModel
import javafx.scene.control.SingleSelectionModel
import javafx.scene.layout.StackPane
import javafx.stage.Stage

class List : Application() {
    override fun start(stage: Stage) {
        // setup list
        val list = ListView<String>()
        list.items.addAll("apple", "banana", "cherry", "durian", "eggplant", "fig")
        list.selectionModel.selectionMode = SelectionMode.SINGLE
        val scene = Scene(StackPane(list), 275.0, 300.0)

        // handlers
        list.setOnMousePressed { event ->
            println("${list.selectionModel.selectedItems} pressed")
        }

        list.setOnDragDetected { event ->
            println("${list.selectionModel.selectedItems} moved")
            scene.cursor = Cursor.HAND
        }
        list.setOnMouseReleased { event ->
            println("${list.selectionModel.selectedItems} released")
            scene.cursor = Cursor.DEFAULT
        }

        // setup scene
        stage.title = "Fruits"
        stage.scene = scene
        stage.show()
    }
}

fun main() {
    Application.launch(List::class.java)
}