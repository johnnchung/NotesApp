import kotlinx.coroutines.*

suspend fun main() {                                // A function that can be suspended and resumed later (suspend)
    val start = System.currentTimeMillis()
    coroutineScope {              // Create a scope for starting coroutines (coroutineScope)
        for (i in 1..10) {
            launch {              // Start 10 concurrent tasks (launch)
                delay(3000L - i * 300)    // Pause their execution (delay)
                log(start, "Countdown: $i")
            }
        }
    }
    log(start, "Liftoff!")                    // Execution continues when all coroutines in the scope have finished
}

fun log(start: Long, msg: String) {
    println("$msg " + "(on ${Thread.currentThread().name}) " + "after ${(System.currentTimeMillis() - start)/1000F}s")
}