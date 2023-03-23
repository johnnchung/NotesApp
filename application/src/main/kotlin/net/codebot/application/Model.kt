package net.codebot.application

import java.time.LocalDateTime

class Model {
    // List of Views that are subscribed to the model
    private val views : ArrayList<IView> = ArrayList()
    /* A map containing title as the key, the pair contains the following:  "Group" : "Body Text"
       notesMap is a starter stucture to hold the data, use notesList to do all your functions.
    */
    private val notesMap : MutableMap<String, Pair<String, String>> = mutableMapOf()
    // Mutablelist of all groups.
    private val groupArray : MutableList<String> = mutableListOf()
    /* notesList is the arraylist that holds a note instance that is used to display the notes in NoteView. */
    private val notesList: ArrayList<Note> = ArrayList()

    // Arrays that store the title, group, and content of each note item
    // Corresponds by index
    val titleList = mutableListOf<String>()
    val groupList = mutableListOf<String>()
    val contentList = mutableListOf<String>()
    var editedNote = ""
    var notePage = ArrayList<NotesPage>()

    // TODO: Add documentation for these
    var openedNotes = false
    var savedNotes = false
    var closedNotes = false

    /* Default state values for sorting options, default search value for the search bar,
       and if sort by grouping is enabled.
     */
    var defaultsort = "Title"
    var searchval = ""
    var defaultgroup = false


    // Add a view to the views arraylist
    fun createView(view: IView) {
        views.add(view)
        view.update()
    }

    // Notify all views that there is a change in the model
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
        // main important section for adding to the homepage, create an instance of the note class and
        // add it to the noteslist
        notesList.add(Note(this, title, group,"",LocalDateTime.now()))
        updateAllNotes()
        notifyObservers()
    }

    // Retrieve notesList
    fun getNotesList(): ArrayList<Note> {
        return notesList
    }

    // Retrieves  notesMap. This is only used when updating a note in notesList
    fun getNotesMap(): MutableMap<String, Pair<String, String>> {
        return notesMap
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
        // notesMap in this function is used for updating purposes only in terms of storing the old
        // values of all notes instances before an update has happened
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


    /*  This is called when there is a change in sorting options. It changes the default state of our sorting option,
        and lets all the views know that there is a change.
     */
    fun sortNotify(sortVal: String) {
        defaultsort = sortVal
        notifyObservers()
    }

    /*  This is called when "By Group" is enabled or disabled. It changes the state of our group variable in the model
        to whatever newval is, and lets all the views know that there is a change.
     */
    fun groupNotes(newval: Boolean) {
        defaultgroup = newval
        notifyObservers()
    }

    /*  This is called when our Search bar has input. It changes the state of searchval in the model
      to whatever newval is, and lets all the views know that there is a change.
   */
    fun searchQuery(newval: String) {
        searchval = newval
        notifyObservers()
    }
}