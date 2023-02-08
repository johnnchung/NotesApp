package com.example.dragdrop

import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.layout.Pane
import javafx.scene.text.Text
import javafx.stage.Stage

class DragDrop : Application() {
    override fun start(stage: Stage) {
        // create items to show
        val apple = Text("Apple")
        apple.translateX = 50.0
        apple.translateY = 50.0

        val banana = Text("Banana")
        banana.translateX = 100.0
        banana.translateY = 100.0

        val cherry = Text("Cherry")
        cherry.translateX = 150.0
        cherry.translateY = 150.0

        // setup the scene graph
        val root = Pane()
        root.children.addAll(apple, banana, cherry)

        // make each node draggable, original Java code here
        // https://www.youtube.com/watch?v=YaDkj-bqcj8

        root.children.forEach {node ->
            var startX = 0.0
            var startY = 0.0

            node.setOnMousePressed { event ->
                startX = event.sceneX - node.translateX // translateX is the mouse x-value
                startY = event.sceneY - node.translateY // translateY is the mouse y-value
            }

            node.setOnMouseDragged { event ->
                node.translateX = event.sceneX - startX
                node.translateY = event.sceneY - startY
            }
        }

        // setup and show the scene
        stage.scene = Scene(root, 275.0, 300.0)
        stage.title = "Drag-Drop"
        stage.isResizable = false
        stage.show()
    }
}
