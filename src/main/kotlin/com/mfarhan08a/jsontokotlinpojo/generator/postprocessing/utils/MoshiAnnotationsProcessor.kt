package com.mfarhan08a.jsontokotlinpojo.generator.postprocessing.utils

import com.mfarhan08a.jsontokotlinpojo.core.models.GenerationModel
import com.mfarhan08a.jsontokotlinpojo.generator.properties.ClassItem
import com.mfarhan08a.jsontokotlinpojo.generator.properties.annotations.KotlinAnnotations
import com.mfarhan08a.jsontokotlinpojo.generator.properties.templates.ImportsTemplate
import com.mfarhan08a.jsontokotlinpojo.generator.utils.ClassGenerateHelper

internal class MoshiAnnotationsProcessor(
    private val generateHelper: ClassGenerateHelper
) {

    fun applyAnnotations(
        generationModel: GenerationModel,
        classItem: ClassItem
    ) = with(ImportsTemplate.Moshi()) {
        with(KotlinAnnotations.Moshi()) {
            generateHelper.setAnnotations(
                classItem,
                if (generationModel.useMoshiAdapter) {
                    adapterClassAnnotation
                } else {
                    classAnnotation
                },
                annotation,
                if (generationModel.useMoshiAdapter) {
                    imports + jsonClassAnnotation
                } else {
                    imports
                }
            )
        }
    }
}