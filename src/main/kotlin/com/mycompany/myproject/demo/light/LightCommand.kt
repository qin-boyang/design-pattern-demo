package com.mycompany.myproject.demo.light

class Light {
    var status: String = LightState.Off
}

data object LightState {
    val On = "ON"
    val Off = "OFF"
}

class RemoteControl {
    private val history = mutableListOf<Command>()

    fun executeCommand(command: Command) {
        command.execute()
        history.add(command)
    }

    fun undoLastCommand() {
        // Defensive check: don't crash if history is empty!
        if (history.isNotEmpty()) {
            val lastCommand = history.removeLast()
            lastCommand.undo()
        }
    }
}

interface Command {
    fun execute()
    fun undo()
}
class LightOnCommand(private var light: Light): Command {
    override fun execute() {
        light.status = LightState.On
    }

    override fun undo() {
        light.status = LightState.Off
    }
}