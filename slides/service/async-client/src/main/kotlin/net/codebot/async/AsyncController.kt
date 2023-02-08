package net.codebot.async

import kotlinx.coroutines.*
import javafx.fxml.FXML
import javafx.scene.control.Label
import java.net.URL

val ENDPOINT = "http://kotlin-book.bignerdranch.com/2e/flight"
fun fetchData(): String = URL(ENDPOINT).readText()

class AsyncController {
    @FXML
    private lateinit var asyncText: Label

    @FXML
    private fun onAsyncButtonClick() {
        runBlocking {
            println("Before launch")
            launch {
                asyncText.text = fetchData()
            }
            println("After launch")
        }
        println("After runBlocking")
    }
}