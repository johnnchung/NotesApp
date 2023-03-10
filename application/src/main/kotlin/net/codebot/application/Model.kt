package net.codebot.application

class Model {
    private val views : ArrayList<IView> = ArrayList()
    private val notesMap : MutableMap<String, Pair<String, String>> = mutableMapOf()
    private val groupArray : MutableList<String> = mutableListOf()
    private val notesList: ArrayList<Note> = ArrayList()

    // Arrays that store the title, group, and content of each note item
    // Corresponds by index
    val titleList = mutableListOf<String>()
    val groupList = mutableListOf<String>()
    val contentList = mutableListOf<String>()
    var editedNote = ""
    var notePage = ArrayList<NotesPage>()

    var openedNotes = false
    var savedNotes = false
    var closedNotes = false

    fun createView(view: IView) {
        views.add(view)
        view.update()
    }

    private fun notifyObservers() {
        for (view in views) {
            view.update()
        }
    }

    // Create notes app with title, group, and empty content
    fun createNote(title: String, group: String) {
        if (notesMap.containsKey(title)) {
            return
        }
        if (!groupArray.contains(group)) {
            groupArray.add(group)
        }
        notesMap[title] = Pair(group, "")
        notesList.add(Note(this, title, group,""))
        updateAllNotes()
        notifyObservers()
    }

    // Retrieve notesList
    fun getNotesList(): ArrayList<Note> {
        return notesList
    }

    // Delete specific note by finding unique title within our notesMap
    fun deleteNote(title: String) {
        notesMap.remove(title)
        for (notes in notesList) {
            if (notes.titleField.text == title) {
                notesList.remove(notes)
                break
            }
        }
        updateAllNotes()
        notifyObservers()
    }

    // Update notes title or group field
    fun updateNote(searchKey: String, title: String, group: String) {
        val oldText = notesMap[searchKey]!!.second
        for (notes in notesList) {
            if (notes.titleField.text == title) {
                notes.titleField.text = title
                notes.groupField.text = group
                break
            }
        }
        notesMap.remove(searchKey)
        notesMap[title] = Pair(group, oldText)
        updateAllNotes()
        notifyObservers()
    }

    // Clear all arrays associated with notes, and re-populate based on updated values
    private fun updateAllNotes() {
        titleList.clear()
        groupList.clear()
        contentList.clear()
        for(arrItem in getNotesList()) {
            titleList.add(arrItem.getTitle())
            groupList.add(arrItem.getGroup())
            contentList.add(arrItem.getContent())
        }
    }

    // Updating notes page to open the tab pane of the note selected for editing
    fun updateNotesPage(title: String, group: String, content: String) {
        notePage.clear()
        editedNote = title
        notePage.add(NotesPage(this, editedNote))
        closedNotes = true
        openedNotes = true
        notifyObservers()
    }

    // Save notes field text objects
    fun saveNotes() {
        savedNotes = true
        notifyObservers()
    }

    // Save the notes fields
    fun saveNotesContent(title: String, htmlText: String) {
        for(arrItem in getNotesList()) {
            if(arrItem.getTitle() == title) {
                val index = getNotesList().indexOf(arrItem)
                notesList[index].bodyDisplay.text = htmlText
                contentList[index] = htmlText
            }
        }
    }
}