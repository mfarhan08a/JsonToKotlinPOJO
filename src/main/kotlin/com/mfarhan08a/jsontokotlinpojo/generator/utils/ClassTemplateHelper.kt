package com.mfarhan08a.jsontokotlinpojo.generator.utils

import com.mfarhan08a.jsontokotlinpojo.core.models.FieldModel
import com.mfarhan08a.jsontokotlinpojo.core.models.GenerationModel
import com.mfarhan08a.jsontokotlinpojo.generator.properties.ClassItem
import com.mfarhan08a.jsontokotlinpojo.generator.properties.templates.ClassTemplate

internal class ClassTemplateHelper {

    fun createKotlinDataClassField(generationModel: GenerationModel, model: FieldModel) =
        if (generationModel.kotlinNullableFields) {
            createAnnotatedField(
                model.fieldName, model.annotation,
                String.format(
                    ClassTemplate.FIELD_KOTLIN_DTO,
                    model.fieldNameFormatted,
                    model.classType
                ).replace(">", "?>")
            )
        } else {
            createAnnotatedField(
                model.fieldName, model.annotation,
                String.format(
                    ClassTemplate.FIELD_KOTLIN_DTO_NON_NULL,
                    model.fieldNameFormatted,
                    model.classType
                )
            )
        }

    fun createClassBodyKotlinDataClass(
        classItem: ClassItem,
        classBody: String?,
        generationModel: GenerationModel
    ) = createClassBodyAnnotated(
        classItem,
        String.format(
            generateKotlinClass(generationModel),
            classItem.className,
            classBody
        )
    )

    private fun generateKotlinClass(generationModel: GenerationModel): String {
        val body = if (generationModel.useKotlinParcelable) {
            ClassTemplate.CLASS_BODY_KOTLIN_DTO_PARCELABLE
        } else {
            ClassTemplate.CLASS_BODY_KOTLIN_DTO
        }
        return if (generationModel.useKotlinDataClass) {
            body
        } else {
            body.replace(ClassTemplate.KOTLIN_DATA_CLASS, "")
        }
    }

    fun createClassItem(
        packagePath: String?,
        imports: String?,
        body: String?
    ) = if (packagePath?.isNotEmpty() == true) {
        if (!imports.isNullOrEmpty()) {
            String.format(
                ClassTemplate.CLASS_ROOT_IMPORTS,
                packagePath,
                imports,
                body
            )
        } else {
            String.format(
                ClassTemplate.CLASS_ROOT,
                packagePath,
                body
            )
        }
    } else {
        String.format(ClassTemplate.CLASS_ROOT_NO_PACKAGE, body)
    }

    fun createClassItemWithoutSemicolon(
        packagePath: String?,
        imports: String?,
        body: String?
    ) = if (packagePath?.isNotEmpty() == true) {
        if (!imports.isNullOrEmpty()) {
            String.format(
                ClassTemplate.CLASS_ROOT_IMPORTS_WITHOUT_SEMICOLON,
                packagePath,
                imports,
                body
            )
        } else {
            String.format(
                ClassTemplate.CLASS_ROOT_WITHOUT_SEMICOLON,
                packagePath,
                body
            )
        }
    } else {
        String.format(ClassTemplate.CLASS_ROOT_NO_PACKAGE, body)
    }

    private fun createClassBodyAnnotated(
        classItem: ClassItem,
        classItemBody: String
    ) = if (classItem.classAnnotation?.isNotEmpty() == true) {
        String.format(
            ClassTemplate.CLASS_BODY_ANNOTATED,
            classItem.classAnnotation,
            classItemBody
        )
    } else {
        classItemBody
    }

    private fun createAnnotatedField(
        name: String?,
        annotation: String?,
        field: String
    ) = if (!annotation.isNullOrEmpty()) {
        String.format(
            ClassTemplate.FIELD_ANNOTATED,
            String.format(annotation, name),
            field
        )
    } else {
        ClassTemplate.NEW_LINE + field
    }
}