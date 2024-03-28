package com.mfarhan08a.jsontokotlinpojo.generator.utils

import com.google.common.base.CaseFormat
import com.mfarhan08a.jsontokotlinpojo.core.errors.JSONStructureException
import com.mfarhan08a.jsontokotlinpojo.core.errors.WrongClassNameException
import com.mfarhan08a.jsontokotlinpojo.generator.properties.ClassItem
import com.mfarhan08a.jsontokotlinpojo.generator.properties.ReservedWords
import com.mfarhan08a.jsontokotlinpojo.generator.properties.templates.ArrayItemsTemplate
import com.mfarhan08a.jsontokotlinpojo.generator.properties.templates.ClassTemplate
import org.json.JSONArray
import org.json.JSONObject

class ClassGenerateHelper {
    fun validateJsonContent(content: String): String {
        try {
            JSONObject(content)
        } catch (exception: Exception) {
            return try {
                val jsonArray = JSONArray(content)
                if (jsonArray.length() > 0) {
                    val jsonObject = jsonArray.optJSONObject(0)
                    if (jsonObject.keySet().isEmpty()) {
                        throw JSONStructureException()
                    }
                    jsonObject.toString()
                } else {
                    throw JSONStructureException()
                }
            } catch (arrayException: Exception) {
                throw JSONStructureException()
            }
        }
        return content
    }

    fun validateClassName(name: String?) {
        if (name?.matches(NAME_PATTERN) != true) {
            throw WrongClassNameException()
        }
    }

    fun updateClassBody(classBody: String?): String? {
        if (null != classBody && classBody.isNotEmpty()) {
            val lastIndex = classBody.length - 1
            if (classBody[lastIndex] == '\n') {
                return classBody.substring(0, lastIndex)
            }
        }
        return classBody
    }

    fun formatClassName(name: String) = upperCaseName(proceedField(name))

    fun getClassNameWithItemPostfix(name: String) =
        String.format(ArrayItemsTemplate.ITEM_NAME, upperCaseName(proceedField(name)))

    fun upperCaseName(name: String) = if (name.length > 1) {
        Character.toUpperCase(name.first()).toString() + name.substring(1)
    } else {
        name.uppercase()
    }

    fun formatClassField(name: String) = lowerCaseFirst(proceedField(name), forceLowerCase = true)

    fun lowerCaseFirst(name: String, forceLowerCase: Boolean = false) = if (name.length > 1) {
        Character.toLowerCase(name.first()).toString() + name.substring(1)
    } else if (forceLowerCase) {
        name.lowercase()
    } else {
        name
    }

    internal fun setAnnotations(
        classItem: ClassItem,
        classAnnotation: String,
        annotation: String,
        imports: Array<String>
    ) {
        classItem.classAnnotation = classAnnotation
        classItem.annotation = annotation
        classItem.classImports.addAll(imports)
    }

    fun updateClassModel(classBodyBuilder: StringBuilder) {
        if (classBodyBuilder.isEmpty()) {
            classBodyBuilder.append(ClassTemplate.FIELD_KOTLIN_DOT_DEFAULT)
        } else {
            classBodyBuilder.deleteCharAt(classBodyBuilder.lastIndexOf(","))
        }
    }

    fun proceedField(fieldName: String): String {
        var objectName = fieldName
        objectName = objectName
            .replace("[^A-Za-z0-9]".toRegex(), "_")
            .replace("_{2,}".toRegex(), "_")
        val isDigitFirst = (
                objectName.isNotBlank() && Character.isDigit(objectName.first()) ||
                        objectName.length > 1 && objectName.first() == '_' &&
                        Character.isDigit(objectName[1])
                )
        if (objectName.isBlank() || isDigitFirst || ReservedWords.WORDS_SET.contains(objectName)) {
            objectName = "json_member_$objectName"
        }
        objectName = objectName.replace("([A-Z])".toRegex(), "_$1")
        return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, objectName)
    }
}

private val NAME_PATTERN = "^[a-zA-Z0-9]*$".toRegex()