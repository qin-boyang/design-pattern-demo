package com.mycompany.myproject.demo.fee

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class FeeCalculatorTest {

    // 這裡我們預期會有一個 FeeFactory 來幫我們根據支付類型建立計算器

    @Test
    fun `Credit Card payment should charge 3 percent fee`() {
        // 100.0 的 3% 是 3.0
        val calculator = FeeFactory.create(PaymentType.CREDIT_CARD)
        val fee = calculator.calculateFee(100.0)
        assertEquals(3.0, fee)
    }

    @Test
    fun `Bank Transfer payment should charge a fixed fee of 5 dollars`() {
        // 無論金額多少，銀行轉帳固定收 5.0
        val calculator = FeeFactory.create(PaymentType.BANK_TRANSFER)
        val fee = calculator.calculateFee(200.0)
        assertEquals(5.0, fee)
    }

    @Test
    fun `Cash payment should have zero fee`() {
        // 現金支付免手續費
        val calculator = FeeFactory.create(PaymentType.CASH)
        val fee = calculator.calculateFee(500.0)
        assertEquals(0.0, fee)
    }

    @Test
    fun `Credit Card fee should handle rounding correctly`() {
        // 測試邊界：10.0 的 3% 是 0.3
        val calculator = FeeFactory.create(PaymentType.CREDIT_CARD)
        val fee = calculator.calculateFee(10.0)
        assertEquals(0.3, fee)
    }
}