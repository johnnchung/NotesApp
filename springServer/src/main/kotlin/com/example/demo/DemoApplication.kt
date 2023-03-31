package com.example.demo

import kotlinx.serialization.Serializable
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*

@Serializable
data class Messages(val id: String, val text: String)

@SpringBootApplication
class DemoApplication

@Serializable
data class DataC(val width : Double, val height : Double,
				val xCoord : Double, val yCoord : Double,
				val titles : List<String>, val groups : List<String>, val contents : List<String>) {}

fun main(args: Array<String>) {
	runApplication<DemoApplication>(*args)
}


@RestController
@RequestMapping("/notes")
class MessageResource(val service: MessageService) {
	@GetMapping
	fun index(): List<DataC> = service.getNotesData()

	@PostMapping
	fun post(@RequestBody note: DataC) {
		service.post(note)
	}
	@PutMapping("/{title}")
	fun put(@PathVariable title: String, @RequestBody note: DataC) {
		// Should be the new fields that the user enters
		val dataC = DataC(
			width = 10.0,
			height = 20.0,
			xCoord = 30.0,
			yCoord = 40.0,
			titles = listOf("Title 1", "Title 2"),
			groups = listOf("Group 1", "Group 2"),
			contents = listOf("Content 1", "Content 2")
		)
		service.put(title, dataC)
	}
}

@Service
class MessageService {
	var notes: MutableList<DataC> = mutableListOf()
	fun getNotesData() = notes
	fun post(note: DataC) {
		notes.add(note)
	}

	fun put(changeTitle: String, newNote: DataC) {
		if (notes.contains(newNote)) {
			for(note in notes) {
				for(noteTitle in note.titles) {
					if(noteTitle == changeTitle) {
						val idx = notes.indexOf(note)
						notes[idx] = newNote
					}
				}
			}
		} else {
			notes.add(newNote)
		}
	}
}

