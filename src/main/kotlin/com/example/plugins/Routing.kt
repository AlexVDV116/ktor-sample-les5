package com.example.plugins

import com.example.routes.itemRoutes
import com.example.routes.reservationRoutes
import com.example.routes.userRoutes
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    // Default example of routing
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
    // Adding our own routes
        userRoutes()
        itemRoutes()
        reservationRoutes()
    }
}
