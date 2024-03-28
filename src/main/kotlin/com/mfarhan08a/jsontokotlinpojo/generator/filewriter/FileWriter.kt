package com.mfarhan08a.jsontokotlinpojo.generator.filewriter

import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.fileTypes.UserBinaryFileType
import com.intellij.psi.PsiFileFactory
import com.mfarhan08a.jsontokotlinpojo.core.models.GenerationModel
import com.mfarhan08a.jsontokotlinpojo.core.models.ProjectModel

internal class FileWriter {

    fun writeToFile(
        classItemBody: String,
        fileName: String,
        projectModel: ProjectModel,
        generationModel: GenerationModel
    ) {
        val factory = PsiFileFactory.getInstance(projectModel.project)
        val fileNew = factory.createFileFromText(
            fileName,
            UserBinaryFileType.INSTANCE,
            classItemBody
        )
        WriteCommandAction.runWriteCommandAction(projectModel.project, Runnable {
            val existingFile = projectModel.directory.findFile(fileName)
            if (existingFile == null) {
                projectModel.directory.add(fileNew)
            } else if (generationModel.rewriteClasses) {
                existingFile.delete()
                projectModel.directory.add(fileNew)
            }
        })
    }
}
