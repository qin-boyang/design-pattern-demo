package com.mycompany.myproject.demo.marsrover

data class Movement(val roverState: RoverState, val x: Int, val y: Int)
// 1. 定義方向接口 (狀態)
sealed class RoverState {
    abstract fun rotateLeft(): Movement
    abstract fun rotateRight(): Movement
    abstract fun move(): Movement

    // 這裡定義具體的方向狀態
    object N : RoverState() {
        override fun rotateLeft() = Movement(W, 0, 0)
        override fun rotateRight() = Movement(E, 0, 0)
        override fun move() = Movement(N, 0, 1)
    }
    object E : RoverState() {
        /* 請實作 */
        override fun rotateLeft() = Movement(N, 0, 0)
        override fun rotateRight() = Movement(S, 0, 0)
        override fun move() = Movement(E, 1, 0)
    }
    object S : RoverState() {
        override fun rotateLeft() = Movement(E, 0, 0)
        override fun rotateRight() = Movement(W, 0, 0)
        override fun move() = Movement(S, 0, -1)
    }
    object W : RoverState() {
        override fun rotateLeft() = Movement(S, 0, 0)
        override fun rotateRight() = Movement(N, 0, 0)
        override fun move() = Movement(W, -1, 0)
    }
}


// 2. 定義火星車 (Context)
class Rover(var x: Int = 0, var y: Int = 0, var roverState: RoverState) {
    fun execute(command: String) {
         when (command) {
            "L" -> {
                afterMovement(roverState.rotateLeft())
            }
            "R" -> {
                afterMovement(roverState.rotateRight())
            }
            "M" -> {
                afterMovement(roverState.move())
            }
            else -> throw IllegalArgumentException("Invalid command")
        }
    }

    private fun afterMovement(movement: Movement) {
        roverState = movement.roverState
        x += movement.x
        y += movement.y
    }
}