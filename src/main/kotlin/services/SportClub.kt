package org.example.services

import org.example.people.Client
import org.example.subscriptions.Subscription

class SportClub {
    var clients: MutableList<Client> = mutableListOf(

        Client("Vitalik", 22, Subscription("silver", "Standart subscription", 2500), "Vitalik", "qwerty123"),
        Client("Petr", 10, Subscription("silver", "Standart subscription", 2500), "Petr", "qwerty123"),
    )
    var subscriptions: MutableList<Subscription> = mutableListOf()
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
     clients.add(Client(name, age, null, login, password ))


    }
    fun go(subscriptions: MutableList<Subscription>) {

        this.subscriptions = subscriptions
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


        subscriptions.forEachIndexed { index, sub ->
            println("${index + 1}. ${sub.name}")
        }

        print("Select a subscription number: ")
        val choice = readln().toIntOrNull()

        if (choice != null && choice in 1..subscriptions.size) {
            val selectedSub = subscriptions[choice - 1]

            client.subscription = selectedSub
            println("You have selected a subscription: ${subscriptions[choice - 1].name}")
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
        println("Абонемент: ${if (client.subscription != null) client.subscription!!.name else "Не выбран"}")
    }
}