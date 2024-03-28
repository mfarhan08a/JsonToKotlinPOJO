package com.mfarhan08a.jsontokotlinpojo.generator.postprocessing.common

import com.mfarhan08a.jsontokotlinpojo.core.models.FrameworkVW.*
import com.mfarhan08a.jsontokotlinpojo.core.models.GenerationModel
import com.mfarhan08a.jsontokotlinpojo.generator.postprocessing.BasePostProcessor
import com.mfarhan08a.jsontokotlinpojo.generator.properties.ClassItem
import com.mfarhan08a.jsontokotlinpojo.generator.properties.annotations.PojoAnnotations
import com.mfarhan08a.jsontokotlinpojo.generator.properties.templates.ImportsTemplate
import com.mfarhan08a.jsontokotlinpojo.generator.utils.ClassGenerateHelper
import com.mfarhan08a.jsontokotlinpojo.generator.utils.ClassTemplateHelper

internal abstract class JavaPostProcessor(
    generateHelper: ClassGenerateHelper,
    classTemplateHelper: ClassTemplateHelper
) : BasePostProcessor(generateHelper, classTemplateHelper) {

    override fun applyAnnotations(
        generationModel: GenerationModel,
        classItem: ClassItem
    ) = when (generationModel.annotationEnum) {
        is GsonJavaRecords,
        is Gson -> {
            generateHelper.setAnnotations(
                classItem,
                PojoAnnotations.GSON.classAnnotation,
                PojoAnnotations.GSON.annotation,
                ImportsTemplate.GSON.imports
            )
        }

        is LoganSquareJavaRecords,
        is LoganSquare -> {
            generateHelper.setAnnotations(
                classItem,
                PojoAnnotations.LOGAN_SQUARE.classAnnotation,
                PojoAnnotations.LOGAN_SQUARE.annotation,
                ImportsTemplate.LOGAN_SQUARE.imports
            )
        }

        is JacksonJavaRecords,
        is Jackson -> {
            generateHelper.setAnnotations(
                classItem,
                PojoAnnotations.JACKSON.classAnnotation,
                PojoAnnotations.JACKSON.annotation,
                ImportsTemplate.JACKSON.imports
            )
        }

        is FastJsonJavaRecords,
        is FastJson -> {
            generateHelper.setAnnotations(
                classItem,
                PojoAnnotations.FAST_JSON.classAnnotation,
                PojoAnnotations.FAST_JSON.annotation,
                ImportsTemplate.FAST_JSON.imports
            )
        }

        is AutoValue -> {
            generateHelper.setAnnotations(
                classItem,
                PojoAnnotations.AUTO_VALUE_GSON.classAnnotation,
                PojoAnnotations.AUTO_VALUE_GSON.annotation,
                ImportsTemplate.AUTO_VALUE_GSON.imports
            )
        }

        is MoshiJavaRecords,
        is Moshi -> {
            generateHelper.setAnnotations(
                classItem,
                PojoAnnotations.MOSHI.classAnnotation,
                PojoAnnotations.MOSHI.annotation,
                ImportsTemplate.MOSHI().imports
            )
        }

        is NoneLombok -> {
            val annotations = PojoAnnotations.Lombok(generationModel.useLombokValue)
            val importsTemplate = ImportsTemplate.Lombok(generationModel.useLombokValue)
            generateHelper.setAnnotations(
                classItem,
                annotations.classAnnotation,
                annotations.annotation,
                importsTemplate.imports
            )
        }

        is KotlinSerialization,
        is None,
        is NoneJavaRecords -> { // NO OP
        }
    }
}
