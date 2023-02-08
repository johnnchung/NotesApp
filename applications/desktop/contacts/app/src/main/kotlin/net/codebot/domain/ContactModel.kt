package net.codebot.domain

import net.codebot.persistance.FileStorage
import net.codebot.presentation.IView

class ContactModel {
    val list = mutableListOf<Contact>()
    val views = mutableListOf<IView>()

    fun add(element: Contact) {
        list.add(element)
        notifyViews(list.indexOf(list.last()))
    }

    fun addAll(elements: MutableList<Contact>) {
        list.clear()
        for (element in elements) {
            list.add(element)
        }
        notifyViews(list.indexOf(list.last()))
    }

    fun register(view: IView) {
        this.views.add(view)
    }

    fun notifyViews(index: Int) {
        for (view in views) {
            view.modelUpdated(index)
        }
    }

    fun save() {
        FileStorage.save(list, "data.json")
    }

    fun restore() {
        list.clear()
        addAll(FileStorage.restore("data.json"))
    }
}
