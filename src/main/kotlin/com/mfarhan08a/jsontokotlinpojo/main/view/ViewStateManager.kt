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
                sources.filterIsInstance<SourceVM.Json>().firstOrNull()?.apply {
                    applyLanguage(this, model)
                }
            }
        }
    }

    private fun applyLanguage(source: SourceVM, model: GenerationModel) {
        source.selectedLanguage = source.languages.filterIsInstance<LanguageVM.Kotlin>().firstOrNull()
        source.selectedLanguage?.let {
            applyFrameworks(it, model)
        }
    }

    private fun applyFrameworks(language: LanguageVM, model: GenerationModel) {
        language.selectedFramework =
            language.frameworks.firstOrNull { it.propertyName == model.annotationEnum.propertyName }
        language.selectedFramework?.let {
            applyProperties(it, model)
        }
    }

    private fun applyProperties(framework: FrameworkVW, model: GenerationModel) {
        with(framework.properties) {
            filterIsInstance<AdditionalPropertiesVM.UseKotlinParcelable>().firstOrNull()?.selected =
                model.useKotlinParcelable
            filterIsInstance<AdditionalPropertiesVM.UseKotlinSingleDataClass>().firstOrNull()?.selected =
                model.useKotlinSingleDataClass
            filterIsInstance<AdditionalPropertiesVM.UseKotlinNullableFields>().firstOrNull()?.selected =
                model.kotlinNullableFields
            filterIsInstance<AdditionalPropertiesVM.UseMoshiAdapterAnnotation>().firstOrNull()?.selected =
                model.useMoshiAdapter
            filterIsInstance<AdditionalPropertiesVM.UseKotlinDataClasses>().firstOrNull()?.selected =
                model.useKotlinDataClass
        }
    }
}