package com.mfarhan08a.jsontokotlinpojo.main.listeners

import com.mfarhan08a.jsontokotlinpojo.core.delegates.MessageDelegate
import com.mfarhan08a.jsontokotlinpojo.core.errors.CustomPluginException
import com.mfarhan08a.jsontokotlinpojo.generator.utils.ClassGenerateHelper
import com.mfarhan08a.jsontokotlinpojo.main.form.GeneratorView
import com.mfarhan08a.jsontokotlinpojo.main.view.ViewModelMapper
import java.awt.event.ActionEvent
import java.awt.event.ActionListener

internal class GenerateActionListener(
    private val generatorView: GeneratorView,
    private val eventListener: GuiFormEventListener,
    private val messageDelegate: MessageDelegate,
    private val classGenerateHelper: ClassGenerateHelper,
    private val viewModelMapper: ViewModelMapper
) : ActionListener {

    override fun actionPerformed(actionEvent: ActionEvent) = with(generatorView) {
        try {
            classGenerateHelper.validateClassName(className.text)
            classGenerateHelper.validateJsonContent(textArea.text)
            eventListener.onJsonDataObtained(viewModelMapper.map(generatorView))
        } catch (exception: CustomPluginException) {
            messageDelegate.onPluginExceptionHandled(exception)
        }
    }
}