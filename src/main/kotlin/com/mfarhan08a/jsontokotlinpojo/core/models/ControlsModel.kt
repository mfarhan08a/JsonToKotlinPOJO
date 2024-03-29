package com.mfarhan08a.jsontokotlinpojo.core.models


data class ControlsModel(
    val sources: List<SourceVM>,
    var selectedSource: SourceVM? = null
)

sealed class SourceVM(
    val languages: List<LanguageVM>,
    var selectedLanguage: LanguageVM? = null,
    val propertyName: String
) {
    class Json(
        languages: List<LanguageVM>,
        selected: LanguageVM? = null
    ) : SourceVM(languages, selected, JSON)

    companion object {
        const val JSON = "Json"
    }
}

sealed class LanguageVM(
    val frameworks: List<FrameworkVW>,
    var selectedFramework: FrameworkVW? = null,
    val propertyName: String
) {
    class Kotlin(
        frameworks: List<FrameworkVW>,
        selected: FrameworkVW? = null
    ) : LanguageVM(frameworks, selected, KOTLIN)

    companion object {
        const val KOTLIN = "Kotlin"
    }
}

sealed class FrameworkVW(
    val propertyName: String,
    val properties: List<AdditionalPropertiesVM>
) {
    class None(
        properties: List<AdditionalPropertiesVM> = emptyList()
    ) : FrameworkVW(NONE, properties)

    class Gson(
        properties: List<AdditionalPropertiesVM> = emptyList()
    ) : FrameworkVW(GSON, properties)

    class Jackson(
        properties: List<AdditionalPropertiesVM> = emptyList()
    ) : FrameworkVW(JACKSON, properties)

    class LoganSquare(
        properties: List<AdditionalPropertiesVM> = emptyList()
    ) : FrameworkVW(LOGAN_SQUARE, properties)

    class Moshi(
        properties: List<AdditionalPropertiesVM> = emptyList()
    ) : FrameworkVW(MOSHI, properties)

    class FastJson(
        properties: List<AdditionalPropertiesVM> = emptyList()
    ) : FrameworkVW(FAST_JSON, properties)

    class KotlinSerialization(
        properties: List<AdditionalPropertiesVM> = emptyList()
    ) : FrameworkVW(KOTLIN_SERIALIZATION, properties)

    companion object {
        const val NONE = "None"
        const val GSON = "GSON"
        const val JACKSON = "Jackson"
        const val LOGAN_SQUARE = "Logan Square"
        const val MOSHI = "Moshi"
        const val FAST_JSON = "FastJson"
        const val KOTLIN_SERIALIZATION = "Kotlin Serialization"
    }
}

sealed class AdditionalPropertiesVM(
    var selected: Boolean,
    val propertyName: String
) {
    class UseKotlinParcelable(
        selected: Boolean = false
    ) : AdditionalPropertiesVM(selected, KOTLIN_PARCELABLE)

    class UseKotlinSingleDataClass(
        selected: Boolean = false
    ) : AdditionalPropertiesVM(selected, KOTLIN_SINGLE_DATA_CLASS)

    class UseMoshiAdapterAnnotation(
        selected: Boolean = false
    ) : AdditionalPropertiesVM(selected, MOSHI_ADAPTER)

    class UseKotlinDataClasses(
        selected: Boolean = false
    ) : AdditionalPropertiesVM(selected, KOTLIN_DATA_CLASSES)

    class UseKotlinNullableFields(
        selected: Boolean = false
    ) : AdditionalPropertiesVM(selected, KOTLIN_NULLABLE_FIELDS)

    companion object {
        const val KOTLIN_PARCELABLE = "parcelable (Android)"
        const val KOTLIN_SINGLE_DATA_CLASS = "single file"
        const val MOSHI_ADAPTER = "generate adapter"
        const val KOTLIN_DATA_CLASSES = "use data classes"
        const val KOTLIN_NULLABLE_FIELDS = "nullable fields"
    }
}
