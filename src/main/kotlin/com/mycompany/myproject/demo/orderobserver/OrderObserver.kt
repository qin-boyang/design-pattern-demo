package com.mycompany.myproject.demo.orderobserver

interface OrderObserver {

    fun onStateChanged(stateName: String)
}

class Order {
    var state: OrderState = OrderState.New
    var observers: MutableList<OrderObserver> = mutableListOf()
    fun attach(observer: OrderObserver) {
        observers.add(observer)
    }

    fun detach(observer: OrderObserver) {
        observers.remove(observer)
    }

    fun operate(command : Command) {
        state = when (command) {
            Command.Pay -> state.pay()
            Command.Ship -> state.ship()
            Command.Cancel -> state.cancel()
        }
        observers.forEach { it.onStateChanged(state.name()) }
    }
}

enum class Command {Pay, Ship, Cancel}

sealed class OrderState {
    abstract fun pay(): OrderState
    abstract fun ship(): OrderState
    abstract fun cancel(): OrderState
    abstract fun name(): String

    object New : OrderState() {
        override fun pay(): OrderState = Paid
        override fun ship(): OrderState = this
        override fun cancel(): OrderState = Cancelled
        override fun name(): String = "New"
    }

    object Paid : OrderState() {
        override fun pay(): OrderState = this
        override fun ship(): OrderState = Shipped
        override fun cancel(): OrderState = Cancelled
        override fun name(): String = "Paid"
    }
    object Shipped : OrderState() {
        override fun pay(): OrderState = this
        override fun ship(): OrderState = this
        override fun cancel(): OrderState = this
        override fun name(): String = "Shipped"
    }

    object Cancelled : OrderState() {
        override fun pay(): OrderState = this
        override fun ship(): OrderState = this
        override fun cancel(): OrderState = this
        override fun name(): String = "Cancelled"
    }


}