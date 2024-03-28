package com.mfarhan08a.jsontokotlinpojo.main.listeners

import com.mfarhan08a.jsontokotlinpojo.core.models.GenerationModel

internal interface GuiFormEventListener {
    fun onJsonDataObtained(model: GenerationModel)
}