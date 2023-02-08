package presentation

import Settings
import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.image.ImageView
import javafx.scene.input.MouseEvent
import javafx.scene.layout.BorderPane
import javafx.scene.layout.VBox

class Scene1(private val presenter: Presenter) : Scene(
        BorderPane(),
        Settings.VIEW_WIDTH.toDouble(),
        Settings.VIEW_HEIGHT.toDouble()
    ), IObserver {
    private fun createLayout() {
        // setup information in the center of the screen
        val img = ImageView(Settings.TITLE_LOGO)

        val title = Label(Settings.TITLE)
        title.font = Settings.TITLE_FONT

        val subtitle = Label(Settings.SUBTITLE)
        subtitle.font = Settings.NORMAL_FONT

        val center = VBox(img, title, subtitle)
        center.alignment = Pos.CENTER
        center.spacing = 10.0

        // setup buttons along the bottom
        val nextButton = Button("Next")
        nextButton.onMouseClicked = EventHandler { mouseEvent: MouseEvent? -> presenter.advance() }
        nextButton.requestFocus()

        val bottom = VBox(nextButton)
        bottom.padding = Insets(50.0)
        bottom.alignment = Pos.CENTER_RIGHT

        // add everything to the root
        val root = this.root as BorderPane
        root.center = center
        root.bottom = bottom
    }

    // updates are driven by the presenter, not the model
    override fun update() {
        // we would fetch data here if we had data on this screen
    }

    init {
        createLayout()
    }
}