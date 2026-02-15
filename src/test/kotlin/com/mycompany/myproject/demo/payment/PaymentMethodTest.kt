package com.mycompany.myproject.demo.payment

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class PaymentMethodTest {

    @Test
    fun `pay with credit card`() {
        val paymentMethod = PaymentMethod.create(PaymentMethodType.Card, "1234567890")
        val paymentFee = paymentMethod.calculatePaymentFee()
        assertEquals(0.5, paymentFee)
    }

    @Test
    fun `pay with cash`() {
        val paymentMethod = PaymentMethod.create(PaymentMethodType.Cash)
        val paymentFee = paymentMethod.calculatePaymentFee()
        assertEquals(0.0, paymentFee)
    }
}