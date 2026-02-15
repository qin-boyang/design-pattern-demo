package com.mycompany.myproject.demo.orderobserver

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class OrderObserverTest {

    @Test
    fun `should notify all attached observers when state changes`() {
        // 1. Arrange
        val order = Order()
        val emailService = SpyObserver()
        val smsService = SpyObserver()

        order.attach(emailService)
        order.attach(smsService)

        // 2. Act
        order.operate(Command.Pay)

        // 3. Assert
        // We verify the observers were called with the correct state name
        assertEquals("Paid", emailService.lastReceivedState)
        assertEquals("Paid", smsService.lastReceivedState)
        assertEquals(1, emailService.notificationCount)
    }

    @Test
    fun `should not notify detached observers`() {
        val order = Order()
        val emailService = SpyObserver()

        order.attach(emailService)
        order.detach(emailService)

        order.operate(Command.Pay)

        assertEquals(null, emailService.lastReceivedState)
        assertEquals(0, emailService.notificationCount)
    }

    // --- A "Spy" is a simple test double that records what happened ---
    class SpyObserver : OrderObserver {
        var lastReceivedState: String? = null
        var notificationCount = 0

        override fun onStateChanged(stateName: String) {
            lastReceivedState = stateName
            notificationCount++
        }
    }
}
