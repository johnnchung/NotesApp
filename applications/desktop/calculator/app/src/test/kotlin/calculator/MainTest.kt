package calculator

import kotlin.test.*
import calculator.Main.*

class MainTest {
    @Test
    fun addition() {
        assert(true == true)
        assert(Expr(0, Expr.OP.ADD, 0).calculate() == 0)
        assert(Expr(0, Expr.OP.ADD, 2_147_483_647).calculate() == 2_147_483_647)
        assert(Expr(0, Expr.OP.ADD, -2_147_483_647).calculate() == -2_147_483_647)
        assert(Expr(1, Expr.OP.ADD, -2_147_483_648).calculate() == -2_147_483_647)
    }

    @Test
    fun multiplication() {
        println("Multiplication tests")
        assert(Expr(0, Expr.OP.MUL, 0).calculate() == 0)
        assert(Expr(0, Expr.OP.MUL, 5).calculate() == 0)
        assert(Expr(5, Expr.OP.MUL, 0).calculate() == 0)
        assert(Expr(1, Expr.OP.MUL, 0).calculate() == 0)
        assert(Expr(1, Expr.OP.MUL, 5).calculate() == 5)
        assert(Expr(5, Expr.OP.MUL, 1).calculate() == 5)
        assert(Expr(1, Expr.OP.MUL, 2_147_483_647).calculate() == 2_147_483_647)
        assert(Expr(1, Expr.OP.MUL, -2_147_483_648).calculate() == -2_147_483_648)
    }
}