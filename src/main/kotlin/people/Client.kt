package org.example.people

import org.example.subscriptions.Subscription


class Client: Person {

    var subscription: Subscription?
    var login: String = ""
    var password: String = ""
    var trainer: Trainer? =null

    constructor(name: String, age: Int, subscription: Subscription?, login: String, password: String) : super(name, age) {
        this.subscription = subscription
        this.login = login
        this.password = password
    }
    constructor(name: String,
                age: Int,
                subscription: Subscription?,
                login: String,
                password: String,
                trainer: Trainer) : this(name, age,subscription,login,password) {
         this.trainer = trainer
    }
}
