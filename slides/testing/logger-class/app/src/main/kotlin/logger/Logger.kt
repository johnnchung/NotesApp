import java.util.logging.*

object LoggerExample {
    private val LOGGER = Logger.getLogger(LoggerExample::class.java.getName())

    @JvmStatic
    fun main(args: Array<String>) {
        // Set handlers for both console and log file
        val consoleHandler: Handler? = ConsoleHandler()
        val fileHandler: Handler? = FileHandler("./example.log")
        LOGGER.addHandler(consoleHandler)
        LOGGER.addHandler(fileHandler)

        // Set to filter what gets logged at each destination
        consoleHandler?.setLevel(Level.ALL)
        fileHandler?.setLevel(Level.ALL)
        LOGGER.level = Level.ALL

        // Log some data
        LOGGER.info("Information")
        LOGGER.config("Configuration")
        LOGGER.warning("Warning")

        // Console handler removed
        LOGGER.removeHandler(consoleHandler)

        // This should still go to the log file
        LOGGER.log(Level.FINE, "Finer logged without console")
    }
}