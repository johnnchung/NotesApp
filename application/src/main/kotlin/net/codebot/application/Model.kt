package net.codebot.application

class Model {
    private val views : ArrayList<IView> = ArrayList()
    private val notesMap : MutableMap<String, Pair<String, String>> = mutableMapOf()
    private val groupArray : MutableList<String> = mutableListOf()
    private val noteslist: ArrayList<Note> = ArrayList()

    fun createView(view: IView) {
        views.add(view)
        view.update()
    }
    private fun notifyObservers() {
        for (view in views) {
            view.update()
        }
    }
    fun createNote(title: String, group: String) {
        if (notesMap.containsKey(title)) {
            return
        }

        if (!groupArray.contains(group)) {
            groupArray.add(group)
        }

        notesMap[title] = Pair(group, "")
        noteslist.add(Note(this,title,group,""))
        notifyObservers()
    }

    fun getNoteslist(): ArrayList<Note> {
        return noteslist
    }


    fun getNotes(): MutableMap<String, Pair<String, String>> {
        return notesMap
    }

    fun deleteNote(title:String) {
        notesMap.remove(title)
        for (notes in noteslist) {
            if (notes.titleField.text == title) {
                noteslist.remove(notes)
                break
            }
        }
        notifyObservers()
    }

    fun updateNote(searchkey:String, title:String, group:String) {
        val oldText = notesMap[searchkey]!!.second
        for (notes in noteslist) {
            if (notes.titleField.text == searchkey) {
                notes.titleField.text = title
                notes.groupField.text = group
                break
            }
        }
        notesMap.remove(searchkey)
        notesMap[title] = Pair(group, oldText)
        notifyObservers()
    }
}