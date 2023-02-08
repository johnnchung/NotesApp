import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.layout.StackPane
import javafx.scene.text.Text
import javafx.stage.Stage

fun main() {
    Application.launch(HelloFX::class.java)
}


class HelloFX : Application() {
    override fun init() {
        super.init()
    }

    override fun start(stage: Stage) {
        val text = Text("Hello FX!")
        val root = StackPane(text)
        val scene = Scene(root, 320.0, 240.0)
        stage.title = "Hello!"
        stage.scene = scene
        stage.show()
    }

    override fun stop() {
        super.stop()
    }
}