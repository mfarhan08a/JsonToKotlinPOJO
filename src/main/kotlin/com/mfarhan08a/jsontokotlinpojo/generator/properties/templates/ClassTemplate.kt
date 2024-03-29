package com.mfarhan08a.jsontokotlinpojo.generator.properties.templates

internal object ClassTemplate {
    const val KOTLIN_DATA_CLASS = "data"
    const val NEW_LINE = "\n"
    const val TAB = "\t"

    const val CLASS_BODY_KOTLIN_DTO = "$KOTLIN_DATA_CLASS class %1\$s" +
            "(" + NEW_LINE +
            "%2\$s" + NEW_LINE +
            ")"
    const val CLASS_BODY_KOTLIN_DTO_PARCELABLE = "@Parcelize\n$KOTLIN_DATA_CLASS class %1\$s" +
            "(" + NEW_LINE +
            "%2\$s" + NEW_LINE +
            ") : Parcelable"
    const val CLASS_BODY_ANNOTATED = "%1\$s" + NEW_LINE +
            "%2\$s"
    const val CLASS_ROOT_IMPORTS = (
            "package %1\$s;" + NEW_LINE + NEW_LINE +
                    "%2\$s" + NEW_LINE +
                    "%3\$s"
            )
    const val CLASS_ROOT_IMPORTS_WITHOUT_SEMICOLON = (
            "package %1\$s" + NEW_LINE + NEW_LINE +
                    "%2\$s" + NEW_LINE +
                    "%3\$s"
            )
    const val CLASS_ROOT = "package %1\$s;" + NEW_LINE + NEW_LINE +
            "%2\$s" + NEW_LINE
    const val CLASS_ROOT_WITHOUT_SEMICOLON = "package %1\$s" + NEW_LINE + NEW_LINE +
            "%2\$s" + NEW_LINE
    const val CLASS_ROOT_NO_PACKAGE = "%1\$s" + NEW_LINE
    const val FIELD_KOTLIN_DTO = TAB + "val %1\$s: %2\$s? = null" + "," + NEW_LINE
    const val FIELD_KOTLIN_DTO_NON_NULL = TAB + "val %1\$s: %2\$s" + "," + NEW_LINE
    const val FIELD_KOTLIN_DOT_DEFAULT = TAB + "val any: Any? = null"
    const val FIELD_ANNOTATED = "$NEW_LINE$TAB%1\$s$NEW_LINE%2\$s"
}