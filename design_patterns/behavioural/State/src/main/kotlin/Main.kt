/*
 * State Design Pattern
 * (c) 2022 Jeff Avery
 */

fun main() {
    val manager = GameManager()
    manager.state = State.RUNNING
    println("Game is ${if (manager.isRunning) "running" else "not running"}")

    manager.state = State.PAUSED
    println("Game is ${if (manager.isRunning) "running" else "not running"}")

    manager.state = State.STOPPED
    println("Game is ${if (manager.isRunning) "running" else "not running"}")
}

enum class State { STOPPED, PAUSED, RUNNING }

class GameManager {
    var state : State = State.STOPPED
        set(value) {
            println("$field -> $value")
            field = value
        }

    val isRunning: Boolean
        get() = when(state) {
            State.PAUSED, State.RUNNING -> true
            else -> false
        }
}