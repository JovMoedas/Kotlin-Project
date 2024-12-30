package com.example.plugins

import com.example.route.routeUser
import io.ktor.server.application.*

fun Application.configureRouting() {
    routeUser()
}
