package net.codebot.application

import javafx.scene.control.*

class MenuBarClass(private val model: Model): MenuBar(), IView {
    private val fileOptions = Menu("File")
    private val editOptions = Menu("Edit")
    private val viewOptions = Menu("View")

    // Temporary placeholders for event handlers
    private var fileText: String? = null
    private var editText: String? = null
    private var viewText: String? = null


    // Getters for menu bar
    fun getFileOptions(): Menu {
        return fileOptions
    }
    fun getFileText(): String{
        return fileText.toString()
    }
    fun getEditOptions(): Menu {
        return editOptions
    }
    fun getEditText(): String {
        return editText.toString()
    }
    fun getViewOptions(): Menu {
        return viewOptions
    }
    fun getViewText(): String {
        return viewText.toString()
    }

    override fun update() {
    }

    init {
        fileOptions.items.addAll(MenuItem("New"), MenuItem("Open"),
            MenuItem("Save").apply {
                setOnAction {
                    model.saveNotes()
                } }, SeparatorMenuItem(), MenuItem("Exit")
        )
        editOptions.items.addAll(MenuItem("Undo"), MenuItem("Copy"),
            MenuItem("Paste"), SeparatorMenuItem(), MenuItem("Delete")
        )
        viewOptions.items.addAll(MenuItem("Zoom In"), MenuItem("Zoom Out")
        )
        this.menus.addAll(fileOptions, editOptions, viewOptions)

        // Event handlers within the menu bar
        // TODO: Add controller logic for each menu bar item
        this.fileOptions.setOnAction { event ->
            val selectedOption = event.target as MenuItem
            fileText = selectedOption.text
        }

        this.editOptions.setOnAction { event ->
            val selectedOption = event.target as MenuItem
            editText = selectedOption.text
        }

        this.viewOptions.setOnAction { event ->
            val selectedOption = event.target as MenuItem
            viewText = selectedOption.text
        }

        model.createView(this)
    }
}