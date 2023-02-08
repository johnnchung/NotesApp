package todo.app

/*
 * TO-DO application
 * (c) 2022 Jeff Avery
 * Manage a to-do list from the command-line.
 */

fun main(args: Array<String>) {
    // data stored in a list internally
    // but saved in a file on exit
    val list = mutableListOf<Item>()
    val filename = "data.json"

    // load previous to-do list
    list.restore(filename)

    // process commands
    val command = CommandFactory.createFromArgs(args)
    command.execute(list)

    // save to-do list (json)
    list.save(filename)
}