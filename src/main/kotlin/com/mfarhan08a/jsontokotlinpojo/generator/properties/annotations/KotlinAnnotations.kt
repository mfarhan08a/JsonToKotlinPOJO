package com.mfarhan08a.jsontokotlinpojo.generator.properties.annotations

internal sealed class KotlinAnnotations(
    val classAnnotation: String = EMPTY_ANNOTATION,
    val annotation: String
) {
    object Gson : KotlinAnnotations(
        annotation = "@field:SerializedName(\"%1\$s\")"
    )

    object LoganSquare : KotlinAnnotations(
        classAnnotation = "@JsonObject",
        annotation = "@field:JsonField(name = [\"%1\$s\"])"
    )

    object Jackson : KotlinAnnotations(
        annotation = "@field:JsonProperty(\"%1\$s\")"
    )

    object FastJson : KotlinAnnotations(
        annotation = "@JSONField(name=\"%1\$s\")"
    )

    data class Moshi(
        val adapterClassAnnotation: String = "@JsonClass(generateAdapter = true)"
    ) : KotlinAnnotations(
        annotation = "@Json(name=\"%1\$s\")"
    )

    object KotlinSerialization : KotlinAnnotations(
        classAnnotation = "@Serializable",
        annotation = "@SerialName(\"%1\$s\")"
    )
}

private const val EMPTY_ANNOTATION = ""