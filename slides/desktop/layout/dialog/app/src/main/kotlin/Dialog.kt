import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.TextArea
import javafx.scene.layout.Background
import javafx.scene.layout.Pane
import javafx.stage.Stage
import kotlin.system.exitProcess

class OKDialog : Application() {

    @Throws(Exception::class)
    override fun start(stage: Stage) {
        val text = TextArea("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.")
        text.isWrapText = true
        text.prefWidth = 280.0
        text.prefHeight = 125.0
        text.relocate(10.0, 10.0)
        text.isEditable = false
        text.background = Background.EMPTY

        for (n in text.childrenUnmodifiable) {
            text.background = Background.EMPTY
        }

        val ok = Button("Ok")
        ok.prefWidth = 75.0
        ok.relocate(130.0, 155.0)
        ok.setOnAction {
            println("ok")
            exitProcess(0)
        }

        val cancel = Button("Cancel")
        cancel.prefWidth = 75.0
        cancel.relocate(215.0, 155.0)
        cancel.setOnAction {
            println("cancel")
            exitProcess(0)
        }

        val scene = Scene(Pane(text, ok, cancel), 300.0, 200.0)
        stage.scene = scene
        stage.title = "Dialog Box"
        stage.isResizable = false
        stage.isAlwaysOnTop = true
        stage.show()
    }
}