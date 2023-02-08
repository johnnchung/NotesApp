package presentation

import Settings
import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox

class Scene2(private val presenter: Presenter) : Scene(
    BorderPane(),
    Settings.VIEW_WIDTH.toDouble(),
    Settings.VIEW_HEIGHT.toDouble()
), IObserver {
    var javaInfo = Label("Information")

    private fun createLayout() {
        // setup individual elements
        val title = Label("Java Information")
        title.font = Settings.TITLE_FONT
        val top = VBox(title, javaInfo)
        top.spacing = 10.0
        top.padding = Insets(50.0)
        top.alignment = Pos.CENTER

        // forward and back buttons
        val nextButton = Button("Next")
        nextButton.onMouseClicked = EventHandler { presenter.advance() }
        nextButton.requestFocus()

        val prevButton = Button("Previous")
        prevButton.onMouseClicked = EventHandler { presenter.rewind() }

        val bottom = HBox(prevButton, nextButton)
        bottom.alignment = Pos.CENTER_RIGHT
        bottom.spacing = 10.0
        bottom.padding = Insets(50.0)

        // add everything to the root
        val root = this.root as BorderPane
        root.center = top
        root.bottom = bottom
    }

    // updates are driven by the presenter, not the model
    override fun update() {
        javaInfo.text = """
             Vendor: ${presenter.getValue("java.vendor")}
             Version: ${presenter.getValue("java.version")}
             """.trimIndent()
    }

    init {
        createLayout()
        update()
    }
}