package com.mfarhan08a.jsontokotlinpojo.generator.parser

import com.mfarhan08a.jsontokotlinpojo.generator.properties.ClassEnum
import com.mfarhan08a.jsontokotlinpojo.generator.properties.ClassField
import com.mfarhan08a.jsontokotlinpojo.generator.properties.ClassItem
import com.mfarhan08a.jsontokotlinpojo.generator.properties.JsonModel.*
import com.mfarhan08a.jsontokotlinpojo.generator.properties.templates.ImportsTemplate
import com.mfarhan08a.jsontokotlinpojo.generator.utils.ClassGenerateHelper
import org.json.JSONArray
import org.json.JSONObject

internal class JsonObjectParser(
    private val classGenerateHelper: ClassGenerateHelper
) {

    fun parseJsonObject(
        jsonItem: JsonItem,
        classesMap: LinkedHashMap<String?, ClassItem>,
        classItem: ClassItem,
        jsonCallback: (innerJsonItem: JsonItem, classesMap: LinkedHashMap<String?, ClassItem>) -> Unit,
        arrayCallback: (
            innerJsonItem: JsonItemArray,
            classField: ClassField,
            classesMap: LinkedHashMap<String?, ClassItem>
        ) -> Unit
    ) {
        for (jsonObjectKey in jsonItem.jsonObject.keySet()) {
            val itemObject = jsonItem.jsonObject[jsonObjectKey]
            val classFieldsParser = object : ClassFieldsParser() {

                override fun onPlainTypeRecognised(classEnum: ClassEnum?) {
                    classItem.classFields[jsonObjectKey] = ClassField(classEnum)
                }

                override fun onJsonTypeRecognised() {
                    val className = classGenerateHelper.formatClassName(jsonObjectKey)
                    val classField = ClassField(null, className)
                    val innerJsonItem = JsonItem(jsonObjectKey, (itemObject as JSONObject))
                    classItem.classFields[jsonObjectKey] = classField
                    jsonCallback.invoke(innerJsonItem, classesMap)
                }

                override fun onJsonArrayTypeRecognised() {
                    val jsonArray = itemObject as JSONArray
                    classItem.classImports.add(ImportsTemplate.LIST)
                    val classField = ClassField()
                    if (jsonArray.length() == 0) {
                        classField.classField = ClassField(ClassEnum.OBJECT)
                        classItem.classFields[jsonObjectKey] = classField
                    } else {
                        val jsonItemArray = JsonItemArray(jsonObjectKey, itemObject)
                        arrayCallback.invoke(jsonItemArray, classField, classesMap)
                        classItem.classFields[jsonObjectKey] = classField
                    }
                }
            }
            classFieldsParser.parseField(itemObject)
        }
    }
}
