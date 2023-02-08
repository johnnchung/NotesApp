package net.codebot.client

/**
 * Sprint Client Demo
 * Copyright (c) 2022 Jeff Avery <jeffery.avery@uwaterloo.ca>
 *
 * This file may be freely distributed as long as this header remains intact.
 *
 * Demo for CS 346 that demonstrates the use of POST and GET methods.
 * These are pure HTTP requests and could work against any server that can
 * to handle this message class encoded as JSON.
 */

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import net.codebot.shared.SysInfo
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.time.Duration

// spring-server demo uses localhost:8080
// you should not need to change this address
val SERVER_ADDRESS = "http://127.0.0.1:8080/messages"

// matches the data class in the server demo
// the server will generate a unique ID if we pass in a null id
@Serializable
data class Message(val id: String?, val text: String)

fun main() {
    val post_response = post(Message("100", "Hello ${SysInfo.hostname}"))

    val get_response = get()
    println(get_response) // list of JSON objects from database
}

fun get(): String {
    val client = HttpClient.newBuilder()
        .version(HttpClient.Version.HTTP_2)
        .followRedirects(HttpClient.Redirect.NEVER)
        .connectTimeout(Duration.ofSeconds(20))
        .build()

    val request = HttpRequest.newBuilder()
        .uri(URI.create(SERVER_ADDRESS))
        .GET()
        .build()

    val response = client.send(request, HttpResponse.BodyHandlers.ofString())
    return response.body()
}

fun post(message: Message): String {
    val string = Json.encodeToString(message)

    val client = HttpClient.newBuilder().build();
    val request = HttpRequest.newBuilder()
        .uri(URI.create(SERVER_ADDRESS))
        .header("Content-Type", "application/json")
        .POST(HttpRequest.BodyPublishers.ofString(string))
        .build()

    val response = client.send(request, HttpResponse.BodyHandlers.ofString());
    return response.body()
}