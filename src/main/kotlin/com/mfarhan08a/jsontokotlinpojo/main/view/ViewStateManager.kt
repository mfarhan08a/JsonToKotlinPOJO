package com.mfarhan08a.jsontokotlinpojo.main.view

import com.mfarhan08a.jsontokotlinpojo.core.models.*
import com.mfarhan08a.jsontokotlinpojo.main.form.GeneratorView
import com.mfarhan08a.jsontokotlinpojo.main.persistense.ViewStateService

internal class ViewStateManager(
    private val viewStateService: ViewStateService
) {

    fun restoreCommonProperties(generatorVew: GeneratorView) {
        viewStateService.state.model?.let { model ->
            with(generatorVew) {
                useTabsIndentation.isSelected = model.useTabsIndentation
                rewriteExistingClassesCheckBox.isSelected = model.rewriteClasses
                className.text = model.rootClassName
            }
        }
    }

    fun restoreState(properties: ControlsModel?) {
        viewStateService.state.model?.let { model ->
            properties?.apply {
                sources.filterIsInstance<Source.Json>().firstOrNull()?.apply {
                    applyLanguage(this, model)
                }
            }
        }
    }

    private fun applyLanguage(source: Source, model: GenerationModel) {
        source.selectedLanguage = source.languages.filterIsInstance<Language.Kotlin>().firstOrNull()
        source.selectedLanguage?.let {
            applyFrameworks(it, model)
        }
    }

    private fun applyFrameworks(language: Language, model: GenerationModel) {
        language.selectedFramework =
            language.frameworks.firstOrNull { it.propertyName == model.annotationEnum.propertyName }
        language.selectedFramework?.let {
            applyProperties(it, model)
        }
    }

    private fun applyProperties(framework: Framework, model: GenerationModel) {
        with(framework.properties) {
            filterIsInstance<AdditionalProperties.UseKotlinParcelable>().firstOrNull()?.selected =
                model.useKotlinParcelable
            filterIsInstance<AdditionalProperties.UseKotlinSingleDataClass>().firstOrNull()?.selected =
                model.useKotlinSingleDataClass
            filterIsInstance<AdditionalProperties.UseKotlinNullableFields>().firstOrNull()?.selected =
                model.kotlinNullableFields
            filterIsInstance<AdditionalProperties.UseKotlinDataClasses>().firstOrNull()?.selected =
                model.useKotlinDataClass
        }
    }
}