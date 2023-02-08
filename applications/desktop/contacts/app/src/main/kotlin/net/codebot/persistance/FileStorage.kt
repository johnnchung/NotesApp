package net.codebot.persistance

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import net.codebot.domain.Contact
import java.io.File

object FileStorage {
    fun save(list: MutableList<Contact>, filename: String) {
        val jsonList = Json.encodeToString(list)
        File(filename).writeText(jsonList)
    }

    fun restore(filename: String): MutableList<Contact> {
        val jsonList = File(filename).readText()
        return Json.decodeFromString(jsonList)
    }
}