package org.example.services

import org.example.people.Client
import org.example.printMenu
import kotlin.collections.get
import kotlin.text.set

class Admin{
    var sportClub: SportClub = SportClub()
    var subs: MutableList<String> = mutableListOf("silver","gold","premium")

    fun start() {
        while (true) {
            printClients()
            printMenu()
            val choice = readln()
            when (choice) {
                "1" -> addClient()
                "2" -> deleteClient()
                "3" -> editClient()
                else -> println("There is no such menu item")
            }
            println("Do you want to continue as admin? (yes/no)")
            if (readln().lowercase() != "yes") break
        }
    }
    fun printClients() {
        println("Clients:")
        sportClub.clients.forEachIndexed { i, client -> println("${i + 1}: ${client.name}") }

    }
    fun printSubs() {
        println("Subs:")
        subs.forEachIndexed { i, sub -> println("${i + 1}: $sub") }
    }

    fun deleteClient() {
        printClients()
        println("Choose client")
        val d = readln().toIntOrNull()
        if (d != null && d in 1..sportClub.clients.size) {
            sportClub.clients.removeAt(d-1)
            println("Remove success")
        }
    }

    fun addClient() {
        println("Enter client name:")
        val name = readln()
        println("Client age")
        val age = readln().toInt()
        println("Choose subscription for client:")
        subs.forEachIndexed { i, sub -> println("${i + 1}: $sub") }
        val subChoice = readln().toIntOrNull()
        val subscription = if (subChoice != null && subChoice in 1..subs.size) subs[subChoice - 1] else ""
        println("Enter the login")
        val login =readln()
        println("Enter the password")
        val password =readln()
        sportClub.clients.add(
            Client(name, age, subscription, login, password)


        )
        println("Client added!")
    }
    fun editClient(){
        printClients()
        println("Choose client to edit:")
        val d = readln().toIntOrNull()

        if (d != null && d in 1..sportClub.clients.size) {
            val client: Client = sportClub.clients[d - 1] // делаем изменяемую копию

            // Редактируем имя
            println("Enter new name (current: ${client.name}):")
            val newName = readln()
            client.name = newName

            // Редактируем возраст
            println("Enter new age (current: ${client.age}):")
            val newAge = readln().toIntOrNull()
            if (newAge != null) {
                client.age = newAge
            }

            // Добавляем новый ключ для абонемента
            println("Choose subscription for client:")
            subs.forEachIndexed { i, sub -> println("${i + 1}: $sub") }
            val subChoice = readln().toIntOrNull()
            val subscription = if (subChoice != null && subChoice in 1..subs.size) subs[subChoice - 1] else ""
            client.subscription = subscription

            // Сохраняем изменения обратно в список клиентов
            sportClub.clients[d - 1] = client
            println("Client updated: ${client.name}")
        } else {
            println("Invalid choice")
        }
    }
}
