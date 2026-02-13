package com.mycompany.myproject.demo.membership

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DiscountCalculatorTest {

    @Test
    fun `regular user should pay original price`() {
        val calculator = DiscountFactory.createFor(MembershipType.REGULAR)
        val finalPrice = calculator.calculate(100.0)
        assertEquals(100.0, finalPrice)
    }

    @Test
    fun `VIP user should get 10 percent discount`() {
        val calculator = DiscountFactory.createFor(MembershipType.VIP)
        val finalPrice = calculator.calculate(100.0)
        assertEquals(90.0, finalPrice)
    }

    @Test
    fun `SuperVIP user should get 20 percent discount`() {
        val calculator = DiscountFactory.createFor(MembershipType.SUPER_VIP)
        val finalPrice = calculator.calculate(100.0)
        assertEquals(80.0, finalPrice)
    }

    @Test
    fun `should apply additional 50 discount when total exceeds 1000 for SuperVIP`() {
        // 計算邏輯: 2000 - (2000 * 0.2) - 50 = 1550.0
        val calculator = DiscountFactory.createFor(MembershipType.SUPER_VIP)
        val finalPrice = calculator.calculate(2000.0)
        assertEquals(1550.0, finalPrice)
    }

    @Test
    fun `should not result in negative price`() {
        // 測試極端情況：如果折扣大於總價
        val extremeCalculator = DiscountCalculator(listOf(
            ThresholdDiscount(java.math.BigDecimal("10.0"), java.math.BigDecimal("100.0"))
        ))
        val finalPrice = extremeCalculator.calculate(50.0)
        assertEquals(0.0, finalPrice)
    }
}
