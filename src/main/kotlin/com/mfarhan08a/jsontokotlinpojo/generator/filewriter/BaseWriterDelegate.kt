package com.mfarhan08a.jsontokotlinpojo.generator.filewriter

import com.mfarhan08a.jsontokotlinpojo.core.delegates.MessageDelegate
import com.mfarhan08a.jsontokotlinpojo.core.delegates.PreWriterDelegate
import com.mfarhan08a.jsontokotlinpojo.core.errors.FileWriteException
import com.mfarhan08a.jsontokotlinpojo.core.models.GenerationModel
import com.mfarhan08a.jsontokotlinpojo.core.models.ProjectModel
import com.mfarhan08a.jsontokotlinpojo.generator.postprocessing.common.KotlinDataClassPostProcessor
import com.mfarhan08a.jsontokotlinpojo.generator.properties.ClassItem
import java.io.IOException

internal abstract class BaseWriterDelegate(
    private val messageDelegate: MessageDelegate,
    private val postProcessor: KotlinDataClassPostProcessor,
    private val fileWriterDelegate: FileWriter,
    private val preWriterDelegate: PreWriterDelegate
) {

    abstract fun writeFiles(
        set: Set<ClassItem>,
        generationModel: GenerationModel,
        projectModel: ProjectModel
    )

    protected fun prepareClass(
        classItem: ClassItem,
        generationModel: GenerationModel
    ) = postProcessor.proceed(classItem, generationModel)

    protected fun writeFile(
        classItemBody: String,
        className: String,
        generationModel: GenerationModel,
        projectModel: ProjectModel
    ) {
        val fileName = "$className$FILE_KOTLIN"
        try {
            if (projectModel.directory.findFile(fileName) != null) {
                if (generationModel.rewriteClasses) {
                    messageDelegate.logEventMessage("updated $fileName")
                } else {
                    messageDelegate.logEventMessage("skipped $fileName")
                }
            } else {
                messageDelegate.logEventMessage("created $fileName")
            }
            fileWriterDelegate.writeToFile(
                preWriterDelegate.updateFileBody(generationModel, classItemBody), fileName,
                projectModel,
                generationModel
            )
        } catch (e: IOException) {
            throw FileWriteException(e.message)
        }
    }
}

const val FILE_KOTLIN = ".kt"