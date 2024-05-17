package com.mfarhan08a.jsontokotlinpojo.generator.properties.annotations

internal sealed class KotlinAnnotations(
    val classAnnotation: String = EMPTY_ANNOTATION,
    val annotation: String
) {
    object KotlinSerialization : KotlinAnnotations(
        classAnnotation = "@Serializable",
        annotation = "@SerialName(\"%1\$s\")"
    )

    object Gson : KotlinAnnotations(
        annotation = "@field:SerializedName(\"%1\$s\")"
    )
}

private const val EMPTY_ANNOTATION = ""