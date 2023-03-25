package net.codebot.application
import javafx.geometry.Insets
import javafx.scene.layout.ColumnConstraints
import javafx.scene.layout.GridPane
import javafx.scene.layout.Priority
import javafx.scene.layout.RowConstraints

// This class displays all our notes in the noteList variable in our model
class NoteView(private val model: Model): GridPane(), IView {
    /* Called when we notify this view.
       We first search the notesList based on the model's search query, then sort the list based on the model's
       sort value, and finally, we group any notes that have the same group in the notesList.
     */
    override fun update() {
        notelist = model.getNotesList()
        searchList(model.searchval)
        sortList(model.defaultsort)
        groupList(model.defaultgroup)

        // Clear the GridPane and add each note to a cell in the grid
        children.clear()
        var row = 0
        var col = 0
        for (note in notelist) {
            val box = note.getBox()
            setVgrow(box, Priority.ALWAYS)
            setHgrow(box, Priority.ALWAYS)
            add(box, col, row)
            col++
            if (col > 3) {
                col = 0
                row++
            }
        }
    }

    init {
        model.createView(this)
        vgap = 10.0
        hgap = 10.0
        padding = Insets(10.0)

        // Add column and row constraints to make the GridPane resize properly
        for (i in 0..3) {
            columnConstraints.add(ColumnConstraints().apply {
                hgrow = Priority.ALWAYS
            })
        }
        for (i in 0..10) {
            rowConstraints.add(RowConstraints().apply {
                vgrow = Priority.ALWAYS
            })
        }
    }

    // Create an empty temporary notesList
    private var notelist : ArrayList<Note> = ArrayList()

    /* This function takes in a sorting option, then sorts our noteList either Title or Date.
     */
    private fun sortList(sortVal: String) {
        if (sortVal == "Title") {
            notelist.sortBy {
                it.getTitle().lowercase()
            }
        }
        else {
            notelist.sortByDescending {
                it.getTime()
            }
        }
    }

    /* This function takes in a boolean value, then sorts our noteList by Grouping if it is true.
        Otherwise, sort the list by the model's sort value.
     */
    private fun groupList(groupVal: Boolean) {
        if (groupVal) {
            notelist.sortBy {
                it.getGroup()
            }
        } else {
            sortList(model.defaultsort)
        }
    }

    /* This function takes in a search query, then it filters the noteList array and only displays the notes
       that have a prefix of searchVal.
    */
    // TODO: Add a search for the body text
    private fun searchList(searchVal:String) {
        if (searchVal != "") {
            notelist = ArrayList( notelist.filter {
                it.getTitle().startsWith(searchVal)
            })
        }
    }
}