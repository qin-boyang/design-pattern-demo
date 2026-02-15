package com.mycompany.myproject.demo.light

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CommandPatternTest {

    @Test
    fun `should support undoing a light operation`() {
        val light = Light()
        val remote = RemoteControl()

        val lightOn = LightOnCommand(light)

        // 1. Execute
        remote.executeCommand(lightOn)
        assertEquals("ON", light.status)

        // 2. Undo
        remote.undoLastCommand()
        assertEquals("OFF", light.status)
    }
}
