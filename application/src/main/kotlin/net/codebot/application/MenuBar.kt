package net.codebot.application

import javafx.scene.Node
import javafx.scene.control.*
import javafx.scene.layout.BorderPane
import javafx.scene.web.HTMLEditor

class MenuBarClass(private val model: Model, private val homePage: Node) : MenuBar(), IView {
    private val fileOptions = Menu("File")
    private val viewOptions = Menu("View")

    fun getFileOptions():Menu {
        return fileOptions
    }

    fun getViewOptions():Menu {
        return viewOptions
    }



    override fun update() {
    }



    init {
        // File menu items
        fileOptions.items.addAll(MenuItem("Save").apply {
            setOnAction {
                model.saveNotes()
            }
        })

        // View menu items
        val zoomInItem = MenuItem("Zoom In")
        val zoomOutItem = MenuItem("Zoom Out")
        viewOptions.items.addAll(zoomInItem, zoomOutItem)

        // Event handlers within the menu bar
        fileOptions.setOnAction { event ->
            val selectedOption = event.target as MenuItem
            when (selectedOption.text) {
                "Save" -> model.saveNotes()
            }
        }

        zoomInItem.setOnAction {
            homePage.scaleX *= 1.1
            homePage.scaleY *= 1.1
        }

        zoomOutItem.setOnAction {
            homePage.scaleX /= 1.1
            homePage.scaleY /= 1.1
        }

        // Add menus to menu bar
        this.menus.addAll(fileOptions, viewOptions)
        model.createView(this)
    }
}
