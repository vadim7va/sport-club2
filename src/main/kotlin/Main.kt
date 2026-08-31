// Создаем изменяемый список клиентов, где каждый клиент представлен Map с ключами "name" и "age"
package org.example

import org.example.services.Admin
import org.example.services.SportClub
import org.example.subscriptions.Subscription

// Запускает программу и предлагает выбор между режимом администратора и пользователя
fun main() {
    var subscription: MutableList <Subscription> = mutableListOf(
        Subscription("silver", "Standart subscription", 2500),
        Subscription( "gold", "more features subscription", 5000),
        Subscription("premium", "for the majors", 10000)
    )

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