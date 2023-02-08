import kotlinx.coroutines.*

fun main() = runBlocking {
    // doWorld1()
    doWorld2()
}

suspend fun doWorld1() = coroutineScope {  // this: CoroutineScope inside suspended function
    launch {
        delay(3000L)
        println("World!")
    }
    println("Hello")
}

// Concurrently executes both sections
suspend fun doWorld2() = coroutineScope { // this: CoroutineScope
    launch {                    // coroutine 1
        delay(2000L)
        println("World 2")
    }
    launch {                    // coroutine 2
        delay(1000L)
        println("World 1")
    }
    println("Hello")
}