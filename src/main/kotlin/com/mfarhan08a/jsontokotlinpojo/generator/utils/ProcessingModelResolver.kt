package com.mfarhan08a.jsontokotlinpojo.generator.utils

import com.mfarhan08a.jsontokotlinpojo.core.models.GenerationModel
import com.mfarhan08a.jsontokotlinpojo.generator.properties.JsonModel
import org.json.JSONArray
import org.json.JSONObject

internal class ProcessingModelResolver {

    fun resolveJsonModel(model: GenerationModel): JsonModel =
        try {
            JsonModel.JsonItem(jsonObject = JSONObject(model.content), key = model.rootClassName)
        } catch (e: Exception) {
            JsonModel.JsonItemArray(
                jsonObject = JSONArray(model.content),
                key = model.rootClassName
            )
        }
}