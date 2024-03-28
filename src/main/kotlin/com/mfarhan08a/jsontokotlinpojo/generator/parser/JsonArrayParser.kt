package com.mfarhan08a.jsontokotlinpojo.generator.parser

import com.mfarhan08a.jsontokotlinpojo.generator.properties.ClassEnum
import com.mfarhan08a.jsontokotlinpojo.generator.properties.ClassField
import com.mfarhan08a.jsontokotlinpojo.generator.properties.ClassItem
import com.mfarhan08a.jsontokotlinpojo.generator.properties.JsonModel.*
import com.mfarhan08a.jsontokotlinpojo.generator.properties.templates.ImportsTemplate
import com.mfarhan08a.jsontokotlinpojo.generator.utils.ClassGenerateHelper
import org.json.JSONArray
import org.json.JSONObject

internal class JsonArrayParser(
    private val classGenerateHelper: ClassGenerateHelper
) {

    fun parseJsonArray(
        jsonItemArray: JsonItemArray,
        classesMap: LinkedHashMap<String?, ClassItem>,
        classItem: ClassItem,
        jsonCallback: (innerJsonItem: JsonItem, classesMap: LinkedHashMap<String?, ClassItem>) -> Unit
    ) {
        val jsonArray = jsonItemArray.jsonObject
        classItem.classImports.add(ImportsTemplate.LIST)
        val classField = ClassField()
        if (jsonArray.length() == 0) {
            classField.classField = ClassField(ClassEnum.OBJECT)
            classItem.classFields[jsonItemArray.key] = classField
        } else {
            parseNonEmptyArray(JsonItemArray(jsonItemArray.key, jsonArray), classField, classesMap, jsonCallback)
            classItem.classFields[jsonItemArray.key] = classField
        }
    }

    fun parseNonEmptyArray(
        jsonItemArray: JsonItemArray,
        classField: ClassField,
        classesMap: LinkedHashMap<String?, ClassItem>,
        jsonCallback: (innerJsonItem: JsonItem, classesMap: LinkedHashMap<String?, ClassItem>) -> Unit
    ) {
        val itemName = classGenerateHelper.getClassNameWithItemPostfix(jsonItemArray.key)
        if (jsonItemArray.jsonObject.length() != 0) {
            val itemObject = jsonItemArray.jsonObject.first()
            val classFieldsParser = object : ClassFieldsParser() {

                override fun onPlainTypeRecognised(classEnum: ClassEnum?) {
                    classField.classField = ClassField(classEnum)
                }

                override fun onJsonTypeRecognised() {
                    val size = jsonItemArray.jsonObject.length()
                    val innerItemsMap = LinkedHashMap<String?, ClassItem>()
                    for (index in 0 until size) {
                        (jsonItemArray.jsonObject[index] as? JSONObject)?.let {
                            val jsonItem = JsonItem(itemName, it)
                            jsonCallback.invoke(jsonItem, innerItemsMap)
                        }
                    }
                    classField.classField = ClassField(null, itemName)
                    innerItemsMap.forEach { (key: String?, value: ClassItem) ->
                        val existing = classesMap[key]
                        if (existing != null) {
                            value.classFields.forEach { (classKey: String?, classValue: ClassField?) ->
                                existing.classFields.putIfAbsent(classKey, classValue)
                            }
                            existing.classImports.addAll(value.classImports)
                        } else {
                            classesMap[key] = value
                        }
                    }
                }

                override fun onJsonArrayTypeRecognised() {
                    classField.classField = ClassField()
                    parseNonEmptyArray(
                        JsonItemArray(itemName, (itemObject as JSONArray)),
                        classField.classField!!,
                        classesMap,
                        jsonCallback
                    )
                }
            }
            classFieldsParser.parseField(itemObject)
        } else {
            classField.classField = ClassField(ClassEnum.OBJECT)
        }
    }
}
