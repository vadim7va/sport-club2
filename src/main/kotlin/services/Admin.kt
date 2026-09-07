package org.example.services
import org.example.people.Client
import org.example.subscriptions.Subscription
import org.example.printMenu


class Admin{
    var sportClub: SportClub = SportClub()
    var subscriptions: MutableList<Subscription> = mutableListOf()

    fun start(subscriptions: MutableList<Subscription> ) {
        this.subscriptions = subscriptions
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
        subscriptions.forEachIndexed { i, sub -> println("${i + 1}: ${sub.name}") }
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
        //TODO Перенести в отдельный метод

        println("Choose subscription for client:")
        subscriptions.forEachIndexed { i, sub -> println("${i + 1}: ${sub.name}") }
        val subChoice = readln().toIntOrNull()
        val selectedSubscription = if (subChoice != null && subChoice in 1..subscriptions.size) subscriptions[subChoice - 1] else null
        if (selectedSubscription == null)
            return

        println("Enter the login")
        val login =readln()
        println("Enter the password")
        val password =readln()
        sportClub.clients.add(
            Client(name, age, selectedSubscription, login, password)


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
                    //TODO Перенести в отдельный метод
            // Добавляем новый ключ для абонемента
            println("Choose subscription for client:")
            subscriptions.forEachIndexed { i, sub -> println("${i + 1}: ${sub.name}") }
            val subChoice = readln().toIntOrNull()
            val selectedSubscription= if (subChoice != null && subChoice in 1..subscriptions.size) subscriptions[subChoice - 1] else null
            if (selectedSubscription == null)
                return
            client.subscription = selectedSubscription

            // Сохраняем изменения обратно в список клиентов
            sportClub.clients[d - 1] = client
            println("Client updated: ${client.name}")
        } else {
            println("Invalid choice")
        }
    }
}
