package com.mycompany.myproject.demo.marsrover

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RoverRotationTest {

    @Test
    fun `should rotate left from North to West`() {
        val rover = Rover(roverState = RoverState.N)
        rover.execute("L")
        assertEquals(RoverState.W, rover.roverState)
    }

    @Test
    fun `should rotate right from North to East`() {
        val rover = Rover(roverState = RoverState.N)
        rover.execute("R")
        assertEquals(RoverState.E, rover.roverState)
    }

    @Test
    fun `should rotate right four times to come back to North`() {
        val rover = Rover(roverState = RoverState.N)
        rover.execute("R")
        assertEquals(RoverState.E, rover.roverState)
        rover.execute("R")
        assertEquals(RoverState.S, rover.roverState)
        rover.execute("R")
        assertEquals(RoverState.W, rover.roverState)
        rover.execute("R")
        assertEquals(RoverState.N, rover.roverState)
    }

    @Test
    fun `should move North correctly`() {
        // 初始在 (0, 0) 面向北
        val rover = Rover(x = 0, y = 0, roverState = RoverState.N)
        rover.execute("M")
        assertEquals(0, rover.x)
        assertEquals(1, rover.y)
    }

    @Test
    fun `should handle complex movement`() {
        // 順序：(0,0,N) -> M -> R -> M -> (1,1,E)
        val rover = Rover(0, 0, RoverState.N)
        rover.execute("M")
        assertEquals(0, rover.x)
        assertEquals(1, rover.y)
        assertEquals(RoverState.N, rover.roverState)
        rover.execute("R")
        assertEquals(0, rover.x)
        assertEquals(1, rover.y)
        assertEquals(RoverState.E, rover.roverState)
        rover.execute("M")
        assertEquals(1, rover.x)
        assertEquals(1, rover.y)
        assertEquals(RoverState.E, rover.roverState)
    }
}
