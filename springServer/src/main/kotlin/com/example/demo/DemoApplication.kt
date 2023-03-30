package com.example.demo

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

@Serializable
data class Messages(val id: String, val text: String)


@SpringBootApplication
class DemoApplication

fun main(args: Array<String>) {
	runApplication<DemoApplication>(*args)
	val postResponse = post(DataClass(0.0, 0.0, 0.0, 0.0, listOf<String>(), listOf<String>(), listOf<String>()))
	val get_response = get()
	println(get_response)
}

@RestController
@RequestMapping("/messages")
class MessageResource(val service: MessageService) {
	@GetMapping
	fun index(): List<Messages> = service.findMessages()

	@PostMapping
	fun post(@RequestBody message: Messages) {
		service.post(message)
	}
}


data class DataClass(val width : Double, val height : Double,
					val xCoord : Double, val yCoord : Double,
					val titles : List<String>, val groups : List<String>, val contents : List<String>)

@Service
class MessageService {
	var messages: MutableList<Messages> = mutableListOf()

	fun findMessages() = messages
	fun post(message: Messages) {
		messages.add(message)
	}
}

fun get(): String {
	val client = HttpClient.newBuilder().build()
	val request = HttpRequest.newBuilder()
		.uri(URI.create("http://localhost:8080/messages"))
		.GET()
		.build()

	val response = client.send(request, HttpResponse.BodyHandlers.ofString())
	return response.body()
}

fun post(message: DataClass): String {
	val string = Json.encodeToString(message)
	println(string)

	val client = HttpClient.newBuilder().build();
	println(client)
	val request = HttpRequest.newBuilder()
		.uri(URI.create("http://localhost:8080/messages"))
		.header("Content-Type", "application/json")
		.POST(HttpRequest.BodyPublishers.ofString(string))
		.build()

	println(request)

	val response = client.send(request, HttpResponse.BodyHandlers.ofString());
	println(response.statusCode())
	return response.body()
}