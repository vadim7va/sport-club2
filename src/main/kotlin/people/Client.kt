package org.example.people

import kotlin.Any

class Client: Person {

    var subscription: String = ""
    var login: String = ""
    var password: String = ""

    constructor(name: String, age: Int, subscription: String, login: String, password: String) : super(name, age) {
        this.subscription = subscription
        this.login = login
        this.password = password
    }
}