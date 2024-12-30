package com.example

import io.ktor.server.engine.*
import io.ktor.server.tomcat.*
import com.example.plugins.*

fun main() {
    embeddedServer(Tomcat, port = 8080, host = "192.168.56.1") {
        configureRouting()
        configureSerialization()
    }.start(wait = true)
}
