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

@SpringBootApplication
class SpringServer

@Serializable
data class NotesSchema(val width : Double, val height : Double,
					   val xCoord : Double, val yCoord : Double,
					   val titles : List<String>, val groups : List<String>,
					   val contents : List<String>) {}

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
	var notes: NotesSchema = NotesSchema(0.0, 0.0, 0.0, 0.0, emptyList(), emptyList(), emptyList())

	// Retrieve list of notes
	fun getAllNotes(): NotesSchema {
		// TODO: Rather than reading from JSON locally, we can load this from the cloud service now
		var fileInput = FileInputStream("C:\\Users\\John Chung\\cs346-project\\application\\data.json")
		return Json.decodeFromStream(fileInput)
	}

	// Updates list of notes, creating a new one if it does not already exist
	fun put(newNote: NotesSchema) {
		// TODO: Rather than reading from JSON locally, we can load this from the cloud service now
		val file = FileOutputStream("C:\\Users\\John Chung\\cs346-project\\application\\data.json")
		val string = Json.encodeToString(newNote)
		file.write(string.toByteArray())
		notes = newNote
	}
}

