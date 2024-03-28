package com.mfarhan08a.jsontokotlinpojo.generator.parser

import com.mfarhan08a.jsontokotlinpojo.generator.properties.ClassEnum
import com.mfarhan08a.jsontokotlinpojo.generator.properties.ClassEnum.*
import org.json.JSONArray
import org.json.JSONObject

internal abstract class ClassFieldsParser {

    fun parseField(targetItem: Any) {
        when (targetItem) {
            is JSONObject -> onJsonTypeRecognised()
            is JSONArray -> onJsonArrayTypeRecognised()
            is String -> onPlainTypeRecognised(STRING)
            is Int -> onPlainTypeRecognised(INTEGER)
            is Double -> onPlainTypeRecognised(DOUBLE)
            is Float -> onPlainTypeRecognised(FLOAT)
            is Long -> onPlainTypeRecognised(LONG)
            is Boolean -> onPlainTypeRecognised(BOOLEAN)
            else -> onPlainTypeRecognised(OBJECT)
        }
    }

    abstract fun onPlainTypeRecognised(classEnum: ClassEnum?)

    abstract fun onJsonTypeRecognised()

    abstract fun onJsonArrayTypeRecognised()
}