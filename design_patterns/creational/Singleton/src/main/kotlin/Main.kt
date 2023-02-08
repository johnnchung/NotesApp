/*
 * Singleton Design Pattern
 * (c) 2022 Jeff Avery
 */

// object that can only be instantiated once
// thread-safe, no resources leaked

object SystemConfig {
    val osName: String = System.getProperties().getProperty("os.name")
    val osVersion: String = System.getProperties().getProperty("os.version")
    val osArch: String = System.getProperties().getProperty("os.arch")

    fun display() {
        println("Using $this: ${osName} ${osVersion} (${osArch})")
    }
}

fun main() {
    // same instance each time we reference it
    SystemConfig.display()
    SystemConfig.display()
    SystemConfig.display()
}