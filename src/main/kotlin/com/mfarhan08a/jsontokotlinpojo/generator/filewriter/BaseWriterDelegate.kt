package com.mfarhan08a.jsontokotlinpojo.generator.filewriter

import com.mfarhan08a.jsontokotlinpojo.core.delegates.MessageDelegate
import com.mfarhan08a.jsontokotlinpojo.core.delegates.PreWriterDelegate
import com.mfarhan08a.jsontokotlinpojo.core.errors.FileWriteException
import com.mfarhan08a.jsontokotlinpojo.core.models.GenerationModel
import com.mfarhan08a.jsontokotlinpojo.core.models.ProjectModel
import com.mfarhan08a.jsontokotlinpojo.generator.postprocessing.PostProcessorFactory
import com.mfarhan08a.jsontokotlinpojo.generator.properties.ClassItem
import java.io.IOException

internal abstract class BaseWriterDelegate(
    private val messageDelegate: MessageDelegate,
    private val factory: PostProcessorFactory,
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
    ) = factory.createPostProcessor(generationModel)
        .proceed(classItem, generationModel)

    protected fun writeFile(
        classItemBody: String,
        className: String,
        generationModel: GenerationModel,
        projectModel: ProjectModel
    ) {
        val fileName = "$className${if (generationModel.useKotlin) FILE_KOTLIN else FILE_JAVA}"
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
const val FILE_JAVA = ".java"