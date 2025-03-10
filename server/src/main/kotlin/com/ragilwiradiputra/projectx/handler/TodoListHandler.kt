package com.ragilwiradiputra.projectx.handler

import com.ragilwiradiputra.projectx.model.ToDoList
import com.ragilwiradiputra.projectx.service.ToDoListService
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.toDoListRoutes(){
    route("to-do-lists"){
        get {
            val toDoLists: List<ToDoList> = ToDoListService.getToDoLists()
            call.respond(HttpStatusCode.OK, toDoLists)
        }

        get("/{id}"){
            val id = call.parameters["id"]?.toIntOrNull()
            if (id == null){
                call.respond(HttpStatusCode.BadRequest, "ID is required")
                return@get
            }

            val toDoList = ToDoListService.getToDoList(id)
            if (toDoList == null){
                call.respond(HttpStatusCode.NotFound, "To Do list not found")
                return@get
            }

            call.respond(HttpStatusCode.OK, toDoList)
        }

        post {
            val body = call.receiveNullable<ToDoList>()
            if (body == null){
                call.respond(HttpStatusCode.BadRequest, "Body is required")
                return@post
            }

            call.respond(HttpStatusCode.OK, ToDoListService.addToDoList(body))
        }
    }
}