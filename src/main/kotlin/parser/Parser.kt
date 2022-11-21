package parser

import parser.NonTerminal.S
import parser.NonTerminal.T
import parser.Terminal.*
import java.io.InputStream

class Parser(private val lex: LexicalAnalyzer) {

    constructor(inputStream: InputStream) : this(LexicalAnalyzer(inputStream))

    constructor(string: String) : this(string.byteInputStream())

    init {
        lex.nextToken()
    }

    fun parse(): Node {
        return s()
    }

    private fun s(): Node {
        val res = Node(S)
        when (lex.curTerminal) {
            VAR -> {
                lex.nextToken()
                res.addChild(VAR)

                lex.expect(IDENTIFIER)
                lex.nextToken()
                res.addChild(IDENTIFIER)

                lex.expect(COLON)
                lex.nextToken()
                res.addChild(COLON)

                lex.expect(ARRAY_IDENTIFIER)
                lex.nextToken()
                res.addChild(ARRAY_IDENTIFIER)

                lex.expect(LANGLE)
                lex.nextToken()
                res.addChild(LANGLE)

                lex.expect(IDENTIFIER)
                lex.nextToken()
                res.addChild(IDENTIFIER)

                lex.expect(RANGLE)
                lex.nextToken()
                res.addChild(RANGLE)

                res.addChild(t())
            }
            else -> lex.error("Unexpected token ${lex.curTerminal}")
        }
        return res
    }

    private fun t(): Node {
        val res = Node(T)
        when (lex.curTerminal) {
            SEMICOLON -> {
                lex.nextToken()
                res.addChild(SEMICOLON)
            }
            END -> {
                res.addChild(EMPTY)
            }
            else -> lex.error("Unexpected token ${lex.curTerminal}")
        }
        return res
    }
}
