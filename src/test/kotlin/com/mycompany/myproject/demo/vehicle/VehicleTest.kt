package com.mycompany.myproject.demo.vehicle

import kotlin.test.Test
import kotlin.test.assertEquals

class VehicleTest {

    @Test
    fun `test car`() {
        val car = Car("Camry", "Toyota")
        val driving = DrivingSomething(car)
        val result = driving.drive()
        assertEquals("Camry is a car", result)
    }

    @Test
    fun `test truck`() {
        val truck = Truck("F150", true)
        val driving = DrivingSomething(truck)
        val result = driving.drive()
        assertEquals("F150 is a truck", result)
    }

    @Test
    fun `test suv`() {
        val suv = SUV("Rav4", 2026)
        val driving = DrivingSomething(suv)
        val result = driving.drive()
        assertEquals("Rav4 is a SUV", result)
    }
}