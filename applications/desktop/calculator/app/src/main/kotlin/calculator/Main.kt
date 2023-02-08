package calculator

import javafx.application.Application
import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.TextField
import javafx.scene.input.KeyCode
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.GridPane
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.stage.Stage

// Simple Calculator
// (c) 2021-2022 Jeff Avery
// Originally based on this Java tutorial:
// https://www.youtube.com/watch?v=dfhmTyRTCSQ

class Main : Application() {
    class Expr(var num1: Int, var op: OP = OP.NONE, var num2: Int) {
        enum class OP { ADD, SUB, MUL, DIV, NONE }

        fun calculate(): Int {
            return when(op) {
                OP.ADD -> num1 + num2
                OP.SUB -> num1 - num2
                OP.MUL -> num1 * num2
                OP.DIV -> num1 / num2
                else -> num1
            }
        }
        fun clear() = run { num1 = 0 ; op = OP.NONE; num2 = 0 }
        fun set(operation: OP) = run { op = operation }
        fun set(n: Int) = if (op == OP.NONE) num1 = (num1 * 10) + n else num2 = (num2 * 10) + n
    }
    var expr = Expr(0, Expr.OP.NONE, 0)

    class CalcButton(caption: String, handler: () -> Unit ): Button() {
        init {
            text = caption
            setPrefSize(50.0, 50.0)
            font = Font("Helvetica", 16.0)
            background = Background(BackgroundFill(Color.LIGHTGRAY, null, null))
            isVisible = true
            isDisable = false
            isFocusTraversable = true

            onAction = EventHandler {
                handler()
            }

            onMousePressed = EventHandler {
                this.style = "-fx-background-color: #1BD8E3; "
            }

            onMouseReleased = EventHandler {
                this.style = "-fx-background-color: #d3d3d3; "
            }
        }
    }

    override fun start(stage: Stage?) {
        // text output field
        val output = TextField("")
        output.font = Font("Helvetica", 21.0)
        output.isVisible = true
        output.isDisable = true
        output.alignment = Pos.BASELINE_RIGHT

        // numbers
        val button0 = CalcButton("0", { expr.set(0); output.text = output.text.plus("0") } )
        val button1 = CalcButton("1", { expr.set(1); output.text = output.text.plus("1") } )
        val button2 = CalcButton("2", { expr.set(2); output.text = output.text.plus("2") } )
        val button3 = CalcButton("3", { expr.set(3); output.text = output.text.plus("3") } )
        val button4 = CalcButton("4", { expr.set(4); output.text = output.text.plus("4") } )
        val button5 = CalcButton("5", { expr.set(5); output.text = output.text.plus("5") } )
        val button6 = CalcButton("6", { expr.set(6); output.text = output.text.plus("6") } )
        val button7 = CalcButton("7", { expr.set(7); output.text = output.text.plus("7") } )
        val button8 = CalcButton("8", { expr.set(8); output.text = output.text.plus("8") } )
        val button9 = CalcButton("9", { expr.set(9); output.text = output.text.plus("9") } )

        // operations
        val addButton = CalcButton("+") { expr.set(Expr.OP.ADD); output.text = output.text.plus("+")  }
        val subButton = CalcButton("-") { expr.set(Expr.OP.SUB); output.text = output.text.plus("-")  }
        val mulButton = CalcButton("*") { expr.set(Expr.OP.MUL); output.text = output.text.plus("*")  }
        val divButton = CalcButton("/") { expr.set(Expr.OP.DIV); output.text = output.text.plus("/")  }
        val equButton = CalcButton("=") { expr.num1 = expr.calculate(); output.text = output.text.plus("=") + expr.num1; expr.num2 = 0; expr.op = Expr.OP.NONE  }

        // functions
        val aButton = CalcButton("A") { }
        val clrButton = CalcButton("C") { expr.clear(); output.text = "" }
        val delButton = CalcButton("Del") { expr.num1 = expr.num1/10 ; output.text = expr.num1.toString()}
        val decButton = CalcButton(".") { }
        val negButton = CalcButton("-/+") { }

        // setup grid and buttons
        val grid = GridPane()
        grid.hgap = 3.0
        grid.vgap = 3.0
        grid.padding = Insets(3.0)

        grid.addRow(0, aButton, clrButton, delButton, divButton)
        grid.addRow(1, button7, button8, button9, mulButton)
        grid.addRow(2, button4, button5, button6, subButton)
        grid.addRow(3, button1, button2, button3, addButton)
        grid.addRow(4, decButton, button0, negButton, equButton)

        // setup layout
        val root = VBox()
        output.translateX = 3.0
        output.translateY = 10.0
        grid.translateX = 0.0
        grid.translateY = 20.0
        grid.requestFocus()
        grid.setOnKeyTyped {
            when(it.character) {
                "1" -> button1.fire()
                "2" -> button2.fire()
                "3" -> button3.fire()
                "4" -> button4.fire()
                "5" -> button5.fire()
                "6" -> button6.fire()
                "7" -> button7.fire()
                "8" -> button8.fire()
                "9" -> button9.fire()
                "0" -> button0.fire()
                "C" -> clrButton.fire()
                "c" -> clrButton.fire()
                "+" -> addButton.fire()
                "-" -> subButton.fire()
                "*" -> mulButton.fire()
                "/" -> divButton.fire()
                "=" -> equButton.fire()
                "." -> decButton.fire()
            }
            grid.setOnKeyPressed {
                if (it.code == KeyCode.ENTER) equButton.fire()
            }
        }
        root.children.addAll(output, grid)

        // show the window
        val scene = Scene(root, 215.0, 330.0)
        stage?.scene = scene
        stage?.isResizable = false
        stage?.title = "Calc"
        stage?.show()
    }
}
