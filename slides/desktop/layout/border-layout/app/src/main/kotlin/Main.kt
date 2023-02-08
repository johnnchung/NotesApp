import javafx.application.Application
import javafx.scene.Node
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox
import javafx.stage.Stage

class Main : Application() {
    override fun start(stage: Stage) {

        // top section: toolbar
        val toolBar = ToolBar(
            Button("New"),
            Button("Open"),
            Button("Save")
        )

        // side: tree view
        val tree = FolderView().build()

        // center: text entry area
        val text = TextArea()
        text.isWrapText = true
        text.text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris " +
                "nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in " +
                "reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla " +
                "pariatur. Excepteur sint occaecat cupidatat non proident, sunt in " +
                "culpa qui officia deserunt mollit anim id est laborum."
        val center = HBox(text)
        center.setOnMouseMoved { println(it.x.toString() + "," + it.y)}

        // bottom: status bar
        val label = Label("Copyright (c) 2021 Jeff Avery")
        val checkmark: Node = ImageView(Image(javaClass.getResourceAsStream("check.png")))
        val status = HBox(checkmark, label)

        // setup the scene
        val border = BorderPane()
        border.top = toolBar
        border.left = tree
        border.center = center
        border.bottom = status
        val scene = Scene(border)

        // setup and show the window
        stage.title = "BorderPane Demo"
        stage.isResizable = true

        stage.width = 640.0
        stage.minWidth = 512.0
        stage.maxWidth = 768.0

        stage.height = 480.0
        stage.minHeight = 384.0
        stage.maxHeight = 576.0

        stage.scene = scene
        stage.show()
    }
}