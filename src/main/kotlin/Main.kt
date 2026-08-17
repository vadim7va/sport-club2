// Создаем изменяемый список клиентов, где каждый клиент представлен Map с ключами "name" и "age"
package org.example

import kotlin.collections.forEachIndexed
import kotlin.contracts.Returns

open class Person {
    var name: String = ""
    var age: Int = 0

    constructor(name: String, age: Int) {
        this.name = name
        this.age = age
    }
}


class Client: Person{

    var subscription: String = ""
    var login: String= ""
    var password: String = ""

    constructor(name: String, age: Int, subscription: String, login: String, password: String):super(name, age){
        this.subscription = subscription
        this.login = login
        this.password = password
    }




}

class SportClub{
    var clients: MutableList <Client> = mutableListOf(

        Client("Vitalik",22, "gold", "Vitalik","qwerty123"),
        Client("Petr",10, "silver","Petr","qwerty123"),
        )

    fun register() {
        println("REGISTERING A NEW CLIENT")

        println("Enter your name:")
        val name = readln()

        println("Enter your age:")
        val age = readln().toIntOrNull()
        if (age == null || age < 0) {
            println("Invalid age. Registration cancelled.")
            return
        }

        println("Choose subscription (1 - silver, 2 - gold, 3 - premium):")
        val subChoice = readln().toIntOrNull()
        val subscription = when (subChoice) {
            1 -> "silver"
            2 -> "gold"
            3 -> "premium"
            else -> {
                println("Invalid subscription choice. Default: silver")
                "silver"
            }
        }

        println("Create login:")
        val login = readln()
    fun go(){
        println("Enter the login")
        val login =readln()
        println("Enter the password")
        val password =readln()
        if (auth(login, password))
            println("Auth success")
        else
            println("Auth failed")
    }
    fun auth(login:String, password:String): Boolean{
        for (client in clients) {
          if (client.login.equals(login)&&client.password.equals(password))
              return true
        }
        return false
    }
}


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




// Запускает программу и предлагает выбор между режимом администратора и пользователя
fun main() {
    println("1. admin\n 2. user")
    var d = readln()
    if (d=="1")
    {
        var admin: Admin = Admin()
        admin.start()
    }
    else
    {var sportClub: SportClub = SportClub()
        sportClub.go()
    }

}





// Выводит доступные действия для администратора
fun printMenu(){
    println("Sport club\n1. add client\n2. delete client\n3. edit client")

}














// Позволяет пользователю выбрать и "купить" абонемент из доступных
//fun user() {
//    printSubs()
//    println("Choose a subs by number:")
//    val d = readln().toIntOrNull()
//    if (d != null && d in 1..subs.size) {
//        val sub = subs[d - 1] // корректируем индекс
//        println("You have bought a subs: $sub")
//    } else {
//        println("Invalid choice")
//    }
//}

