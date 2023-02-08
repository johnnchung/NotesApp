package com.example.dragdroptargets

import javafx.application.Application
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.scene.input.ClipboardContent
import javafx.scene.input.DragEvent
import javafx.scene.input.MouseEvent
import javafx.scene.input.TransferMode
import javafx.scene.layout.Pane
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import javafx.stage.Stage

// Drag-drop example using Dragboard
// Converted and modified from Java sample here:
// https://jenkov.com/tutorials/javafx/drag-and-drop.html

class DragAndDropExample : Application() {
    override fun start(primaryStage: Stage) {
        val label = Label("Drag from left to right circle")
        label.translateX = 125.0
        label.translateY = 175.0

        val source = createCircle("#ff00ff", "#ff88ff", 100.0)
        source.onDragDetected = EventHandler { event: MouseEvent? ->
            println("Circle 1 drag detected")
            val db = source.startDragAndDrop(*TransferMode.ANY)
            val content = ClipboardContent()
            content.putString("Circle source text")
            db.setContent(content)
        }
        source.onMouseDragged = EventHandler { event: MouseEvent ->
            event.isDragDetect = true
        }

        val target = createCircle("#00ffff", "#88ffff", 300.0)
        target.onDragOver = EventHandler { event ->
            if (event.gestureSource !== target && event.dragboard.hasString()) {
                event.acceptTransferModes(*TransferMode.COPY_OR_MOVE)
            }
            event.consume()
        }
        target.onDragDropped = EventHandler { event: DragEvent ->
            val db = event.dragboard
            if (db.hasString()) {
                println("Dropped: " + db.string)
                event.isDropCompleted = true
            } else {
                event.isDropCompleted = false
            }
            event.consume()
        }

        val pane = Pane()
        pane.children.addAll(label, source, target)

        val scene = Scene(pane, 400.0, 225.0, true)
        primaryStage.scene = scene
        primaryStage.title = "Drag-Drop Dragboard"
        primaryStage.isResizable = false
        primaryStage.show()
    }

    private fun createCircle(strokeColor: String, fillColor: String, x: Double): Circle {
        val circle = Circle()
        circle.centerX = x
        circle.centerY = 85.0
        circle.radius = 50.0
        circle.stroke = Color.valueOf(strokeColor)
        circle.strokeWidth = 5.0
        circle.fill = Color.valueOf(fillColor)
        return circle
    }
}