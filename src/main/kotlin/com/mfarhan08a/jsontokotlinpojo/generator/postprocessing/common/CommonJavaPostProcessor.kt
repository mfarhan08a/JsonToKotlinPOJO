package com.mfarhan08a.jsontokotlinpojo.generator.postprocessing.common

import com.mfarhan08a.jsontokotlinpojo.core.models.FieldModel
import com.mfarhan08a.jsontokotlinpojo.core.models.GenerationModel
import com.mfarhan08a.jsontokotlinpojo.core.models.Visibility
import com.mfarhan08a.jsontokotlinpojo.generator.properties.ClassItem
import com.mfarhan08a.jsontokotlinpojo.generator.properties.templates.ClassTemplate
import com.mfarhan08a.jsontokotlinpojo.generator.utils.ClassGenerateHelper
import com.mfarhan08a.jsontokotlinpojo.generator.utils.ClassTemplateHelper

internal class CommonJavaPostProcessor(
    generateHelper: ClassGenerateHelper,
    classTemplateHelper: ClassTemplateHelper
) : JavaPostProcessor(generateHelper, classTemplateHelper) {

    override fun proceedClassBody(
        classItem: ClassItem,
        generationModel: GenerationModel
    ): String {
        val classBodyBuilder = StringBuilder()
        val classMethodBuilder = StringBuilder()
        val classFields = classItem.classFields
        with(classBodyBuilder) {
            for (objectName in classFields.keys) {
                val classItemValue = classFields[objectName]?.getJavaItem(
                    primitive = generationModel.javaPrimitives
                )
                val itemNameFormatted = generateHelper.formatClassField(objectName)
                append(
                    classTemplateHelper.createField(
                        FieldModel(
                            classType = classItemValue,
                            fieldNameFormatted = itemNameFormatted,
                            fieldName = objectName,
                            annotation = classItem.annotation,
                            visibility = if (generationModel.useLombokValue) {
                                Visibility.NONE
                            } else {
                                Visibility.PRIVATE
                            }
                        )
                    )
                )
            }
            for (objectName in classFields.keys) {
                val classItemValue = classFields[objectName]?.getJavaItem(
                    primitive = generationModel.javaPrimitives
                )
                val itemNameFormatted = generateHelper.formatClassField(objectName)
                if (generationModel.useSetters) {
                    append(ClassTemplate.NEW_LINE)
                    append(
                        classTemplateHelper.createSetter(
                            itemNameFormatted,
                            classItemValue
                        )
                    )
                }
                if (generationModel.useGetters) {
                    append(ClassTemplate.NEW_LINE)
                    append(
                        classTemplateHelper.createGetter(
                            itemNameFormatted,
                            classItemValue
                        )
                    )
                }
            }
            if (generationModel.useStrings) {
                append(ClassTemplate.NEW_LINE)
                append(
                    classTemplateHelper.createToString(
                        classItem
                    )
                )
            }
            append(classMethodBuilder)
            return toString()
        }
    }

    override fun createClassTemplate(
        classItem: ClassItem,
        classBody: String?,
        generationModel: GenerationModel
    ) = classTemplateHelper.createClassBody(classItem, classBody)
}
