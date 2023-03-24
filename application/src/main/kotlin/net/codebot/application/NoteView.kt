package net.codebot.application
import javafx.scene.layout.VBox

// This class displays all our notes in the noteList variable in our model
class NoteView(private val model: Model): VBox(), IView {
    // Create an empty temporary noteslist
    private var notelist : ArrayList<Note> = ArrayList()
    /* This function takes in a sorting option, then sorts our notelist either Title or Date.
     */
    fun sortList(sortVal: String) {
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

    /* This function takes in a boolean value, then sorts our notelist by Grouping if it is true.
        Otherwise, sort the list by the model's sort value.
     */
    fun groupList(groupVal: Boolean) {
      if (groupVal) {
          notelist.sortBy {
              it.getGroup()
          }
      } else {
          sortList(model.defaultsort)
      }
    }

    /* This function takes in a search query, then it filters the notelist array and only displays the notes
       that have a prefix of searchval.
    */
    // TODO: Add a search for the body text
    fun searchlist(searchVal:String) {
        if (searchVal != "") {
            println("hi: $searchVal")
            notelist = ArrayList( notelist.filter {
                it.getTitle().startsWith(searchVal)
            })
         //  notelist =  notelist.forEach{ println(it.getTitle())}
        }

    }

    /* Called when we notify this view.
       We first search the noteslist based on the model's search query, then sort the list based on the model's
       sort value, and finally, we group any notes that have the same group in the noteslist.
     */
    override fun update() {
        this.children.clear()
        notelist = model.getNotesList()
        searchlist(model.searchval)
        sortList(model.defaultsort)
        groupList(model.defaultgroup)
        notelist.forEach {
            this.children.add(it.getBox())
        }
    }
    init {
        model.createView(this)
    }
}