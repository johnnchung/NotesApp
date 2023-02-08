package net.codebot

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import net.codebot.plugins.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "127.0.0.1") {
        configureRouting()
        configureSerialization()
    }.start(wait = true)
}
