import assembler.convertText
import java.io.File
import kotlin.IllegalArgumentException
import kotlin.system.exitProcess


fun main(args: Array<String>) {
    try {
        App().convert(args.getOrNull(0))
        exitProcess(0)
    } catch (e: Throwable) {
        println(e.message)
        exitProcess(1)
    }
}

class App {
    fun convert(fileName: String?) {

        if (fileName == null) throw IllegalArgumentException("Please provide a filename")

        var file = File(fileName)

        println(("File selected is ${file.absolutePath}"))

        if (file.extension != "asm") throw IllegalArgumentException("File needs to be of type asm")
        if (!file.exists()) throw IllegalArgumentException("File does not exist")
        if (!file.canRead()) throw IllegalArgumentException("File is not readable")


        val input = file.readText()
        val converted = convertText(input)

        var output = File("${file.nameWithoutExtension}.hack")
        output.writeText(converted)

        println("Created ${output.absolutePath}")
    }

}
