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
    class KotlinSerialization(
        properties: List<AdditionalPropertiesVM> = emptyList()
    ) : FrameworkVW(KOTLIN_SERIALIZATION, properties)

    class Gson(
        properties: List<AdditionalPropertiesVM> = emptyList()
    ) : FrameworkVW(GSON, properties)

    class None(
        properties: List<AdditionalPropertiesVM> = emptyList()
    ) : FrameworkVW(NONE, properties)

    companion object {
        const val KOTLIN_SERIALIZATION = "Kotlin Serialization"
        const val GSON = "GSON"
        const val NONE = "None"
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

    class UseKotlinDataClasses(
        selected: Boolean = false
    ) : AdditionalPropertiesVM(selected, KOTLIN_DATA_CLASSES)

    class UseKotlinNullableFields(
        selected: Boolean = false
    ) : AdditionalPropertiesVM(selected, KOTLIN_NULLABLE_FIELDS)

    companion object {
        const val KOTLIN_PARCELABLE = "parcelable (Android)"
        const val KOTLIN_SINGLE_DATA_CLASS = "single file"
        const val KOTLIN_DATA_CLASSES = "use data classes"
        const val KOTLIN_NULLABLE_FIELDS = "nullable fields"
    }
}
