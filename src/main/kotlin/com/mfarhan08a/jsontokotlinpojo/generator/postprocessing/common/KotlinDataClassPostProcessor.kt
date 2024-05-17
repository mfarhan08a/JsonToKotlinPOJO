package com.mfarhan08a.jsontokotlinpojo.generator.postprocessing.common

import com.mfarhan08a.jsontokotlinpojo.core.models.FieldModel
import com.mfarhan08a.jsontokotlinpojo.core.models.FrameworkVW.*
import com.mfarhan08a.jsontokotlinpojo.core.models.GenerationModel
import com.mfarhan08a.jsontokotlinpojo.generator.postprocessing.BasePostProcessor
import com.mfarhan08a.jsontokotlinpojo.generator.properties.ClassItem
import com.mfarhan08a.jsontokotlinpojo.generator.properties.annotations.KotlinAnnotations
import com.mfarhan08a.jsontokotlinpojo.generator.properties.templates.ClassTemplate
import com.mfarhan08a.jsontokotlinpojo.generator.properties.templates.ImportsTemplate
import com.mfarhan08a.jsontokotlinpojo.generator.properties.templates.PARCELABLE_ANDROID
import com.mfarhan08a.jsontokotlinpojo.generator.properties.templates.PARCELIZE_KOTLINX
import com.mfarhan08a.jsontokotlinpojo.generator.utils.ClassGenerateHelper
import com.mfarhan08a.jsontokotlinpojo.generator.utils.ClassTemplateHelper

internal class KotlinDataClassPostProcessor(
    generateHelper: ClassGenerateHelper,
    classTemplateHelper: ClassTemplateHelper,
) : BasePostProcessor(generateHelper, classTemplateHelper) {

    override fun proceedClassImports(
        imports: HashSet<String>,
        generationModel: GenerationModel
    ): StringBuilder {
        imports.remove(ImportsTemplate.LIST)
        if (generationModel.useKotlinParcelable) {
            imports.add(PARCELABLE_ANDROID)
            imports.add(PARCELIZE_KOTLINX)
        }
        val importsBuilder = StringBuilder()
        for (importItem in imports) {
            importsBuilder.append(importItem.replace(";", ""))
            importsBuilder.append(ClassTemplate.NEW_LINE)
        }
        return importsBuilder
    }

    override fun proceedClassBody(
        classItem: ClassItem,
        generationModel: GenerationModel
    ): String {
        val classBodyBuilder = StringBuilder()
        val classFields = classItem.classFields
        for (objectName in classFields.keys) {
            if (generationModel.annotationEnum is KotlinSerialization) {
                classBodyBuilder.append(
                    classTemplateHelper.createKotlinDataClassField(
                        generationModel,
                        FieldModel(
                            classType = classFields[objectName]?.getKotlinItem(),
                            annotation = if (objectName == generateHelper.formatClassField(objectName)) null else classItem.annotation,
                            fieldName = objectName,
                            fieldNameFormatted = generateHelper.formatClassField(objectName)
                        )
                    )
                )
            } else {
                classBodyBuilder.append(
                    classTemplateHelper.createKotlinDataClassField(
                        generationModel,
                        FieldModel(
                            classType = classFields[objectName]?.getKotlinItem(),
                            annotation = classItem.annotation,
                            fieldName = objectName,
                            fieldNameFormatted = generateHelper.formatClassField(objectName)
                        )
                    )
                )
            }

        }
        generateHelper.updateClassModel(classBodyBuilder)
        return classBodyBuilder.toString()
    }

    override fun createClassItemText(
        packagePath: String?,
        imports: String?,
        classTemplate: String?
    ) = classTemplateHelper
        .createClassItemWithoutSemicolon(
            packagePath,
            imports,
            classTemplate
        )

    override fun applyAnnotations(
        generationModel: GenerationModel,
        classItem: ClassItem
    ) = when (generationModel.annotationEnum) {
        is Gson -> {
            generateHelper.setAnnotations(
                classItem,
                KotlinAnnotations.Gson.classAnnotation,
                KotlinAnnotations.Gson.annotation,
                ImportsTemplate.Gson.imports
            )
        }

        is KotlinSerialization -> {
            generateHelper.setAnnotations(
                classItem,
                KotlinAnnotations.KotlinSerialization.classAnnotation,
                KotlinAnnotations.KotlinSerialization.annotation,
                ImportsTemplate.KotlinSerialization.imports
            )
        }

        else -> {
            // NO OP
        }
    }

    override fun createClassTemplate(
        classItem: ClassItem,
        classBody: String?,
        generationModel: GenerationModel
    ) = classTemplateHelper.createClassBodyKotlinDataClass(
        classItem,
        classBody,
        generationModel
    )
}
