package analog.clock

import javafx.animation.AnimationTimer
import javafx.application.Application
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyCodeCombination
import javafx.scene.input.KeyCombination
import javafx.scene.input.KeyEvent
import javafx.scene.layout.StackPane
import javafx.scene.layout.VBox
import javafx.scene.text.Font
import javafx.stage.Stage
import java.text.SimpleDateFormat
import java.util.*
import kotlin.system.exitProcess

class Main : Application() {
    private val width = 250.0
    private val height = 325.0

    override fun start(stage: Stage) {
        // analog time
        val clock = ClockFace(0.0, 0.0, 100.0)
        val clockPane = StackPane(clock)
        clockPane.setPrefSize(width, height - 75)

        // digital time
        val time = Label()
        time.font = Font("Helvetica", 28.0)
        val timePane = StackPane(time)
        timePane.setPrefSize(width, 75.0)

        // timer fires every 1/60 seconds, and fetches time
        val dateFormat = SimpleDateFormat("hh:mm:ss")
        val timer = object : AnimationTimer() {
            override fun handle(now: Long) {
                time.text = dateFormat.format(Calendar.getInstance().time)
                clock.setTime(
                    Calendar.getInstance()[Calendar.HOUR_OF_DAY],
                    Calendar.getInstance()[Calendar.MINUTE],
                    Calendar.getInstance()[Calendar.SECOND]
                )
            }
        }
        timer.start()

        // show scene & stage
        val scene = Scene(VBox(clockPane, timePane))
        scene.onKeyPressed = EventHandler { event: KeyEvent? ->
            if (KeyCodeCombination(KeyCode.Q, KeyCombination.ALT_DOWN).match(event)) {
                exitProcess(0)
            }
        }
        stage.scene = scene
        stage.onCloseRequest = EventHandler {
            exitProcess(0)
        }
        stage.title = "Clock"
        stage.isResizable = false
        stage.width = width
        stage.height = height
        stage.isAlwaysOnTop = true
        stage.show()
    }
}