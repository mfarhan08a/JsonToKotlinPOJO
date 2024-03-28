package com.mfarhan08a.jsontokotlinpojo.core.delegates

internal class IndentationDelegate {
    fun updateFileBody(body: String) =
        body.replace(TAB, "$SPACE$SPACE$SPACE$SPACE")
}

private const val SPACE = " "
private const val TAB = "\t"