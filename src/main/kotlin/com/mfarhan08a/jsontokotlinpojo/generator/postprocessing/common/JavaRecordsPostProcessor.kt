package com.mfarhan08a.jsontokotlinpojo.generator.postprocessing.common

import com.mfarhan08a.jsontokotlinpojo.core.models.FieldModel
import com.mfarhan08a.jsontokotlinpojo.core.models.GenerationModel
import com.mfarhan08a.jsontokotlinpojo.generator.properties.ClassItem
import com.mfarhan08a.jsontokotlinpojo.generator.utils.ClassGenerateHelper
import com.mfarhan08a.jsontokotlinpojo.generator.utils.ClassTemplateHelper

internal class JavaRecordsPostProcessor(
    generateHelper: ClassGenerateHelper,
    classTemplateHelper: ClassTemplateHelper
) : JavaPostProcessor(generateHelper, classTemplateHelper) {

    override fun proceedClassBody(classItem: ClassItem, generationModel: GenerationModel): String {
        val classBodyBuilder = StringBuilder()
        val classFields = classItem.classFields
        for (objectName in classFields.keys) {
            val classItemValue = classFields[objectName]?.getJavaItem(
                primitive = generationModel.javaPrimitives
            )
            classBodyBuilder.append(
                classTemplateHelper.createJavaRecordClassField(
                    FieldModel(
                        classType = classItemValue,
                        annotation = classItem.annotation,
                        fieldName = objectName,
                        fieldNameFormatted = generateHelper.formatClassField(objectName)
                    )
                )
            )
        }
        generateHelper.updateClassModel(classBodyBuilder)
        return classBodyBuilder.toString()
    }

    override fun createClassTemplate(
        classItem: ClassItem,
        classBody: String?,
        generationModel: GenerationModel
    ) = classTemplateHelper.createClassBodyRecords(classItem, classBody)
}
