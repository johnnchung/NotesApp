package calc

fun main(args: Array<String>) {
    try {
        println(Calc().process(args))
    } catch (e: Exception ) {
        print("usage: calc number [+|-|*|/] number")
    }
}

class Calc() {
    fun process(args:Array<String>): Int {
        if (args.size != 3) throw Exception("Invalid number of arguments")
        return calculate(
            args.get(0).toInt(),
            args.get(2).toInt(),
            args.get(1))
    }

    fun calculate(op1: Int, op2: Int, operator: String): Int =
        when(operator) {
            "+" -> op1 + op2
            "-" -> op1 - op2
            "*" -> op1 * op2
            "/" -> op1 / op2
            else -> throw Exception("Invalid operator")
        }
}