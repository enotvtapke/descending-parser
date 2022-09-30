import parser.Parser

fun main() {
    val res = Parser("var ar: Array<Int>").parse()

    println(res)
}