package com.mfarhan08a.jsontokotlinpojo.main.controllers

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ui.DialogBuilder
import com.mfarhan08a.jsontokotlinpojo.core.delegates.EnvironmentDelegate
import com.mfarhan08a.jsontokotlinpojo.core.delegates.MessageDelegate
import com.mfarhan08a.jsontokotlinpojo.core.errors.CustomPluginException
import com.mfarhan08a.jsontokotlinpojo.core.errors.WrongClassNameException
import com.mfarhan08a.jsontokotlinpojo.core.models.GenerationModel
import com.mfarhan08a.jsontokotlinpojo.generator.GenerationDelegate
import com.mfarhan08a.jsontokotlinpojo.main.listeners.GuiFormEventListener
import com.mfarhan08a.jsontokotlinpojo.main.persistense.ViewStateService
import com.mfarhan08a.jsontokotlinpojo.main.view.GeneratorViewFactory

internal class JsonToKotlinPOJOActionController(
    private val environmentDelegate: EnvironmentDelegate,
    private val messageDelegate: MessageDelegate,
    private val generatorViewFactory: GeneratorViewFactory,
    private val generationDelegate: GenerationDelegate,
    private val viewStateService: ViewStateService
) {

    fun onActionHandled(event: AnActionEvent) = try {
        proceed(event)
    } catch (exception: CustomPluginException) {
        messageDelegate.onPluginExceptionHandled(exception)
    }

    private fun proceed(event: AnActionEvent) {
        val projectModel = environmentDelegate.obtainProjectModel(event)
        val dialogBuilder = DialogBuilder()
        val window = dialogBuilder.window
        generatorViewFactory.bindView(
            dialogBuilder,
            object : GuiFormEventListener {
                override fun onJsonDataObtained(model: GenerationModel) {
                    if (model.rootClassName.isBlank()) {
                        messageDelegate.onPluginExceptionHandled(WrongClassNameException())
                    } else {
                        viewStateService.persistModel(model)
                        window.dispose()
                        generationDelegate.runGenerationTask(model, projectModel)
                    }
                }
            }
        )
    }
}