package net.codebot.original

/*
 * Decorator Pattern Example
 * Design Patterns in Kotlin Episode #2
 * Alexey Soshin & Sebastian Aigner
 * https://www.youtube.com/watch?v=aW1iR0Mmitk
 *
 * Reproduced by Jeff Avery, for CS 346.
 * This is the original, motivating example.
 */

fun main() {
    val logger = Logger()
    val cache = Cache()
    val request = Request("http://example.com")
    val response = processRequest(request, logger, cache)
    println("Results: ${response}")
}

data class Request(val endpoint: String)
data class Response(val body: String)

class Logger {
    fun log(message: String) {
        println("Logger: $message")
    }
}

class Cache {
    val cache = mutableMapOf<Request, Response>()
    fun put(request: Request, response: Response) {
        cache.put(request, response)
    }

    fun get(request: Request): Response? {
        return cache[request]
    }
}

fun processRequest(request: Request, logger: Logger, cache: Cache): Response {
    logger.log(request.toString())
    val cached = cache.get(request) ?: run {
        val response = Response("You called ${request.endpoint}")
        cache.put(request, response)
        response
    }
    return cached
}