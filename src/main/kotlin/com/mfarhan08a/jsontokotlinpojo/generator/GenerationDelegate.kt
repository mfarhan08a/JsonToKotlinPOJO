package com.mfarhan08a.jsontokotlinpojo.generator

import com.mfarhan08a.jsontokotlinpojo.core.delegates.EnvironmentDelegate
import com.mfarhan08a.jsontokotlinpojo.core.delegates.MessageDelegate
import com.mfarhan08a.jsontokotlinpojo.core.errors.CustomPluginException
import com.mfarhan08a.jsontokotlinpojo.core.models.GenerationModel
import com.mfarhan08a.jsontokotlinpojo.core.models.ProjectModel

interface GenerationDelegate {
    fun runGenerationTask(
        generationModel: GenerationModel,
        projectModel: ProjectModel
    )
}

internal class GenerationDelegateImpl(
    private val classCreator: ClassCreator,
    private val environmentDelegate: EnvironmentDelegate,
    private val messageDelegate: MessageDelegate
) : GenerationDelegate {

    override fun runGenerationTask(
        generationModel: GenerationModel,
        projectModel: ProjectModel
    ) {
        try {
            classCreator.generateFiles(generationModel, projectModel)
            messageDelegate.showSuccessMessage()
        } catch (e: CustomPluginException) {
            messageDelegate.onPluginExceptionHandled(e)
        } finally {
            environmentDelegate.refreshProject(projectModel)
        }
    }
}
