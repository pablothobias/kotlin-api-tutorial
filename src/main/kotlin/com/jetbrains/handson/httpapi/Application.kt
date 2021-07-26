package com.jetbrains.handson.httpapi

import io.ktor.application.Application
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.serialization.*
import com.jetbrains.handson.httpapi.routes.registerCustomerRoutes
import com.jetbrains.handson.httpapi.routes.registerOrderRoutes
import io.ktor.http.*
import io.ktor.response.*


fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module(testing: Boolean = false) {
    install(ContentNegotiation) {
        json()
    }
    install(StatusPages) {
       exception<Throwable> { e ->
           call.respondText(e.localizedMessage, ContentType.Text.Plain, HttpStatusCode.InternalServerError)
       }
    }
    install(CORS) {
        anyHost()
    }
    registerCustomerRoutes()
    registerOrderRoutes()
}
