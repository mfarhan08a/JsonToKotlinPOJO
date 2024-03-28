package com.mfarhan08a.jsontokotlinpojo.main.persistense

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.util.xmlb.XmlSerializerUtil
import com.mfarhan08a.jsontokotlinpojo.core.models.GenerationModel

@State(name = "GenerationState")
internal class ViewStateService : PersistentStateComponent<PluginState> {
    private var state = PluginState()

    override fun getState() = state

    fun persistModel(model: GenerationModel) {
        this.state = PluginState(model)
    }

    override fun loadState(state: PluginState) {
        XmlSerializerUtil.copyBean(state, this)
    }
}

data class PluginState(
    val model: GenerationModel? = null
)