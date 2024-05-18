package com.mfarhan08a.jsontokotlinpojo.core.models


data class ControlsModel(
    val sources: List<Source>,
    var selectedSource: Source? = null
)

sealed class Source(
    val languages: List<Language>,
    var selectedLanguage: Language? = null,
    val propertyName: String
) {
    class Json(
        languages: List<Language>,
        selected: Language? = null
    ) : Source(languages, selected, JSON)

    companion object {
        const val JSON = "Json"
    }
}

sealed class Language(
    val frameworks: List<Framework>,
    var selectedFramework: Framework? = null,
    val propertyName: String
) {
    class Kotlin(
        frameworks: List<Framework>,
        selected: Framework? = null
    ) : Language(frameworks, selected, KOTLIN)

    companion object {
        const val KOTLIN = "Kotlin"
    }
}

sealed class Framework(
    val propertyName: String,
    val properties: List<AdditionalProperties>
) {
    class KotlinSerialization(
        properties: List<AdditionalProperties> = emptyList()
    ) : Framework(KOTLIN_SERIALIZATION, properties)

    class Gson(
        properties: List<AdditionalProperties> = emptyList()
    ) : Framework(GSON, properties)

    class None(
        properties: List<AdditionalProperties> = emptyList()
    ) : Framework(NONE, properties)

    companion object {
        const val KOTLIN_SERIALIZATION = "Kotlin Serialization"
        const val GSON = "GSON"
        const val NONE = "None"
    }
}

sealed class AdditionalProperties(
    var selected: Boolean,
    val propertyName: String
) {
    class UseKotlinParcelable(
        selected: Boolean = false
    ) : AdditionalProperties(selected, KOTLIN_PARCELABLE)

    class UseKotlinSingleDataClass(
        selected: Boolean = false
    ) : AdditionalProperties(selected, KOTLIN_SINGLE_DATA_CLASS)

    class UseKotlinDataClasses(
        selected: Boolean = false
    ) : AdditionalProperties(selected, KOTLIN_DATA_CLASSES)

    class UseKotlinNullableFields(
        selected: Boolean = false
    ) : AdditionalProperties(selected, KOTLIN_NULLABLE_FIELDS)

    companion object {
        const val KOTLIN_PARCELABLE = "parcelable (Android)"
        const val KOTLIN_SINGLE_DATA_CLASS = "single file"
        const val KOTLIN_DATA_CLASSES = "use data classes"
        const val KOTLIN_NULLABLE_FIELDS = "nullable fields"
    }
}
