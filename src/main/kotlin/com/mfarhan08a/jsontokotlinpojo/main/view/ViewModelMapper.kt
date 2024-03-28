package com.mfarhan08a.jsontokotlinpojo.main.view

import com.mfarhan08a.jsontokotlinpojo.core.models.GenerationModel
import com.mfarhan08a.jsontokotlinpojo.core.models.LanguageVM
import com.mfarhan08a.jsontokotlinpojo.main.form.GeneratorView
import com.mfarhan08a.jsontokotlinpojo.core.models.AdditionalPropertiesVM.Companion.GETTERS
import com.mfarhan08a.jsontokotlinpojo.core.models.AdditionalPropertiesVM.Companion.JAVA_PRIMITIVE_TYPES
import com.mfarhan08a.jsontokotlinpojo.core.models.AdditionalPropertiesVM.Companion.KOTLIN_DATA_CLASSES
import com.mfarhan08a.jsontokotlinpojo.core.models.AdditionalPropertiesVM.Companion.KOTLIN_NULLABLE_FIELDS
import com.mfarhan08a.jsontokotlinpojo.core.models.AdditionalPropertiesVM.Companion.KOTLIN_PARCELABLE
import com.mfarhan08a.jsontokotlinpojo.core.models.AdditionalPropertiesVM.Companion.KOTLIN_SINGLE_DATA_CLASS
import com.mfarhan08a.jsontokotlinpojo.core.models.AdditionalPropertiesVM.Companion.LOMBOK_VALUE
import com.mfarhan08a.jsontokotlinpojo.core.models.AdditionalPropertiesVM.Companion.MOSHI_ADAPTER
import com.mfarhan08a.jsontokotlinpojo.core.models.AdditionalPropertiesVM.Companion.SETTERS
import com.mfarhan08a.jsontokotlinpojo.core.models.AdditionalPropertiesVM.Companion.TO_STRING

internal class ViewModelMapper(
    private val generatorViewBinder: GeneratorViewBinder
) {
    fun map(generatorVew: GeneratorView) = with(generatorVew) {
        GenerationModel(
            rewriteClasses = rewriteExistingClassesCheckBox.isSelected,
            annotationEnum = resolveFramework(),
            useKotlin = isKotlinSelected(),
            content = textArea.text,
            rootClassName = className.text,
            useSetters = resolveCheckBox(SETTERS),
            useGetters = resolveCheckBox(GETTERS),
            useStrings = resolveCheckBox(TO_STRING),
            useKotlinParcelable = resolveCheckBox(KOTLIN_PARCELABLE),
            useKotlinSingleDataClass = resolveCheckBox(KOTLIN_SINGLE_DATA_CLASS),
            kotlinNullableFields = resolveCheckBox(KOTLIN_NULLABLE_FIELDS),
            javaPrimitives = resolveCheckBox(JAVA_PRIMITIVE_TYPES),
            useLombokValue = resolveCheckBox(LOMBOK_VALUE),
            useTabsIndentation = useTabsIndentation.isSelected,
            useMoshiAdapter = resolveCheckBox(MOSHI_ADAPTER),
            useKotlinDataClass = resolveCheckBox(KOTLIN_DATA_CLASSES)
        )
    }

    private fun resolveCheckBox(key: String) = with(generatorViewBinder.properties) {
        with(this?.selectedSource?.selectedLanguage?.selectedFramework) {
            this?.properties?.firstOrNull { it.propertyName == key }?.selected
        }
    } ?: false

    private fun resolveFramework() = with(generatorViewBinder.properties) {
        this?.selectedSource?.selectedLanguage?.selectedFramework ?: throw IllegalStateException()
    }

    private fun isKotlinSelected() = with(generatorViewBinder.properties) {
        this?.selectedSource?.selectedLanguage is LanguageVM.Kotlin
    }
}