package com.mfarhan08a.jsontokotlinpojo.main.view

import com.intellij.openapi.ui.DialogBuilder
import com.mfarhan08a.jsontokotlinpojo.core.delegates.MessageDelegate
import com.mfarhan08a.jsontokotlinpojo.generator.utils.ClassGenerateHelper
import com.mfarhan08a.jsontokotlinpojo.main.form.GeneratorView
import com.mfarhan08a.jsontokotlinpojo.main.listeners.GenerateActionListener
import com.mfarhan08a.jsontokotlinpojo.main.listeners.GuiFormEventListener

internal class GeneratorViewFactory(
    private val messageDelegate: MessageDelegate,
    private val classGenerateHelper: ClassGenerateHelper,
    private val generatorViewBinder: GeneratorViewBinder,
    private val viewModelMapper: ViewModelMapper
) {

    fun bindView(builder: DialogBuilder, eventListener: GuiFormEventListener) {
        val generatorView = GeneratorView()
        val actionListener = GenerateActionListener(
            generatorView = generatorView,
            eventListener = eventListener,
            messageDelegate = messageDelegate,
            classGenerateHelper = classGenerateHelper,
            viewModelMapper = viewModelMapper
        )
        with(generatorView) {
            generatorViewBinder.bindView(this)
            generateButton.addActionListener(actionListener)
            builder.setCenterPanel(rootView)
        }
        builder.apply {
            setTitle(PLUGIN_TITLE)
            removeAllActions()
            show()
        }
    }
}

private const val PLUGIN_TITLE = "JsonToKotlinPOJO"