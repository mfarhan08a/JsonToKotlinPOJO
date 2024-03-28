package com.mfarhan08a.jsontokotlinpojo.generator.postprocessing

import com.mfarhan08a.jsontokotlinpojo.core.models.FrameworkVW.AutoValue
import com.mfarhan08a.jsontokotlinpojo.core.models.FrameworkVW.JavaRecords
import com.mfarhan08a.jsontokotlinpojo.core.models.GenerationModel
import com.mfarhan08a.jsontokotlinpojo.generator.postprocessing.common.AutoValueClassPostProcessor
import com.mfarhan08a.jsontokotlinpojo.generator.postprocessing.common.CommonJavaPostProcessor
import com.mfarhan08a.jsontokotlinpojo.generator.postprocessing.common.JavaRecordsPostProcessor
import com.mfarhan08a.jsontokotlinpojo.generator.postprocessing.common.KotlinDataClassPostProcessor

internal class PostProcessorFactory(
    private val kotlinDataClassPostProcessor: KotlinDataClassPostProcessor,
    private val autoValueClassPostProcessor: AutoValueClassPostProcessor,
    private val commonJavaPostProcessor: CommonJavaPostProcessor,
    private val javaRecordsPostProcessor: JavaRecordsPostProcessor
) {
    fun createPostProcessor(
        generationModel: GenerationModel
    ): BasePostProcessor = with(generationModel) {
        if (useKotlin) {
            kotlinDataClassPostProcessor
        } else if (annotationEnum is AutoValue) {
            autoValueClassPostProcessor
        } else if (annotationEnum is JavaRecords) {
            javaRecordsPostProcessor
        } else {
            commonJavaPostProcessor
        }
    }
}