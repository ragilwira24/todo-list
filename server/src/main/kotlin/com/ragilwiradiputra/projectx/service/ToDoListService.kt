package com.ragilwiradiputra.projectx.service

import com.ragilwiradiputra.projectx.model.ToDoList
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicInteger

object ToDoListService {
    private val toDoLists = ConcurrentHashMap<Int, ToDoList>()
    private val idCounter = AtomicInteger(1)

    fun getToDoLists(): List<ToDoList> {
        return toDoLists.values.toList()
    }

    fun getToDoList(id: Int): ToDoList? {
        return toDoLists[id]
    }

    fun addToDoList(data: ToDoList): ToDoList {
        val id = idCounter.getAndIncrement()
        return saveOrUpdateProcess(id, data)
    }

    fun updateToDoList(id: Int, data: ToDoList): ToDoList? {
        if (toDoLists.containsKey(id)){
            return saveOrUpdateProcess(id, data)
        }

        return null
    }

    private fun saveOrUpdateProcess(id: Int, data: ToDoList): ToDoList {
        toDoLists[id] = data
        return data
    }

    fun deleteToDoList(id: Int): Boolean {
        return toDoLists.remove(id) != null
    }

}