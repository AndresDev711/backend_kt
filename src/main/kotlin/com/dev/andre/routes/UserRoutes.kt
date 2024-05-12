package com.dev.andre.routes

import com.dev.andre.models.User
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


private val users = mutableListOf(
    User(id = 1, name = "Andres", email = "andres@gmail.com"),
    User(id = 2, name = "Andre", email = "andre@gmail.com"),
)

fun Route.userRouting() {
    route("/user") {
        get {
            if (users.isNotEmpty()) {
                call.respond(users)
            } else {
                call.respondText("No hay usuarios", status = HttpStatusCode.OK)
            }
        }
        get("{id?}") {
            val id = call.parameters["id"] ?: return@get call.respondText(
                text = "ID NO ENCONTRADO",
                status = HttpStatusCode.BadRequest
            )

            val user = users.find { user -> user.id == id.toInt() }
                ?: return@get call.respondText(
                    text = "USUARIO CON ID: $id NO ENCONTRADO",
                    status = HttpStatusCode.NotFound
                )

            call.respond(user)

        }
//        post { }
//        put { }
//        patch { }
//        delete { }
    }
}