import parser.Parser
import java.io.File

fun main() {
    val res = Parser("var ar: Array<Int>").parse()

    println(res)
    File("./parseTree.dot").writeText(res.toString())
}