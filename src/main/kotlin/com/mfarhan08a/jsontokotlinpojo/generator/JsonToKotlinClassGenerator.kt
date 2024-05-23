package com.mfarhan08a.jsontokotlinpojo.generator

import com.mfarhan08a.jsontokotlinpojo.core.models.GenerationModel
import com.mfarhan08a.jsontokotlinpojo.generator.parser.InputDataParser
import com.mfarhan08a.jsontokotlinpojo.generator.properties.ClassItem
import com.mfarhan08a.jsontokotlinpojo.generator.utils.ProcessingModelResolver

internal class JsonToKotlinClassGenerator(
    private val processor: InputDataParser,
    private val processingModelResolver: ProcessingModelResolver
) {

    fun generate(model: GenerationModel): Set<ClassItem> {
        val map = LinkedHashMap<String?, ClassItem>()
        processor.parse(
            processingModelResolver.resolveJsonModel(model),
            map
        )
        return HashSet(map.values)
    }
}