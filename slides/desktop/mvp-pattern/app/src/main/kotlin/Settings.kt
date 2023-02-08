import javafx.scene.text.Font
import javafx.scene.image.Image
import javafx.scene.text.FontWeight

object Settings {
    // Constants
    const val VIEW_WIDTH = 500
    const val VIEW_HEIGHT = 400
    const val WINDOW_TITLE = "Screens Demo"

    // Fonts
    val TITLE_FONT: Font= Font.font("Arial", FontWeight.BOLD, 30.0)
    val NORMAL_FONT: Font = Font.font("Arial", 24.0)

    // Images
    const val TITLE = "Model-View-Presenter Demo"
    const val SUBTITLE = "CS 346 Application Development"
    val TITLE_LOGO = Image("logo.png")
}