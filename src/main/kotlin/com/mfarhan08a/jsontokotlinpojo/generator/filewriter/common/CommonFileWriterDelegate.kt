package com.mfarhan08a.jsontokotlinpojo.generator.filewriter.common

import com.mfarhan08a.jsontokotlinpojo.core.delegates.MessageDelegate
import com.mfarhan08a.jsontokotlinpojo.core.delegates.PreWriterDelegate
import com.mfarhan08a.jsontokotlinpojo.core.models.GenerationModel
import com.mfarhan08a.jsontokotlinpojo.core.models.ProjectModel
import com.mfarhan08a.jsontokotlinpojo.generator.filewriter.BaseWriterDelegate
import com.mfarhan08a.jsontokotlinpojo.generator.filewriter.FileWriter
import com.mfarhan08a.jsontokotlinpojo.generator.postprocessing.PostProcessorFactory
import com.mfarhan08a.jsontokotlinpojo.generator.properties.ClassItem

internal class CommonFileWriterDelegate(
    messageDelegate: MessageDelegate,
    factory: PostProcessorFactory,
    fileWriterDelegate: FileWriter,
    preWriterDelegate: PreWriterDelegate
) : BaseWriterDelegate(
    messageDelegate,
    factory,
    fileWriterDelegate,
    preWriterDelegate
) {

    override fun writeFiles(
        set: Set<ClassItem>,
        generationModel: GenerationModel,
        projectModel: ProjectModel
    ) = set.forEach { classItem ->
        classItem.className?.let { className ->
            writeFile(
                classItemBody = prepareClass(
                    classItem.apply {
                        packagePath = projectModel.packageName
                    },
                    generationModel
                ),
                className = className,
                generationModel = generationModel,
                projectModel = projectModel
            )
        }
    }
}
