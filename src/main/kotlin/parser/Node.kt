package parser

data class Node(val node: Token, val children: MutableList<Node>) {
    constructor(node: Token, vararg children: Node) : this(node, children.toMutableList())
    constructor(node: Token, block: Node.() -> Unit) : this(node, mutableListOf()) {
        this.block()
    }

    fun node(nodeLabel: Token, block: Node.() -> Unit) {
        Node(nodeLabel).also {
            addChild(it)
            it.block()
        }
    }

    fun node(nodeLabel: Token) {
        node(nodeLabel) {}
    }

    fun nodes(vararg nodeLabels: Token) {
        nodeLabels.forEach {
            node(it)
        }
    }

    fun addChild(child: Node) {
        children.add(child)
    }

    fun addChild(child: Token) {
        children.add(Node(child))
    }

    private fun toString(nodeId: Long): String =
        buildString {
            append("$nodeId [label=\"$node\"]")
            appendLine()
            val childrenIds = (nodeId + 1..nodeId + children.size).toList()
            append("$nodeId -- {${childrenIds.joinToString(separator = " ")}}")
            appendLine()
            children.forEachIndexed { index, child ->
                append(child.toString(childrenIds[index]))
            }
        }

    override fun toString(): String = buildString {
        append("graph ParseTree {")
        appendLine()
        append(toString(0).trimEnd().prependIndent("  "))
        appendLine()
        append("}")
        appendLine()
    }
}