package parser

object ParserTestUtils {
    fun Node.nonGenericArrayIdentifier() =
        node(NonTerminal.E) {
            node(Terminal.IDENTIFIER)
            node(NonTerminal.E_) {
                node(Terminal.EMPTY)
            }
        }

    fun Node.nonGenericIdentifier() =
        node(NonTerminal.F) {
            nonGenericArrayIdentifier()
            node(NonTerminal.F_) {
                node(Terminal.EMPTY)
            }
        }
}