package com.example

import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        // Servir arquivos estáticos da pasta 'resources/static'
        static("/static") {
            // O caminho da pasta no seu sistema de arquivos
            resources("static")
        }

        // Rota para redirecionar a raiz para o index.html
        get("/") {
            // Serve o arquivo index.html quando acessar a raiz do servidor
            call.respondRedirect("/static/index.html")
        }
    }
}