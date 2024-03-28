package com.mfarhan08a.jsontokotlinpojo.generator.properties.templates

internal sealed class ImportsTemplate(
    val imports: Array<String>
) {
    object GSON : ImportsTemplate(arrayOf(SERIALIZED_NAME))

    object LOGAN_SQUARE : ImportsTemplate(arrayOf(JSON_OBJECT, JSON_FIELD))

    object JACKSON : ImportsTemplate(arrayOf(JSON_PROPERTY))

    object AUTO_VALUE_GSON : ImportsTemplate(
        arrayOf(SERIALIZED_NAME, AUTO_VALUE, TYPED_ADAPTER, GSON_IMPORT)
    )

    object FAST_JSON : ImportsTemplate(arrayOf(FAST_JSON_PROPERTY))

    data class MOSHI(
        val jsonClassAnnotation: String = "import com.squareup.moshi.JsonClass;"
    ) : ImportsTemplate(arrayOf(MOSHI_PROPERTY))

    class Lombok(useValue: Boolean) : ImportsTemplate(
        imports = if (useValue) {
            arrayOf(LOMBOK_VALUE)
        } else {
            arrayOf(LOMBOK_DATA)
        }
    )

    object KOTLIN_SERIALIZATION : ImportsTemplate(
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
internal const val LOMBOK_DATA = "import lombok.Data;"
internal const val LOMBOK_VALUE = "import lombok.Value;"
internal const val AUTO_VALUE = "import com.google.auto.value.AutoValue;"
internal const val TYPED_ADAPTER = "import com.google.gson.TypeAdapter;"
internal const val GSON_IMPORT = "import com.google.gson.Gson;"

internal const val KOTLIN_SERIALIZATION_SERIALIZABLE = "import kotlinx.serialization.Serializable"
internal const val KOTLIN_SERIALIZATION_SERIALNAME = "import kotlinx.serialization.SerialName"


internal const val PARCELABLE_ANDROID = "import android.os.Parcelable"
internal const val PARCELIZE_KOTLINX = "import kotlinx.parcelize.Parcelize"