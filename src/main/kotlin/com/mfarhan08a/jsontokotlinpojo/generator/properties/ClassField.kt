package com.mfarhan08a.jsontokotlinpojo.generator.properties

import com.mfarhan08a.jsontokotlinpojo.generator.properties.templates.ArrayItemsTemplate

internal class ClassField(
    private var classEnum: ClassEnum? = null,
    var className: String? = null,
    var classField: ClassField? = null
) {
    fun getKotlinItem(): String? {
        return if (null != classField) {
            String.format(
                ArrayItemsTemplate.LIST_OF_ITEM,
                classField?.getKotlinItem()
            )
        } else {
            className ?: classEnum?.kotlin
        }
    }
}