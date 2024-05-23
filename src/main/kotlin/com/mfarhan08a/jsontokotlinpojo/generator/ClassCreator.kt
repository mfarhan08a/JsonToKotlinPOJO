package com.mfarhan08a.jsontokotlinpojo.generator

import com.mfarhan08a.jsontokotlinpojo.core.models.GenerationModel
import com.mfarhan08a.jsontokotlinpojo.core.models.ProjectModel
import com.mfarhan08a.jsontokotlinpojo.generator.filewriter.FileDelegateFactory

internal class ClassCreator(
    private val jsonToKotlinClassGenerator: JsonToKotlinClassGenerator,
    private val fileWriteFactory: FileDelegateFactory
) {

    fun generateFiles(
        generationModel: GenerationModel,
        projectModel: ProjectModel
    ) = fileWriteFactory.createFileWriter(
        generationModel
    ).writeFiles(
        jsonToKotlinClassGenerator.generate(generationModel),
        generationModel,
        projectModel
    )
}