fun main(args: Array<String>) {
    val command = CommandFactory.createFromArgs(args)
    command.execute()
}