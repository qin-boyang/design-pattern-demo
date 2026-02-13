package com.mycompany.myproject.demo.order

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class OrderTest {

    @Test
    fun `should allow shipping when order is Paid`() {
        val order = Order() // Initially "New"
        order.operate(Command.Pay)         // Move to "Paid"
        order.operate(Command.Ship)        // Move to "Shipped"

        assertEquals("Shipped", order.getStateName())
    }

    @Test
    fun `should throw error when shipping a New order`() {
        val order = Order() // "New"

        val exception = assertThrows<IllegalStateException> {
            order.operate(Command.Ship)
        }
        assertEquals("Cannot ship a new order. Pay first!", exception.message)
    }

    @Test
    fun `should allow cancellation from Paid state (refund)`() {
        val order = Order()
        order.operate(Command.Pay)
        order.operate(Command.Cancel)

        assertEquals("Cancelled", order.getStateName())
    }

    @Test
    fun `should not allow adding items after order is Shipped`() {
        val order = Order()
        order.operate(Command.Pay)
        order.operate(Command.Ship)

        val exception = assertThrows<IllegalStateException> {
            order.addItem("Laptop")
        }
        assertEquals("Cannot add items to a shipped order.", exception.message)
    }
}
