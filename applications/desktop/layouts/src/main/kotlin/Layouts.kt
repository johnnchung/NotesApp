import javafx.application.Application
import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.*
import javafx.stage.Stage
import kotlin.system.exitProcess

/*
* Layouts: show examples of common layouts in JavaFX
*  BasicWindow class contains defaults for the Window
*  ButtonFactory creates buttons using default properties
*/
class Layouts : Application() {
    val STAGE_WIDTH = 400.0
    val STAGE_HEIGHT = 300.0
    val BUTTON_MIN_WIDTH = 50.0
    val BUTTON_PREF_WIDTH = 100.0
    val BUTTON_MAX_WIDTH = 200.0
    val LABEL_X = 10.0
    val BUTTON_X = 75.0
    override fun start(stage: Stage) {
        // root of our tree, doesn't handle resizing or layout in this case
        val pane = Pane()

        // button to create the windows
        val start = Button("Click here to create layouts")
        start.relocate(BUTTON_X, 25.0)
        start.setOnAction {
            PaneWindow(50.0F, 50.0F)
            StackPaneWindow(100.0F, 100.0F)
            FlowPaneWindow(150.0F, 150.0F)
            HBoxWindow(200.0F, 200.0F)
            VBoxWindow(250.0F, 250.0F)
            GridPaneWindow(300.0F, 300.0F)
            TilePaneWindow(350.0F, 350.0F)
            BorderPaneWindow(400.0F, 400.0F)
        }
        pane.children.add(start)

        // button to demonstrate minimum size
        val min_label = Label("Minimum")
        min_label.relocate(LABEL_X, 100.0)
        pane.children.add(min_label)
        val min = Button(BUTTON_MIN_WIDTH.toString())
        min.prefWidth = BUTTON_MIN_WIDTH
        min.relocate(BUTTON_X, 100.0)
        pane.children.add(min)

        // button to demonstrate preferred size
        val pref_label = Label("Preferred")
        pref_label.relocate(LABEL_X, 150.0)
        pane.children.add(pref_label)
        val pref = Button(BUTTON_PREF_WIDTH.toString())
        pref.prefWidth = BUTTON_PREF_WIDTH
        pref.relocate(BUTTON_X, 150.0)
        pane.children.add(pref)

        // button to demonstrate max size
        val max_label = Label("Maximum")
        max_label.relocate(LABEL_X, 200.0)
        pane.children.add(max_label)
        val max = Button(BUTTON_MAX_WIDTH.toString())
        max.prefWidth = BUTTON_MAX_WIDTH
        max.relocate(BUTTON_X, 200.0)
        pane.children.add(max)

        // scene holding this layout
        val scene = Scene(pane)

        // setup starting window
        stage.x = 0.0
        stage.y = 0.0
        stage.width = 300.0
        stage.height = 300.0
        stage.minWidth = 300.0
        stage.minHeight = 300.0
        stage.maxWidth = 300.0
        stage.maxHeight = 300.0
        stage.isResizable = false
        stage.scene = scene
        stage.title = "Layouts Demo"
        stage.onCloseRequest = EventHandler {
            exitProcess(0)
        }
        stage.show()
    }

    /*
     * Base class for all of our windows
     * Used to set default values for all windows
     */
    open inner class StandardWindow @JvmOverloads internal constructor(x: Float = 0f, y: Float = 0f) :
        Stage() {
        init {
            this.x = x.toDouble()
            this.y = y.toDouble()
            width = STAGE_WIDTH
            height = STAGE_HEIGHT
            this.isResizable = true
        }
    }

    // Customized button
    // Used to set default values for all buttons
    // Lets us manipulate MIN, MAX, PREFERRED sizes in one place for all demos
    private inner class StandardButton @JvmOverloads internal constructor(caption: String? = "Untitled") :
        Button(caption) {
        init {
            // setText(caption); // call to super class already does this
            isVisible = true
            minWidth = BUTTON_MIN_WIDTH
            prefWidth = BUTTON_PREF_WIDTH
            maxWidth = BUTTON_MAX_WIDTH
        }
    }

    /*
     * Classes for each type of Layout that we wish to demo
     * These classes need to instantiate and add a reasonable number of buttons
     * to demo that particular functionality.
     */
    private inner class PaneWindow @JvmOverloads constructor(x: Float = 0f, y: Float = 0f) :
        StandardWindow(x, y) {
        init {
            val button1: Button = StandardButton("Button One")
            val button2: Button = StandardButton("Button Two")
            val button3: Button = StandardButton("Button Three")
            val button4: Button = StandardButton("Button Four")
            val button5: Button = StandardButton("Button Five")
            val button6: Button = StandardButton("Button Six")
            val button7: Button = StandardButton("Button Seven")
            val button8: Button = StandardButton("Button Eight")
            val button9: Button = StandardButton("Button Nine")
            val root = Pane()
            root.children.add(button1)
            root.children.add(button2)
            root.children.add(button3)
            root.children.add(button4)
            root.children.add(button5)
            root.children.add(button6)
            root.children.add(button7)
            root.children.add(button8)
            root.children.add(button9)
            scene = Scene(root)
            title = "Pane"
            show()
        }
    }

    private inner class StackPaneWindow @JvmOverloads constructor(x: Float = 0f, y: Float = 0f) :
        StandardWindow(x, y) {
        init {
            val button1: Button = StandardButton("Button One")
            val button2: Button = StandardButton("Button Two")
            val button3: Button = StandardButton("Button Three")
            val button4: Button = StandardButton("Button Four")
            val root = StackPane(button1, button2, button3, button4)
            scene = Scene(root)
            title = "StackPane"
            show()
        }
    }

    private inner class FlowPaneWindow @JvmOverloads constructor(x: Float = 0f, y: Float = 0f) :
        StandardWindow(x, y) {
        init {
            val button1: Button = StandardButton("Button One")
            val button2: Button = StandardButton("Button Two")
            val button3: Button = StandardButton("Button Three")
            val button4: Button = StandardButton("Button Four")
            val button5: Button = StandardButton("Button Five")
            val button6: Button = StandardButton("Button Six")
            val root = FlowPane(button1, button2, button3, button4, button5, button6)
            root.alignment = Pos.CENTER
            root.padding = Insets(10.0)
            scene = Scene(root)
            title = "FlowPane"
            show()
        }
    }

    private inner class HBoxWindow @JvmOverloads constructor(x: Float = 0f, y: Float = 0f) :
        StandardWindow(x, y) {
        init {
            val button1: Button = StandardButton("Button One")
            val button2: Button = StandardButton("Button Two")
            val button3: Button = StandardButton("Button Three")
            val button4: Button = StandardButton("Button Four")
            val root = HBox(button1, button2, button3, button4)
            root.alignment = Pos.CENTER
            root.padding = Insets(10.0)
            root.spacing = 10.0
            scene = Scene(root)
            title = "HBox"
            show()
        }
    }

    internal inner class VBoxWindow @JvmOverloads constructor(x: Float = 0f, y: Float = 0f) :
        StandardWindow(x, y) {
        init {
            val button1: Button = StandardButton("Button One")
            val button2: Button = StandardButton("Button Two")
            val button3: Button = StandardButton("Button Three")
            val button4: Button = StandardButton("Button Four")
            val root = VBox(button1, button2, button3, button4)
            root.alignment = Pos.CENTER
            root.padding = Insets(10.0)
            root.spacing = 10.0
            scene = Scene(root)
            title = "VBox"
            show()
        }
    }

    internal inner class GridPaneWindow @JvmOverloads constructor(x: Float = 0f, y: Float = 0f) :
        StandardWindow(x, y) {
        init {
            val button1: Button = StandardButton("Button One")
            val button2: Button = StandardButton("Button Two")
            val button3: Button = StandardButton("Button Three")
            val button4: Button = StandardButton("Button Four")
            val button5: Button = StandardButton("Button Five")
            val button6: Button = StandardButton("Button Six")
            val button7: Button = StandardButton("Button Seven")
            val button8: Button = StandardButton("Button Eight")
            val button9: Button = StandardButton("Button Nine")
            val root = GridPane()
            root.add(button1, 0, 0)
            root.add(button2, 1, 0)
            root.add(button3, 2, 0)
            root.add(button4, 0, 1)
            root.add(button5, 1, 1)
            root.add(button6, 2, 1)
            root.add(button7, 0, 2)
            root.add(button8, 1, 2)
            root.add(button9, 2, 2)
            root.alignment = Pos.CENTER
            root.padding = Insets(10.0)
            root.hgap = 10.0
            root.vgap = 10.0
            scene = Scene(root)
            title = "GridPane"
            show()
        }
    }

    internal inner class TilePaneWindow @JvmOverloads constructor(x: Float = 0f, y: Float = 0f) :
        StandardWindow(x, y) {
        init {
            val button1: Button = StandardButton("Button One")
            val button2: Button = StandardButton("Button Two")
            val button3: Button = StandardButton("Button Three")
            val button4: Button = StandardButton("Button Four")
            val button5: Button = StandardButton("Button Five")
            val button6: Button = StandardButton("Button Six")
            val button7: Button = StandardButton("Button Seven")
            val button8: Button = StandardButton("Button Eight")
            val button9: Button = StandardButton("Button Nine")
            val root = TilePane()
            root.tileAlignment = Pos.CENTER
            root.prefColumns = 3
            root.prefRows = 3
            root.children.add(button1)
            root.children.add(button2)
            root.children.add(button3)
            root.children.add(button4)
            root.children.add(button5)
            root.children.add(button6)
            root.children.add(button7)
            root.children.add(button8)
            root.children.add(button9)
            root.alignment = Pos.CENTER
            root.padding = Insets(10.0)
            scene = Scene(root)
            title = "TilePane"
            show()
        }
    }

    internal inner class BorderPaneWindow @JvmOverloads constructor(x: Float = 0f, y: Float = 0f) :
        StandardWindow(x, y) {
        init {
            val button1: Button = StandardButton("Button One")
            val button2: Button = StandardButton("Button Two")
            val button3: Button = StandardButton("Button Three")
            val button4: Button = StandardButton("Button Four")
            val button5: Button = StandardButton("Button Five")

            button1.setOnAction { println("Button1 pressed") }

            val root = BorderPane()
            root.top = button1
            BorderPane.setAlignment(button1, Pos.CENTER)
            root.center = button2
            root.bottom = button3
            BorderPane.setAlignment(button3, Pos.CENTER)
            root.left = button4
            BorderPane.setAlignment(button4, Pos.CENTER)
            root.right = button5
            BorderPane.setAlignment(button5, Pos.CENTER)
            root.padding = Insets(10.0)
            scene = Scene(root)
            title = "BorderPane"
            show()
        }
    }
}