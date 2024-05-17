package com.mfarhan08a.jsontokotlinpojo.generator.properties.templates

internal sealed class ImportsTemplate(
    val imports: Array<String>
) {
    object KotlinSerialization : ImportsTemplate(
        arrayOf(
            KOTLIN_SERIALIZATION_SERIALIZABLE,
            KOTLIN_SERIALIZATION_SERIALNAME
        )
    )

    object Gson : ImportsTemplate(arrayOf(SERIALIZED_NAME))

    companion object {
        const val LIST = "import java.util.List;"
        const val SERIALIZED_NAME = "import com.google.gson.annotations.SerializedName;"
    }
}

internal const val KOTLIN_SERIALIZATION_SERIALIZABLE = "import kotlinx.serialization.Serializable"
internal const val KOTLIN_SERIALIZATION_SERIALNAME = "import kotlinx.serialization.SerialName"

internal const val PARCELABLE_ANDROID = "import android.os.Parcelable"
internal const val PARCELIZE_KOTLINX = "import kotlinx.parcelize.Parcelize"