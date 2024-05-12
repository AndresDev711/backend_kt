package com.dev.andre.routes

import com.dev.andre.models.User
import com.dev.andre.mysql.DbConnection
import com.dev.andre.mysql.entity.EntityUser
import com.dev.andre.mysql.model.UserModel
import com.dev.andre.util.GenericResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.ktorm.database.Database
import org.ktorm.dsl.insert


private val users = mutableListOf(
    User(id = 1, name = "Andres", email = "andres@gmail.com"),
    User(id = 2, name = "Andre", email = "andre@gmail.com"),
)

val db: Database = DbConnection.getDatabaseInstance()

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

        post("/register") {
            val user: UserModel = call.receive()
            println(user)
            val rowAffected = db.insert(EntityUser) {
                set(it.id, user.id)
                set(it.uuid, user.uuid)
                set(it.email, user.email)
                set(it.password, user.password)
                set(it.roleId, 1)
            }

            if (rowAffected > 0) {
                call.respondText(
                    status = HttpStatusCode.OK,
                    text = "$rowAffected NEW REGISTER"
                )
            } else {
                call.respondText("No hay usuarios", status = HttpStatusCode.BadRequest)
            }
        }
//        put { }
//        patch { }
//        delete { }
    }
}