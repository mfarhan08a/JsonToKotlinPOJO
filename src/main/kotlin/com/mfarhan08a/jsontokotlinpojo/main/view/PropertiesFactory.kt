package com.mfarhan08a.jsontokotlinpojo.main.view

import com.mfarhan08a.jsontokotlinpojo.core.models.ControlsModel
import com.mfarhan08a.jsontokotlinpojo.core.models.AdditionalPropertiesVM.*
import com.mfarhan08a.jsontokotlinpojo.core.models.FrameworkVW.*
import com.mfarhan08a.jsontokotlinpojo.core.models.SourceVM.*
import com.mfarhan08a.jsontokotlinpojo.core.models.LanguageVM.*

internal class PropertiesFactory {

    fun createControls(): ControlsModel {
        val result = ControlsModel(
            sources = listOf(
                Json(languages = createJsonLanguages()),
                JsonSchema(languages = createJsonLanguages())
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
        Java(
            frameworks = listOf(
                None(
                    properties = listOf(
                        UseJavaPrimitives(selected = true),
                        UseSetters(),
                        UseGetters(selected = true),
                        UseToString()
                    )
                ),
                NoneJavaRecords(
                    properties = listOf(
                        UseJavaPrimitives(selected = true)
                    )
                ),
                NoneLombok(
                    properties = listOf(
                        UseJavaPrimitives(selected = true),
                        UseLombokValue()
                    )
                ),
                Gson(
                    properties = listOf(
                        UseJavaPrimitives(selected = true),
                        UseSetters(),
                        UseGetters(selected = true),
                        UseToString()
                    )
                ),
                GsonJavaRecords(
                    properties = listOf(
                        UseJavaPrimitives(selected = true)
                    )
                ),
                Jackson(
                    properties = listOf(
                        UseJavaPrimitives(selected = true),
                        UseSetters(),
                        UseGetters(selected = true),
                        UseToString()
                    )
                ),
                JacksonJavaRecords(
                    properties = listOf(
                        UseJavaPrimitives(selected = true)
                    )
                ),
                LoganSquare(
                    properties = listOf(
                        UseJavaPrimitives(selected = true),
                        UseSetters(),
                        UseGetters(selected = true),
                        UseToString()
                    )
                ),
                LoganSquareJavaRecords(
                    properties = listOf(
                        UseJavaPrimitives(selected = true)
                    )
                ),
                Moshi(
                    properties = listOf(
                        UseJavaPrimitives(selected = true),
                        UseSetters(),
                        UseGetters(selected = true),
                        UseToString()
                    )
                ),
                MoshiJavaRecords(
                    properties = listOf(
                        UseJavaPrimitives(selected = true)
                    )
                ),
                FastJson(
                    properties = listOf(
                        UseJavaPrimitives(selected = true),
                        UseSetters(),
                        UseGetters(selected = true),
                        UseToString()
                    )
                ),
                FastJsonJavaRecords(
                    properties = listOf(
                        UseJavaPrimitives(selected = true)
                    )
                ),
                AutoValue()
            )
        ),
        Kotlin(
            frameworks = listOf(
                None(
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
                Jackson(
                    properties = listOf(
                        UseKotlinDataClasses(selected = true),
                        UseKotlinSingleDataClass(selected = true),
                        UseKotlinNullableFields(selected = true),
                        UseKotlinParcelable()
                    )
                ),
                LoganSquare(
                    properties = listOf(
                        UseKotlinDataClasses(selected = true),
                        UseKotlinSingleDataClass(selected = true),
                        UseKotlinNullableFields(selected = true),
                        UseKotlinParcelable()
                    )
                ),
                Moshi(
                    properties = listOf(
                        UseKotlinDataClasses(selected = true),
                        UseKotlinSingleDataClass(selected = true),
                        UseKotlinNullableFields(selected = true),
                        UseMoshiAdapterAnnotation(),
                        UseKotlinParcelable()
                    )
                ),
                FastJson(
                    properties = listOf(
                        UseKotlinDataClasses(selected = true),
                        UseKotlinSingleDataClass(selected = true),
                        UseKotlinNullableFields(selected = true),
                        UseKotlinParcelable()
                    )
                ),
                KotlinSerialization(
                    properties = listOf(
                        UseKotlinDataClasses(selected = true),
                        UseKotlinSingleDataClass(selected = true),
                        UseKotlinNullableFields(selected = true),
                        UseKotlinParcelable()
                    )
                )
            )
        )
    )
}