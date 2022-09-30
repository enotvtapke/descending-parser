package parser

data class Tree(val node: Token, val children: MutableList<Tree>) {
    constructor(node: Token, vararg children: Tree) : this(node, children.toMutableList())

    fun node(nodeLabel: Token, block: Tree.() -> Unit) {
        Tree(nodeLabel).also {
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

    fun addChild(child: Tree) {
        children.add(child)
    }

    fun addChild(child: Token) {
        children.add(Tree(child))
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