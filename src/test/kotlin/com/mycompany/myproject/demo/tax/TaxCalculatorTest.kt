package com.mycompany.myproject.demo.tax

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TaxCalculatorTest {

    @Test
    fun `local book should be tax free`() {
        // 本地書籍：0% 稅
        val calculator = TaxFactory.create(category = "Book", isImported = false)
        val tax = calculator.calculateTax(100.0)
        assertEquals(0.0, tax)
    }

    @Test
    fun `local music CD should have 10 percent tax`() {
        // 本地普通商品：10% 稅
        val calculator = TaxFactory.create(category = "General", isImported = false)
        val tax = calculator.calculateTax(100.0)
        assertEquals(10.0, tax)
    }

    @Test
    fun `imported chocolate should have 5 percent import tax only`() {
        // 進口食物：免消費稅(0%) + 進口稅(5%) = 5%
        val calculator = TaxFactory.create(category = "Food", isImported = true)
        val tax = calculator.calculateTax(100.0)
        assertEquals(5.0, tax)
    }

    @Test
    fun `imported perfume should have 15 percent combined tax`() {
        // 進口普通商品：消費稅(10%) + 進口稅(5%) = 15%
        val calculator = TaxFactory.create(category = "General", isImported = true)
        val tax = calculator.calculateTax(100.0)
        assertEquals(15.0, tax)
    }
}
