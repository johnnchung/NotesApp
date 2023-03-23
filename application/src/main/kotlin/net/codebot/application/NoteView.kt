package net.codebot.application
import javafx.scene.layout.VBox

class NoteView(private val model: Model): VBox(), IView {
    private var notelist : ArrayList<Note> = ArrayList()
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

    fun groupList(groupVal: Boolean) {
      if (groupVal) {
          notelist.sortBy {
              it.getGroup()
          }
      } else {
          sortList(model.defaultsort)
      }
    }

    fun searchlist(searchVal:String) {
        if (searchVal != "") {
            println("hi: $searchVal")
            notelist = ArrayList( notelist.filter {
                it.getTitle().startsWith(searchVal)
            })
         //  notelist =  notelist.forEach{ println(it.getTitle())}
        }

    }

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