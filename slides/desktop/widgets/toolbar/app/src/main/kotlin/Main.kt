import javafx.application.Application
import javafx.application.Platform
import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.ToolBar
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.stage.Stage

class Main : Application() {
    override fun start(stage: Stage) {
        val toolbar = ToolBar()

        val newButton = Button("New")
        newButton.graphic = ImageView(Image("closed.png"))
        newButton.setOnAction { println("New") }

        val openButton = Button("Open")
        openButton.graphic = ImageView(Image("open.png"))
        openButton.setOnAction { println("Open") }

        val quitButton = Button("Quit")
        quitButton.graphic = ImageView(Image("quit.png"))
        quitButton.setOnAction {
            println("Quit")
            Platform.exit()
        }

        toolbar.items.addAll(newButton, openButton, quitButton)
        stage.scene = Scene(Group(toolbar), 800.0, 400.0)
        stage.show()
    }
}