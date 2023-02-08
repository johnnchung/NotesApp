package net.codebot.decorator

/*
 * Decorator Pattern Example
 * Design Patterns in Kotlin Episode #2
 * Alexey Soshin & Sebastian Aigner
 * https://www.youtube.com/watch?v=aW1iR0Mmitk
 *
 * Reproduced by Jeff Avery, for CS 346.
 * This is the optimized version using the decorator pattern
 * Also uses singletons for logger and cache
 */

fun main() {
    val request = Request("http://example.com")

    // these are all valid, just change a single line to change behaviour
    // val processor: Processor = RequestProcessor()
    // val processor: Processor = CacheProcessor(Cache, RequestProcessor())
    // val processor: Processor = LoggingProcessor(Logger, RequestProcessor())
    // val processor: Processor = LoggingProcessor(Logger, CacheProcessor(Cache, RequestProcessor()))

//    val processor: Processor = RequestProcessor()
//    val processor: Processor = LoggingProcessor(Logger, RequestProcessor())

    val processor: Processor = LoggingProcessor(Logger, CacheProcessor(Cache, RequestProcessor()))
    println("Results: ${processor.process(request)}")
}

data class Request(val endpoint: String)
data class Response(val body: String)

// object aka singleton
object Logger {
    fun log(message: String) {
        println("Logger: $message")
    }
}

// object aka singleton
object Cache {
    val cache = mutableMapOf<Request, Response>()
    fun put(request: Request, response: Response) {
        cache.put(request, response)
    }

    fun get(request: Request): Response? {
        return cache[request]
    }
}

interface Processor {
    fun process(request: Request): Response
}

class LoggingProcessor(val logger: Logger, val processor: Processor) : Processor {
    override fun process(request: Request): Response {
        logger.log(request.toString())
        return processor.process(request)
    }
}

class CacheProcessor(val cache: Cache, val processor: Processor): Processor {
    override fun process(request: Request): Response {
        val cached = cache.get(request)
        return if (cached != null) {
            cached
        } else {
            val response = processor.process(request)
            cache.put(request, response)
            response
        }
    }
}

class RequestProcessor(): Processor {
    override fun process(request: Request): Response {
        return Response("You called ${request.endpoint}")
    }
}