package com.mycompany.myproject.demo.coffee

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CoffeeDecoratorTest {

    @Test
    fun `should calculate cost for simple coffee`() {
        val coffee: Coffee = SimpleCoffee()

        assertEquals(10.0, coffee.getCost())
        assertEquals("Simple Coffee", coffee.getDescription())
    }

    @Test
    fun `should calculate cost for coffee with milk and sugar`() {
        // We wrap the simple coffee inside Milk, then wrap that inside Sugar
        val myCoffee: Coffee = SugarDecorator(MilkDecorator(SimpleCoffee()))

        // 10.0 (Simple) + 2.0 (Milk) + 1.0 (Sugar) = 13.0
        assertEquals(13.0, myCoffee.getCost())
        assertEquals("Simple Coffee, Milk, Sugar", myCoffee.getDescription())
    }
}
