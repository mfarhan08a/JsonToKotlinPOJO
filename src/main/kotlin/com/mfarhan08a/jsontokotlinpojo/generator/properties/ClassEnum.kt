package com.mfarhan08a.jsontokotlinpojo.generator.properties

internal enum class ClassEnum(
    val kotlin: String
) {
    STRING("String"),
    INTEGER("Int"),
    BOOLEAN("Boolean"),
    LONG("Long"),
    FLOAT("Float"),
    OBJECT("Any"),
    DOUBLE("Double");
}