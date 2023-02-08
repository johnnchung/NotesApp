import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.paint.Color
import javafx.scene.text.*
import javafx.stage.Stage

class Main : Application() {
    override fun start(stage: Stage?) {
        val header1 = Font.font("Verdana", FontWeight.BOLD, 18.0)
        val header2 = Font.font("Verdana", FontWeight.BOLD, 16.0)
        val body = Font.font("Verdana", FontWeight.NORMAL, 14.0)

        val header1colour = Color.DARKSLATEBLUE
        val header2colour = Color.BLUE
        val bodyColour = Color.BLACK

        // Creating text objects
        val text1 = Text("This is the title \n")
        text1.font = header1
        text1.fill = header1colour

        val text2 = Text("This is the subtitle \n")
        text2.font = header2
        text2.fill = header2colour

        val text3 = Text("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.")
        text3.font = body
        text3.fill = bodyColour

        // Creating the text flow plane
        val textFlowPane = TextFlow();

        // Setting the line spacing between the text objects
        textFlowPane.setTextAlignment(TextAlignment.LEFT);
        textFlowPane.setPrefSize(600.0, 300.0);
        textFlowPane.setLineSpacing(5.0);

        // Populate text flow pane
        val list = textFlowPane.getChildren();
        list.addAll(text1, text2, text3);

        // Setup stage
        val scene = Scene(textFlowPane);
        stage?.setTitle("TextFlowPane Example");
        stage?.setScene(scene);
        stage?.show();
    }
}