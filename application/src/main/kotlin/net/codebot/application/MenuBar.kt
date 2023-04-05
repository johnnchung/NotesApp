package net.codebot.application

import javafx.scene.control.*

class MenuBarClass(private val model: Model): MenuBar(), IView {
    private val fileOptions = Menu("File")

    // Temporary placeholders for event handlers
    private var fileText: String? = null

    // Getters for menu bar
    fun getFileOptions(): Menu {
        return fileOptions
    }
    fun getFileText(): String{
        return fileText.toString()
    }

    override fun update() {
    }

    init {
        // TODO: Apply logic for MenuItem exit
        fileOptions.items.addAll(MenuItem("Save").apply {
                setOnAction {
                    model.saveNotes()
                } }, SeparatorMenuItem(), MenuItem("Exit")
        )
        this.menus.addAll(fileOptions)

        // Event handlers within the menu bar
        this.fileOptions.setOnAction { event ->
            val selectedOption = event.target as MenuItem
            fileText = selectedOption.text
        }
        model.createView(this)
    }
}