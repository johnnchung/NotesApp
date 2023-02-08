package net.codebot.presentation

import javafx.geometry.HPos
import javafx.geometry.Insets
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.GridPane
import net.codebot.domain.Contact
import net.codebot.domain.ContactModel

// setup grid as top-level container
class ContactView(val model: ContactModel) : GridPane(), IView {
    var name: TextField
    var phone: TextField
    var email: TextField

    init {
        // register with the model to receive updates
        model.register(this)

        hgap = 3.0
        vgap = 3.0
        padding = Insets(15.0)

        // name label and field
        name = TextField()
        name.promptText = "Enter name"
        add(Label("Name:"), 0, 0)
        add(name, 1, 0)

        // phone label and field
        phone = TextField()
        phone.promptText = "Enter phone"
        add(Label("Phone:"), 0, 1)
        add(phone, 1, 1)

        // email label and field
        email = TextField()
        email.promptText = "Enter email"
        add(Label("Email:"), 0, 2)
        add(email, 1, 2)

        // save button
        val save = Button("Save")
        add(save, 1, 4)
        GridPane.setHalignment(save, HPos.RIGHT)
        save.requestFocus()

        // key handlers
        save.setOnAction { event ->
            model.add(Contact(name.text, phone.text, email.text))
            model.save()
        }
    }

    // receive update notifications from the model
    override fun modelUpdated(index: Int) {
        if(model.list.size != 0) {
            name.text = model.list.first().name
            phone.text = model.list.first().phone
            email.text = model.list.first().email
        }
    }
}