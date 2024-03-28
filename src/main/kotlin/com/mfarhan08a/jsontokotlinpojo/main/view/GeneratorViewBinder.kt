package com.mfarhan08a.jsontokotlinpojo.main.view

import com.mfarhan08a.jsontokotlinpojo.core.models.ControlsModel
import com.mfarhan08a.jsontokotlinpojo.core.models.FrameworkVW
import com.mfarhan08a.jsontokotlinpojo.core.models.LanguageVM
import com.mfarhan08a.jsontokotlinpojo.core.models.SourceVM
import com.mfarhan08a.jsontokotlinpojo.main.form.GeneratorView
import java.awt.event.ItemEvent
import java.util.*
import javax.swing.BoxLayout
import javax.swing.JCheckBox

internal class GeneratorViewBinder(
    private val propertiesFactory: PropertiesFactory,
    private val viewStateManager: ViewStateManager
) {
    var properties: ControlsModel? = null

    fun bindView(generatorVew: GeneratorView) {
        properties = propertiesFactory.createControls()
        viewStateManager.restoreState(properties)
        viewStateManager.restoreCommonProperties(generatorVew)
        bindSource(generatorVew)
        bindLanguage(generatorVew)
        bindFrameworksAndProperties(generatorVew)
    }

    private fun bindFrameworksAndProperties(generatorVew: GeneratorView) {
        bindFrameworks(generatorVew)
        bindAdditionalProperties(generatorVew)
    }

    private fun bindAdditionalProperties(generatorVew: GeneratorView) {
        generatorVew.propertiesPanel.apply {
            removeAll()
            layout = BoxLayout(this, BoxLayout.PAGE_AXIS)
            with(properties?.selectedSource?.selectedLanguage) {
                this?.selectedFramework?.properties?.let { properties ->
                    properties.forEach { itemProperty ->
                        add(
                            JCheckBox(itemProperty.propertyName).apply {
                                isSelected = itemProperty.selected
                                addItemListener { itemEvent ->
                                    properties.firstOrNull {
                                        it.propertyName == text
                                    }?.let { targetProperty ->
                                        targetProperty.selected = itemEvent.stateChange == ItemEvent.SELECTED
                                    }
                                }
                            }
                        )
                    }
                }
            }
            revalidate()
            repaint()
        }
    }

    private fun bindSource(generatorVew: GeneratorView) {
        with(generatorVew) {
            JSONRadioButton.addItemListener { event ->
                if (event.stateChange == ItemEvent.SELECTED) {
                    resolveSource(SourceVM.JSON)
                }
            }
            JSONSchemaRadioButton.addItemListener { event ->
                if (event.stateChange == ItemEvent.SELECTED) {
                    resolveSource(SourceVM.JSON_SCHEMA)
                }
            }
            // TODO: disable when Json Schema support will be added
            sourcePanel.isVisible = false
        }
    }

    private fun bindLanguage(generatorVew: GeneratorView) {
        with(generatorVew) {
            javaRadioButton.addItemListener { event ->
                if (event.stateChange == ItemEvent.SELECTED) {
                    resolveLanguage(LanguageVM.JAVA)
                    bindFrameworksAndProperties(generatorVew)
                }
            }
            kotlinRadioButton.addItemListener { event ->
                if (event.stateChange == ItemEvent.SELECTED) {
                    resolveLanguage(LanguageVM.KOTLIN)
                    bindFrameworksAndProperties(generatorVew)
                }
            }
            kotlinRadioButton.isSelected = properties?.selectedSource?.selectedLanguage is LanguageVM.Kotlin
        }
    }

    private fun resolveSource(key: String) {
        with(properties) {
            this?.selectedSource = this?.sources?.firstOrNull {
                it.propertyName == key
            }
        }
    }

    private fun resolveLanguage(key: String) {
        with(properties?.selectedSource) {
            this?.selectedLanguage = this?.languages?.firstOrNull {
                it.propertyName == key
            }
        }
    }

    private fun bindFrameworks(generatorVew: GeneratorView) {
        generatorVew.frameworkList.apply {
            removeAll()
            setListData(
                Vector<String>().apply {
                    properties?.selectedSource?.selectedLanguage?.frameworks?.let { list ->
                        addAll(list.map { it.propertyName })
                    }
                }
            )
            selectedIndex = with(properties?.selectedSource?.selectedLanguage) {
                this?.selectedFramework?.let {
                    this.frameworks.indexOf(selectedFramework as FrameworkVW)
                } ?: 0
            }
            addListSelectionListener { selectionEvent ->
                if (!selectionEvent.valueIsAdjusting) {
                    with(properties?.selectedSource?.selectedLanguage) {
                        this?.frameworks?.firstOrNull {
                            it.propertyName == selectedValue
                        }?.let {
                            selectedFramework = it
                            bindAdditionalProperties(generatorVew)
                        }
                    }
                }
            }
        }
    }
}