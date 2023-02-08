import kotlinx.coroutines.*

// SIMPLE
fun main() = runBlocking { // this: CoroutineScope
    launch { // launch a new coroutine and continue
        delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
        println("World!") // print after delay
    }
    println("Hello") // main coroutine continues while a previous one is delayed
}

// EXTRACT FUNCTION
//fun main() = runBlocking { // this: CoroutineScope
//    launch { doWorld() }
//    println("Hello")
//}
//
//// suspending function
//suspend fun doWorld() {
//    delay(1000L)
//    println("World!")
//}