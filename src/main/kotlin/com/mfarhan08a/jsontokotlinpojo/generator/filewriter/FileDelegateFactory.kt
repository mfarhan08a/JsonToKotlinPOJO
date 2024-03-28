package com.mfarhan08a.jsontokotlinpojo.generator.filewriter

import com.mfarhan08a.jsontokotlinpojo.core.models.GenerationModel
import com.mfarhan08a.jsontokotlinpojo.generator.filewriter.common.CommonFileWriterDelegate
import com.mfarhan08a.jsontokotlinpojo.generator.filewriter.common.KotlinSingleFileWriterDelegate

internal class FileDelegateFactory(
    private val commonFileWriterDelegate: CommonFileWriterDelegate,
    private val kotlinSingleFileWriterDelegate: KotlinSingleFileWriterDelegate
) {

    fun createFileWriter(generationModel: GenerationModel): BaseWriterDelegate =
        if (generationModel.useKotlin && generationModel.useKotlinSingleDataClass) {
            kotlinSingleFileWriterDelegate
        } else {
            commonFileWriterDelegate
        }
}