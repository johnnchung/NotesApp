import kotlinx.coroutines.*

// Taken from https://kotlinlang.org
fun main() = runBlocking {
    repeat(100_000) {      // launch a lot of coroutines
        launch {
            delay(5000L)
            print(".")
        }
    }
}