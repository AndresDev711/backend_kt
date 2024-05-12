package com.dev.andre.plugins

import com.dev.andre.routes.userRouting
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello KTOR!")
        }
        userRouting()
    }
}
