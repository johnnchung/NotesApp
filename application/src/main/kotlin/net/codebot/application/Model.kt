package net.codebot.application

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

class Model {
    private val views : ArrayList<IView> = ArrayList()
    private val notesMap : MutableMap<String, Pair<String, String>> = mutableMapOf()
    private val groupArray : MutableList<String> = mutableListOf()
    private val noteslist: ArrayList<Note> = ArrayList()

    val titleList = mutableListOf<String>()
    val groupList = mutableListOf<String>()
    val contentList = mutableListOf<String>()

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
        updateAllNotes()
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
        updateAllNotes()
        notifyObservers()
    }

    fun updateNote(searchkey:String, title:String, group:String) {
        val oldText = notesMap[searchkey]!!.second
        for (notes in noteslist) {
            if (notes.titleField.text == title) {
                println("ISIT, New:")
                val temp = noteslist.indexOf(notes)
                //noteslist[temp].titleField.text = title
                notes.titleField.text = title
                notes.groupField.text = group
                break
            }
        }
        notesMap.remove(searchkey)
        notesMap[title] = Pair(group, oldText)
        updateAllNotes()
        notifyObservers()
    }

    fun updateAllNotes() {
        titleList.clear()
        groupList.clear()
        contentList.clear()
        for(arrItem in getNoteslist()) {
            titleList.add(arrItem.getTitle())
            groupList.add(arrItem.getGroup())
            contentList.add(arrItem.getContent())
        }
    }


}