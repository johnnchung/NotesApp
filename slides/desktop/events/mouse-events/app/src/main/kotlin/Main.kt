import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.scene.control.TextArea
import javafx.scene.input.MouseDragEvent
import javafx.scene.input.MouseEvent
import javafx.scene.layout.Priority
import javafx.scene.layout.StackPane
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import javafx.stage.Stage

// Demonstrates mouse events
class Main : Application() {

    override fun start(stage: Stage) {

        // rectangle to listen to events
        val shape = Rectangle()
        shape.fill = Color.BLUE
        shape.height = 300.0
        shape.width = 400.0

        val top = StackPane(shape)
        top.minWidth = shape.width + 20.0
        top.prefWidth = shape.width + 100.0
        top.minHeight = shape.height + 20.0
        top.prefWidth = shape.height + 100.0
        VBox.setVgrow(top, Priority.ALWAYS);

        // textArea to display events
        val text = TextArea()
        text.maxHeight = 400.0
        VBox.setVgrow(text, Priority.ALWAYS);

        // capture and print events to text area
        shape.setOnMousePressed { event -> printEvent(text, event) }
        shape.setOnMouseReleased { event -> printEvent(text, event) }
        shape.setOnMouseClicked { event -> printEvent(text, event) }
        shape.setOnMouseEntered { event -> printEvent(text, event) }
        shape.setOnMouseExited { event -> printEvent(text, event) }
        shape.setOnMouseDragOver { event -> printEvent(text, event) }
        shape.setOnMouseDragReleased { event -> printEvent(text, event) }
        shape.setOnMouseMoved { event -> printEvent(text, event) }

        // setup scene
        val scene = Scene(VBox(top, text), 700.0, 800.0)
        stage.scene = scene

        // setup stage
        stage.title = "MouseEvents Demo"
        stage.show()
    }

    private fun printEvent(textWidget: TextArea, event: MouseEvent) {
        textWidget.appendText(
            event.eventType.toString()
                    + " x: ${event.x}, "
                    + " y: ${event.y}, "
                    + " sceneX: ${event.sceneX}, "
                    + " sceneY: ${event.sceneY},"
                    + " button: ${event.button}, "
                    + " click: ${event.clickCount}\n"
        )
    }
}