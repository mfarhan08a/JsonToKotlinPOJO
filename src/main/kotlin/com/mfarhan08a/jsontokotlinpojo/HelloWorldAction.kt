package com.mfarhan08a.jsontokotlinpojo

import com.intellij.notification.NotificationDisplayType
import com.intellij.notification.NotificationGroup
import com.intellij.notification.NotificationType
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.project.Project

class HelloWorldAction : AnAction() {
    override fun actionPerformed(event: AnActionEvent) {
        event.project?.showNotification("Hello World")
    }

    private fun Project.showNotification(message: String) {
        NotificationGroup("someId", NotificationDisplayType.BALLOON)
            .createNotification(
                "Hello World Action: ",
                message,
                NotificationType.INFORMATION,
                null
            ).notify(this)
    }

}