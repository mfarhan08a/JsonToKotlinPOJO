package com.mfarhan08a.jsontokotlinpojo.generator.properties.templates

internal sealed class ImportsTemplate(
    val imports: Array<String>
) {
    object Gson : ImportsTemplate(arrayOf(SERIALIZED_NAME))

    object LoganSquare : ImportsTemplate(arrayOf(JSON_OBJECT, JSON_FIELD))

    object Jackson : ImportsTemplate(arrayOf(JSON_PROPERTY))

    object FastJson : ImportsTemplate(arrayOf(FAST_JSON_PROPERTY))

    data class Moshi(
        val jsonClassAnnotation: String = "import com.squareup.moshi.JsonClass;"
    ) : ImportsTemplate(arrayOf(MOSHI_PROPERTY))

    object KotlinSerialization : ImportsTemplate(
        arrayOf(
            KOTLIN_SERIALIZATION_SERIALIZABLE,
            KOTLIN_SERIALIZATION_SERIALNAME
        )
    )

    companion object {
        const val LIST = "import java.util.List;"
        const val SERIALIZED_NAME = "import com.google.gson.annotations.SerializedName;"
    }
}

internal const val JSON_OBJECT = "import com.bluelinelabs.logansquare.annotation.JsonObject;"
internal const val JSON_FIELD = "import com.bluelinelabs.logansquare.annotation.JsonField;"
internal const val JSON_PROPERTY = "import com.fasterxml.jackson.annotation.JsonProperty;"
internal const val FAST_JSON_PROPERTY = "import com.alibaba.fastjson.annotation.JSONField;"
internal const val MOSHI_PROPERTY = "import com.squareup.moshi.Json;"

internal const val KOTLIN_SERIALIZATION_SERIALIZABLE = "import kotlinx.serialization.Serializable"
internal const val KOTLIN_SERIALIZATION_SERIALNAME = "import kotlinx.serialization.SerialName"

internal const val PARCELABLE_ANDROID = "import android.os.Parcelable"
internal const val PARCELIZE_KOTLINX = "import kotlinx.parcelize.Parcelize"