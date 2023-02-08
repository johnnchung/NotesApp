package menubar

import javafx.application.Application
import javafx.application.Application.launch
import javafx.scene.Scene
import javafx.scene.control.Menu
import javafx.scene.control.MenuBar
import javafx.scene.control.MenuItem
import javafx.scene.layout.BorderPane
import javafx.stage.Stage

class Menus : Application() {
    override fun start(stage: Stage) {

        val menuBar = MenuBar();

        // Can make this a macOS style menubar on that OS
        // Leave commented out to have menus anchored in Window
        // https://stackoverflow.com/questions/22569046/how-to-make-an-os-x-menubar-in-javafx/28874063
//        val os = System.getProperty("os.name");
//        if (os != null && os.startsWith("Mac"))
//            menuBar.useSystemMenuBarProperty().set(true);

        val fileMenu = Menu("File")
        val fileNewMenu = MenuItem("New")
        val fileOpenMenu = MenuItem("Open")
        fileMenu.items.add(fileNewMenu)
        fileMenu.items.add(fileOpenMenu)

        val editMenu = Menu("Edit")
        val editCopyMenu = MenuItem("Copy")
        val editPasteMenu = MenuItem("Paste")
        editMenu.items.add(editCopyMenu)
        editMenu.items.add(editPasteMenu)

        menuBar.menus.addAll(fileMenu, editMenu)

        val borderPane = BorderPane();
        borderPane.setTop(menuBar);

        stage.setScene(Scene(borderPane, 300.0, 200.0));
        stage.title = "Menu Demo"
        stage.show()
    }
}