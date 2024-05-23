package com.mfarhan08a.jsontokotlinpojo.core.delegates

import com.intellij.notification.NotificationDisplayType
import com.intellij.notification.NotificationGroup
import com.intellij.notification.NotificationGroupManager
import com.intellij.notification.NotificationType
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManager
import com.intellij.openapi.ui.Messages
import com.mfarhan08a.jsontokotlinpojo.core.errors.CustomPluginException

interface MessageDelegate {
    fun onPluginExceptionHandled(exception: CustomPluginException)

    fun logEventMessage(message: String, project: Project)

    fun showSuccessMessage(project: Project)
}

internal class MessageDelegateImpl : MessageDelegate {
    override fun onPluginExceptionHandled(exception: CustomPluginException) {
        showMessage(exception.message, exception.header)
    }

    override fun logEventMessage(message: String, project: Project) {
        sendNotification(message, NotificationType.WARNING, project)
    }

    override fun showSuccessMessage(project: Project) {
        sendNotification(TITLE_SUCCESS, NotificationType.INFORMATION, project)
    }

    private fun sendNotification(
        message: String,
        type: NotificationType,
        project: Project
    ) {
        showMessage(message, type, project)
    }

    private fun showMessage(
        message: String,
        type: NotificationType,
        project: Project
    ) {
        @Suppress("DEPRECATION")
        val notificationGroup = NotificationGroup(
            displayId = GROUP_ID,
            displayType = NotificationDisplayType.BALLOON,
            isLogByDefault = true
        )
        notificationGroup.createNotification(message, type)
            .notify(project)
    }

    private fun showMessage(
        message: String?,
        header: String
    ) = Messages.showDialog(message, header, arrayOf(TITLE_OK), -1, null)

    companion object {
        private const val TITLE_OK = "OK"
        private const val TITLE_SUCCESS = "Kotlin Class generation: Success"
        private const val GROUP_ID = "KotlinClass Generator"
    }
}
