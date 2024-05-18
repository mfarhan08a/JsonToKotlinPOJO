package com.mfarhan08a.jsontokotlinpojo.main.view

import com.mfarhan08a.jsontokotlinpojo.core.models.ControlsModel
import com.mfarhan08a.jsontokotlinpojo.core.models.AdditionalProperties.*
import com.mfarhan08a.jsontokotlinpojo.core.models.Framework.*
import com.mfarhan08a.jsontokotlinpojo.core.models.Source.*
import com.mfarhan08a.jsontokotlinpojo.core.models.Language.*

internal class PropertiesFactory {

    fun createControls(): ControlsModel {
        val result = ControlsModel(
            sources = listOf(
                Json(languages = createJsonLanguages())
            )
        )
        result.selectedSource = result.sources.first()
        result.selectedSource?.let { source ->
            source.selectedLanguage = source.languages.first()
            source.selectedLanguage?.let { language ->
                language.selectedFramework = language.frameworks.first()
            }
        }
        return result
    }

    private fun createJsonLanguages() = listOf(
        Kotlin(
            frameworks = listOf(
                KotlinSerialization(
                    properties = listOf(
                        UseKotlinDataClasses(selected = true),
                        UseKotlinSingleDataClass(selected = true),
                        UseKotlinNullableFields(selected = true),
                        UseKotlinParcelable()
                    )
                ),
                Gson(
                    properties = listOf(
                        UseKotlinDataClasses(selected = true),
                        UseKotlinSingleDataClass(selected = true),
                        UseKotlinNullableFields(selected = true),
                        UseKotlinParcelable()
                    )
                ),
                None(
                    properties = listOf(
                        UseKotlinDataClasses(selected = true),
                        UseKotlinSingleDataClass(selected = true),
                        UseKotlinNullableFields(selected = true),
                        UseKotlinParcelable()
                    )
                ),
            )
        )
    )
}