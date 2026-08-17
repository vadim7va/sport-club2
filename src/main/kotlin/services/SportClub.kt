package org.example.services

import org.example.people.Client

class SportClub {
    var clients: MutableList<Client> = mutableListOf(

        Client("Vitalik", 22, "gold", "Vitalik", "qwerty123"),
        Client("Petr", 10, "silver", "Petr", "qwerty123"),
    )

    fun register() {
        println("REGISTERING A NEW CLIENT")

        println("Enter your login:")
        val login = readln()

        println("Enter your password:")
        val password = readln()

        println("Enter your name:")
        val name = readln()


        println("Enter your age:")
        val age = readln().toIntOrNull()
        if (age == null || age < 0) {
            println("Invalid age. Registration cancelled.")
            return
        }
     clients.add(Client(name, age, "", login, password ))


    }
    fun go() {
        println("1.login, 2. Register")
        val d = readln().toIntOrNull()
        if (d == 1 ){
            println("Enter the login")
            val login = readln()
            println("Enter the password")
            val password = readln()
            if (auth(login, password))
                println("Auth success")
            else
                println("Auth failed")
        } else {
            register()
        }


    }
    fun auth(login: String, password: String): Boolean {
        for (client in clients) {
            if (client.login.equals(login) && client.password.equals(password))
                return true
        }
        return false
    }
}
