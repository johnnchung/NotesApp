@file:OptIn(DelicateCoroutinesApi::class)

import kotlinx.coroutines.*
import java.net.URL

val ENDPOINT = "http://kotlin-book.bignerdranch.com/2e/flight"
fun fetchData(): String = URL(ENDPOINT).readText()

//fun main() {
//    println("Started")
//    GlobalScope.launch {
//        val data = fetchData()
//        println(data) // this is never printed!!
//    }
//    println("Finished")
//}

fun main() {
    runBlocking {
        println("Started")
        launch {
            val data = fetchData()
            println(data)
        }
        println("Finished")
    }
}
