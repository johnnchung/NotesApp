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

class Scene3(private val presenter: Presenter) : Scene(
    BorderPane(),
    Settings.VIEW_WIDTH.toDouble(),
    Settings.VIEW_HEIGHT.toDouble()
), IObserver {
    var osInfo = Label()
    private fun createLayout() {
        // setup individual elements
        val title = Label("OS Information")
        title.font = Settings.TITLE_FONT
        val top = VBox(title, osInfo)
        top.padding = Insets(10.0)
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
        osInfo.text = """
             Name: ${presenter.getValue("os.name")}
             Vendor: ${presenter.getValue("os.arch")}Version: ${presenter.getValue("os.version")}
             """.trimIndent()
    }

    init {
        createLayout()
        update()
    }
}