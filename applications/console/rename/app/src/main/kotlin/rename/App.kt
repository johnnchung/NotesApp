package rename

import java.io.*

fun main(args: Array<String>) {
    // split up arguments
    val files = getFiles(args)
    val options = getOptions(args)

    // exit if insufficient arguments
    if (files.size == 0 || options.size == 0) {
        println("Usage: [options] [file]")
        println(" [file] denotes file to rename")
        println(" [options] denotes one or more of the following options:")
        println("   prefix:str1 to prepend `str1` to the filename")
        println("   suffix:str2 to append `str2` to the filename")
        println("   capitalize:true to change the case of the filename")
        // otherwise rename files
    } else {
        applyOptions(files, options)
    }
}

fun getFiles(args:Array<String>) : List<String> {
    var files:MutableList<String> = mutableListOf()
    for (arg in args) {
        if (!arg.contains(":")) files.add(arg)
    }
    return files
}

fun getOptions(args:Array<String>): HashMap<String, String> {
    var options = HashMap<String, String>()
    for (arg in args) {
        if (arg.contains(":")) {
            val (key, value) = arg.split(":")
            options.put(key, value)
        }
    }
    return options
}

fun applyOptions(files:List<String>, options:HashMap<String, String>) {
    for (filename in files) {
        var renamedFilename = filename
        // capitalize first, then add prefix/suffix
        if (options.contains("capitalize")) renamedFilename = renamedFilename.toUpperCase()
        if (options.contains("prefix")) renamedFilename = options.get("prefix") + renamedFilename
        if (options.contains("suffix")) renamedFilename = renamedFilename + options.get("suffix")

        val file = File(filename)
        file.renameTo(File(renamedFilename))
        println(filename + " renamed to " + renamedFilename)
    }
}