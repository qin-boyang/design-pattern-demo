package com.mycompany.myproject.demo.order

sealed class OrderState {
    abstract fun pay(): OrderState
    abstract fun ship(): OrderState
    abstract fun cancel(): OrderState
    abstract fun name(): String
    abstract fun canAddItem(): Boolean

    data object New: OrderState() {
        override fun pay(): OrderState {
            return Paid
        }

        override fun ship(): OrderState {
            throw IllegalStateException("Cannot ship a new order. Pay first!")
        }
        override fun cancel(): OrderState {
            return Cancelled
        }

        override fun name(): String {
            return "New"
        }

        override fun canAddItem(): Boolean {
            return true
        }
    }

    data object Paid: OrderState() {
        override fun pay(): OrderState {
            throw IllegalStateException("already paid")
        }

        override fun ship(): OrderState {
            return Shipped
        }
        override fun cancel(): OrderState {
            return Cancelled
        }

        override fun name(): String {
            return "Paid"
        }
        override fun canAddItem(): Boolean {
            throw IllegalStateException("Cannot add items to a paid order.")
        }
    }

    data object Shipped: OrderState() {
        override fun pay(): OrderState {
            throw IllegalStateException("already shipped")
        }

        override fun ship(): OrderState {
            throw IllegalStateException("already shipped")
        }
        override fun cancel(): OrderState {
            throw IllegalStateException("Cannot cancel a shipped order.")
        }

        override fun name(): String {
            return "Shipped"
        }

        override fun canAddItem(): Boolean {
            throw IllegalStateException("Cannot add items to a shipped order.")
        }
    }

    data object Cancelled: OrderState() {
        override fun pay(): OrderState {
            throw IllegalStateException("already cancelled")
        }

        override fun ship(): OrderState {
            throw IllegalStateException("already cancelled")
        }
        override fun cancel(): OrderState {
            throw IllegalStateException("already cancelled")
        }

        override fun name(): String {
            return "Cancelled"
        }

        override fun canAddItem(): Boolean {
            throw IllegalStateException("Cannot add items to a cancelled order.")
        }
    }
}

enum class Command {Pay, Ship, Cancel}

class Order {

    var items: MutableList<String> = mutableListOf()
    var state: OrderState = OrderState.New

    fun operate(command : Command) {
        when (command) {
            Command.Pay -> state = state.pay()
            Command.Ship -> state = state.ship()
            Command.Cancel -> state = state.cancel()
        }
    }
    fun getStateName() = state.name()
    fun addItem(item: String) {
        if (state.canAddItem()) {
            items.add(item)
        }
    }
}