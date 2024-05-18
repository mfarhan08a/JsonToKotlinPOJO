package com.mfarhan08a.jsontokotlinpojo.core.models

import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiDirectory

data class GenerationModel(
    val rewriteClasses: Boolean,
    val useKotlin: Boolean,
    val annotationEnum: Framework,
    val rootClassName: String,
    val content: String?,
    val useKotlinSingleDataClass: Boolean,
    val useKotlinParcelable: Boolean,
    val kotlinNullableFields: Boolean,
    val useTabsIndentation: Boolean,
    val useKotlinDataClass: Boolean
)

data class ProjectModel(
    val directory: PsiDirectory,
    val packageName: String?,
    val virtualFolder: VirtualFile,
    val project: Project
)

data class FieldModel(
    val classType: String? = null,
    val fieldName: String? = null,
    val fieldNameFormatted: String? = null,
    val annotation: String? = null,
)
