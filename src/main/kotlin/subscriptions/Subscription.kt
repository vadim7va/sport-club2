package org.example.subscriptions


class Subscription {
    var name: String = ""
    var description: String = ""
    var price:Int = 0

    constructor(name: String, description: String, price: Int) {
        this.name = name
        this.description = description
        this.price = price
    }
}
