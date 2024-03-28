package com.mfarhan08a.jsontokotlinpojo.generator.postprocessing.common

import com.mfarhan08a.jsontokotlinpojo.core.models.FieldModel
import com.mfarhan08a.jsontokotlinpojo.core.models.GenerationModel
import com.mfarhan08a.jsontokotlinpojo.generator.properties.ClassItem
import com.mfarhan08a.jsontokotlinpojo.generator.properties.templates.ClassTemplate
import com.mfarhan08a.jsontokotlinpojo.generator.utils.ClassGenerateHelper
import com.mfarhan08a.jsontokotlinpojo.generator.utils.ClassTemplateHelper

internal class AutoValueClassPostProcessor(
    generateHelper: ClassGenerateHelper,
    classTemplateHelper: ClassTemplateHelper
) : JavaPostProcessor(generateHelper, classTemplateHelper) {

    override fun proceedClassBody(
        classItem: ClassItem,
        generationModel: GenerationModel
    ): String {
        val classBodyBuilder = StringBuilder()
        val classFields = classItem.classFields
        with(classBodyBuilder) {
            for (objectName in classFields.keys) {
                append(
                    classTemplateHelper.createAutoValueField(
                        FieldModel(
                            classType = classFields[objectName]?.getJavaItem(),
                            annotation = classItem.annotation,
                            fieldName = objectName,
                            fieldNameFormatted = generateHelper.formatClassField(objectName)
                        )
                    )
                )
            }
            append(ClassTemplate.NEW_LINE)
            append(classTemplateHelper.createTypeAdapter(classItem))
            return toString()
        }
    }

    override fun createClassTemplate(
        classItem: ClassItem,
        classBody: String?,
        generationModel: GenerationModel
    ) = classTemplateHelper.createClassBodyAbstract(classItem, classBody)
}
