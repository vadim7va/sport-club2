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
            if (auth(login, password)){
               val client = clients.find {client -> client.login.equals(login)  }
                if (client == null){
                    println("Client is null")
                return
                }
            userMenu(client)
        }
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

    fun userMenu(currentClient: Client) {
        while (true) {
            println(" CUSTOM MENU")
            println("1. View available subscriptions")
            println("2. Choose a subscription")
            println("3. My profile")
            println("4. Exit")
            print("Select an action: ")

            when (readln()) {
                "1" -> showSubscriptions()
                "2" -> chooseSubscription(currentClient)
                "3" -> showProfile(currentClient)
                "4" -> {
                    println("Goodbye!")
                    return
                }
                else -> println("Wrong choice")
            }
        }
    }

    fun showSubscriptions () {
        println(" AVAILABLE SUBSCRIPTIONS")
        val subs = listOf(
            "silver" to "silver - 1500 руб/мес",
            "gold" to "gold - 2500 руб/мес",
            "premium" to "premium - 4000 руб/мес"
        )
        subs.forEachIndexed { index, sub ->
            println("${index + 1}. ${sub.second}")
        }
    }

    fun chooseSubscription(client: Client) {
        println("CHOOSING A SUBSCRIPTION")
        val subs = listOf("silver", "gold", "premium")
        val subsDisplay = listOf(
            "silver - 1500 руб/мес",
            "gold - 2500 руб/мес",
            "premium - 4000 руб/мес"
        )

        subsDisplay.forEachIndexed { index, sub ->
            println("${index + 1}. $sub")
        }

        print("Select a subscription number: ")
        val choice = readln().toIntOrNull()

        if (choice != null && choice in 1..subs.size) {
            val selectedSub = subs[choice - 1]
            client.subscription = selectedSub
            println("You have selected a subscription: ${subsDisplay[choice - 1]}")
            println("The subscription was successfully issued!")
        } else {
            println("Wrong choice. Try again.")
        }
    }

    fun showProfile(client: Client) {
        println(" USER PROFILE")
        println("Имя: ${client.name}")
        println("Возраст: ${client.age}")
        println("Логин: ${client.login}")
        println("Абонемент: ${if (client.subscription.isNotEmpty()) client.subscription else "Не выбран"}")
    }
}