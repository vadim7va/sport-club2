package org.example.services
import org.example.people.Client
import org.example.subscriptions.Subscription



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
                "4" -> setSubscription()
                "5" -> addSubscription()
                "6" -> editSubscription()
                "7" -> deleteSubscription()
                "8" -> viewClientProfile()
                else -> println("There is no such menu item")
            }
            println("Do you want to continue as admin? (yes/no)")
            if (readln().lowercase() != "yes") break
        }
    }
    fun setSubscription() {
        printClients()
        println()
        val clientChoice = readln().toIntOrNull()
        val selectedClient: Client? = if (clientChoice != null && clientChoice in 1..sportClub.clients.size) sportClub.clients [clientChoice - 1] else null
        val selectedSubscription: Subscription?= chooseSubscription()
        selectedClient!!.subscription = selectedSubscription


    }

    fun printClients() {
        println("Clients:")
        sportClub.clients.forEachIndexed { i, client -> println("${i + 1}: ${client.name}") }

    }
    fun printSubs() {
        println("Subs:")
        subscriptions.forEachIndexed { i, sub -> println("${i + 1}: $sub")
        }
    }

    fun addSubscription() {
        println("Enter subscription name:")
        var name: String = readln()

        println("Enter subscription description:")
        var description: String = readln()

        println("Enter subscription price:")
        var price: Int = readln().toInt()

        subscriptions.add(Subscription(name, description, price))
    }

    fun editSubscription() {
        printSubs()

        println("Choose subscription to edit:")
        val d = readln().toIntOrNull()

        if (d != null && d in 1..subscriptions.size) {
            val subscription = subscriptions[d - 1]

            println("Enter new subscription name:")
            subscription.name = readln()

            println("Enter new subscription description:")
            subscription.description = readln()

            println("Enter new subscription price:")
            subscription.price = readln().toInt()

            println("Subscription updated!")
        }
    }

    fun deleteSubscription() {
        printSubs()

        println("Choose subscription to delete:")
        val d = readln().toIntOrNull()

        if (d != null && d in 1..subscriptions.size) {
            subscriptions.removeAt(d - 1)
            println("Subscription deleted!")
        }
    }

    fun viewClientProfile() {
        printClients()

        println("Choose client:")
        val d = readln().toIntOrNull()
        if (d != null && d in 1..sportClub.clients.size) {
            val client = sportClub.clients[d - 1]
            println("Name: ${client.name}")
            println("Age: ${client.age}")
            println("Subscription: ${client.subscription?.name}")
            println("Login: ${client.login}")
        }
    }


    // Выводит доступные действия для администратора
    fun printMenu(){
        println("Sport club\n1. add client\n2. delete client\n3. edit client\n4. set subscription\n5. add subscription\n6. edit subscription\n7. delete subscription\n8. view client profile")
    }

    fun chooseSubscription (): Subscription?{
        println("Choose subscription for client:")
        subscriptions.forEachIndexed { i, sub -> println("${i + 1}: ${sub.name}") }
        val subChoice = readln().toIntOrNull()
        val selectedSubscription: Subscription? = if (subChoice != null && subChoice in 1..subscriptions.size) subscriptions[subChoice - 1] else null
        return selectedSubscription
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

        val selectedSubscription = chooseSubscription()
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
            val selectedSubscription = chooseSubscription()
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
