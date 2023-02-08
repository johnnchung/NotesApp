/*
 * Command Pattern
 * (c) 2022 Jeff Avery
 */

// Factory Method
// pass it a list of arguments and it returns a valid command
// assumes a single command with arguments is being passed in
// unknown commands display a help banner
object CommandFactory {
    fun createFromArgs(args: Array<String>): Command =
        if (args.isEmpty()) {
            HelpCommand(args)
        } else {
            when (args[0]) {
                "add" -> AddCommand(args)
                "del" -> DelCommand(args)
                "show" -> ShowCommand(args)
                else -> HelpCommand(args)
            }
        }
}

// Command pattern
// represents all valid commands that can be issued by the user
// any functionality for a given command should be contained in that class
interface Command {
    fun execute()
}

class AddCommand(val args: Array<String>) : Command {
    override fun execute() {
        assert(args.size == 2)
        println("Add: ${args[1]}")
    }
}

class DelCommand(val args: Array<String>) : Command {
    override fun execute() {
        assert(args.size == 2)
        println("Delete: ${args[1]}")
    }
}

class ShowCommand(val args: Array<String>) : Command {
    override fun execute() {
        assert(args.size == 1)
        println("Show: all")
    }
}

class HelpCommand(val args: Array<String>) : Command {
    override fun execute() {
        println("Usage: todo [add|del|show]")
    }
}

