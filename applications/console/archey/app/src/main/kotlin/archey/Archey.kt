package archey

import java.io.File
import java.text.DecimalFormat

fun main() {
    Archey().print()
}

class Archey {
    val diskPartition = File("/")
    val userName = System.getProperty("user.name")
    val userHome = System.getProperty("user.home")
    val hostname = java.net.InetAddress.getLocalHost().hostName
    val hostAddress = java.net.InetAddress.getLocalHost().hostAddress
    val osName = System.getProperty("os.name")
    val osVersion = System.getProperty("os.version")
    val osArch = System.getProperty("os.arch")
    val processors = Runtime.getRuntime().availableProcessors()
    val freeMemory =  Runtime.getRuntime().freeMemory()
    val totalMemory = Runtime.getRuntime().totalMemory()
    val freeSpace = diskPartition.freeSpace
    val usableSpace = diskPartition.usableSpace

    fun print() {
        val formatter = DecimalFormat("###,###,###,###")
        val ansiRed = "\u001B[31m"
        val ansiGreen = "\u001B[32m"
        val ansiYellow = "\u001B[33m"
        val ansiBlue = "\u001B[34m"
        val ansiPurple = "\u001B[35m"
        val ansiCyan = "\u001B[36m"

        println("")
        println("$ansiRed               ####                   User: $userName")
        println("               ###                    Home: $userHome")
        println("       #######    #######             Name: $hostname")
        println("$ansiYellow     ######################           OS: $osName")
        println("    #####################             Version: $osVersion")
        println("$ansiGreen    ####################              CPU: $osArch")
        println("    ####################              Cores: $processors")
        println("$ansiBlue    #####################             Free Memory: ${formatter.format(freeMemory / (1024 * 1024))} MB")
        println("     ######################           Total Memory: ${formatter.format(totalMemory / (1024 * 1024))} MB")
        println(ansiCyan + "      ####################            Disk Size: ${formatter.format( freeSpace/ (1024 * 1024 * 1024))} GB")
        println("        ################              Free Space: ${formatter.format(usableSpace / (1024 * 1024 * 1024))} GB")
        println("$ansiPurple         ####     #####               IP Address: $hostAddress")
        println("")
    }
}
