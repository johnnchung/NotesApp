package net.codebot.presentation

import javafx.collections.FXCollections
import javafx.scene.control.Label
import javafx.scene.control.ListView
import net.codebot.domain.ContactModel

class ContactList(val model: ContactModel) : ListView<String>(), IView {
    init {
        // register with the model for updates
        model.register(this)

        // setup list
        items = FXCollections.observableArrayList()

        // Select the first item; you don't want an empty selection
        if (items.size > 0) selectionModel.selectIndices(0)
        selectionModel.selectedItem
        selectionModel.selectedItemProperty().addListener { _, oldValue, newValue ->
            println("$oldValue -> $newValue")
        }
    }

    // receive update notifications from the model
    override fun modelUpdated(index: Int) {
        items.add(model.list.get(index).name)
    }
}