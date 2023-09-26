package com.example.routes

import com.example.dao.DAOInMemoryImpl
import com.example.dao.daoInMemoryImpl
import com.example.dto.CreateUserDto
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.userRoutes() {
    route("user") {
        get {
            val users = daoInMemoryImpl.allUsers()
            call.respond(users)
        }

        get("{id?}") {
            val id = call.parameters["id"]
                ?.toIntOrNull()
                ?: return@get call.respondText(
                    text = "Missing id",
                    status = HttpStatusCode.BadRequest
                )
            val user = daoInMemoryImpl.user(id) ?: return@get call.respondText(
                text = "No user with id $id",
                status = HttpStatusCode.NotFound
            )

            call.respond(user)
        }

        post {
            val user = call.receive<CreateUserDto>()
            val newUser = daoInMemoryImpl.addUser(user.name, user.email)
            if (newUser != null) {
//                call.respondText(Json.encodeToString(newUser), status = HttpStatusCode.Created)
                call.respond(message = newUser, status = HttpStatusCode.Created)
            } else {
                // in this situation it is a server error
                call.respondText("User cannot be created", status = HttpStatusCode.NotImplemented)
            }
        }

        delete("{id?}") {
            val id = call.parameters["id"]
                ?. toIntOrNull()
                ?: return@delete call.respond(HttpStatusCode.BadRequest)
            if (daoInMemoryImpl.deleteUser(id)) {
                call.respondText("User removed correctly", status = HttpStatusCode.Accepted)
            } else {
                call.respondText("Not Found", status = HttpStatusCode.NotFound)
            }
        }
    }
}