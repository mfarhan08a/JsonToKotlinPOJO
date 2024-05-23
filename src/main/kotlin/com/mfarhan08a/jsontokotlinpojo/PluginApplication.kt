package com.mfarhan08a.jsontokotlinpojo

import com.intellij.openapi.actionSystem.AnActionEvent
import com.mfarhan08a.jsontokotlinpojo.main.controllers.JsonToKotlinClassActionController
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin

object PluginApplication : KoinComponent {
    init {
        GlobalContext.getOrNull() ?: startKoin {
            modules(
                appModule
            )
        }
    }

    private val controller: JsonToKotlinClassActionController by inject()

    fun actionPerformed(actionEvent: AnActionEvent) {
        controller.onActionHandled(actionEvent)
    }
}