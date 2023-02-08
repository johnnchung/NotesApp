import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.layout.GridPane
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import javafx.scene.text.Font
import javafx.scene.text.Text
import javafx.stage.Stage
import kotlin.system.exitProcess

class Main : Application() {
    override fun start(stage: Stage) {
        val pane = GridPane()
        val status = Text()
        status.font = Font.font("Helvetica", 12.0)

        pane.isGridLinesVisible = true
        pane.hgap = 1.0
        pane.vgap = 1.0

        // populate the grid with rectangles
        for (i in 0..4) {
            for (j in 0..4) {
                val rect = Rectangle(50.0, 50.0)
                rect.fill = Color.WHITE
                // use (x,y) to store the grid position of each rectangle
                // we're taking advantage of the fact that these fields aren't being otherwise used
                rect.x = i.toDouble()
                rect.y = j.toDouble()

                // attach a handler to each rectangle
                rect.setOnMouseClicked {
                    // toggle using the grid position stored in this rectangle
                    toggleCell(pane, rect.x.toInt(), rect.y.toInt())
                    status.text = "Toggled ${rect.x.toInt()}, ${rect.y.toInt()}"
                }
                pane.add(rect, i, j)
            }
        }

        // show the scene
        stage.title = "GridPane Demo"
        stage.scene = Scene(VBox(pane, status))
        stage.setOnCloseRequest { exitProcess(0) }
        stage.isResizable = false
        stage.show()
    }

    // probably better to have this in the event handler, but this illustrates how to reference externally
    private fun toggleCell(pane: GridPane, row: Int, col: Int) {
        // convert (row, col) into a single index into the grid,
        // which stores everything in a single dimension
        val rect = pane.children[1 + (row*5) + col] as Rectangle
        if (rect.fill == Color.WHITE) {
            rect.fill = Color.BLUE
        } else {
            rect.fill = Color.WHITE
        }
    }
}