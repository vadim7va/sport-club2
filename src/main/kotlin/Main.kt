// Создаем изменяемый список клиентов, где каждый клиент представлен Map с ключами "name" и "age"
package org.example

import org.example.people.Trainer
import org.example.services.Admin
import org.example.services.SportClub
import org.example.subscriptions.Subscription
import javax.swing.tree.RowMapper

// Запускает программу и предлагает выбор между режимом администратора и пользователя
fun main() {
    var subscriptions: MutableList <Subscription> = mutableListOf(
        Subscription("silver", "Standart subscription", 2500),
        Subscription( "gold", "more features subscription", 5000),
        Subscription("premium", "for the majors", 10000)
    )
    var trainers: MutableList<Trainer> = mutableListOf(
        Trainer(
            "Roma",
            45,
            mutableListOf(),
            "Roma",
            "qwerty"
        )

    )
    println("1. admin\n 2. user")
    var d = readln()
    if (d=="1")
    {
        var admin: Admin = Admin()
        admin.start(subscriptions)
    }
    else
    {var sportClub: SportClub = SportClub()
        sportClub.go(subscriptions)
    }
}

