package com.mycompany.myproject.demo.dsl

class Button (val label: String, val color: String, val size: Int, val onclick: () -> Unit) {
}

class ButtonBlue (val label: String, val color: String = "Blue", val size: Int, val onClick: () -> Unit, val onDoubleClick: () -> Unit) {
}

fun main() {
    val button = Button("OK", "blue", 10) {
        println("Button clicked")
    }
    button.onclick()
    button.onclick()
    button.onclick()
    button.onclick

    val buttonBlue = ButtonBlue(
        label = "OK",
        size = 10,
        onClick = {println("Blue Button clicked")},
        onDoubleClick = {println("Blue Button double clicked")}
    )

    buttonBlue.onDoubleClick()
    buttonBlue.onDoubleClick()
    buttonBlue.onDoubleClick()
    buttonBlue.onDoubleClick()
    buttonBlue.onClick()
}