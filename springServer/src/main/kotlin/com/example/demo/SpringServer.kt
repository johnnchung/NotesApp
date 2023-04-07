package com.example.demo

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*
import java.io.FileInputStream
import java.io.FileOutputStream
import java.net.HttpURLConnection
import java.net.URL

@SpringBootApplication
class SpringServer

@Serializable
data class NotesSchema(val width : Double, val height : Double,
					   val xCoord : Double, val yCoord : Double,
					   val titles : List<String>, val groups : List<String>,
					   val contents : List<String>, val darkMode: Boolean) {}

fun main(args: Array<String>) {
	runApplication<SpringServer>(*args)
}

// Define routes for retrieving data through HTTP protocols
@RestController
@RequestMapping("/notes")
class NotesResource(val service: NotesService) {
	@GetMapping
	fun getData(): NotesSchema {
		return service.getAllNotes()
	}

	@PutMapping()
	fun put(@RequestBody dataClass: NotesSchema) {
		service.put(dataClass)
	}
}

// Define service to control state of notes data
@Service
class NotesService {
	// Initialize our JSON schema as empty
	var notes: NotesSchema = NotesSchema(0.0, 0.0, 0.0, 0.0, emptyList(), emptyList(), emptyList(), false)

	// Retrieve list of notes
	fun getAllNotes(): NotesSchema {
		var fileInput = URL("https://s3.amazonaws.com/notesapplicationbucket/data.json").openStream()
		return Json.decodeFromStream(fileInput)
	}

	// Updates list of notes, creating a new one if it does not already exist
	fun put(newNote: NotesSchema) {
		val url = URL("https://s3.amazonaws.com/notesapplicationbucket/data.json")
		val connection = url.openConnection() as HttpURLConnection
		connection.requestMethod = "PUT"
		connection.doOutput = true
		connection.setRequestProperty("Content-Type", "application/json")
		val string = Json.encodeToString(newNote)
		connection.outputStream.use { outputStream ->
			outputStream.write(string.toByteArray())
		}
		if (connection.responseCode != HttpURLConnection.HTTP_OK) {
			throw RuntimeException("Failed to update data: ${connection.responseCode}")
		}
		notes = newNote
	}
}

