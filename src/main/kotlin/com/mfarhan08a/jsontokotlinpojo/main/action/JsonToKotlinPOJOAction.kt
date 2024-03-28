package com.mfarhan08a.jsontokotlinpojo.main.action

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.mfarhan08a.jsontokotlinpojo.PluginApplication

class JsonToKotlinPOJOAction : AnAction() {
    override fun actionPerformed(event: AnActionEvent) {
        return PluginApplication.actionPerformed(event)
    }
}