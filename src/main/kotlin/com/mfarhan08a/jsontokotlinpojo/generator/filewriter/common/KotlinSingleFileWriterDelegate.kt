package com.mfarhan08a.jsontokotlinpojo.generator.filewriter.common

import com.mfarhan08a.jsontokotlinpojo.core.delegates.MessageDelegate
import com.mfarhan08a.jsontokotlinpojo.core.delegates.PreWriterDelegate
import com.mfarhan08a.jsontokotlinpojo.core.models.GenerationModel
import com.mfarhan08a.jsontokotlinpojo.core.models.ProjectModel
import com.mfarhan08a.jsontokotlinpojo.generator.filewriter.BaseWriterDelegate
import com.mfarhan08a.jsontokotlinpojo.generator.filewriter.FileWriter
import com.mfarhan08a.jsontokotlinpojo.generator.postprocessing.KotlinDataClassPostProcessor
import com.mfarhan08a.jsontokotlinpojo.generator.properties.ClassItem
import com.mfarhan08a.jsontokotlinpojo.generator.properties.templates.ClassTemplate

internal class KotlinSingleFileWriterDelegate(
    messageDelegate: MessageDelegate,
    postProcessor: KotlinDataClassPostProcessor,
    fileWriterDelegate: FileWriter,
    preWriterDelegate: PreWriterDelegate,
    private val kotlinDataClassPostProcessor: KotlinDataClassPostProcessor
) : BaseWriterDelegate(messageDelegate, postProcessor, fileWriterDelegate, preWriterDelegate) {

    override fun writeFiles(
        set: Set<ClassItem>,
        generationModel: GenerationModel,
        projectModel: ProjectModel
    ) {
        val imports = resolveImports(set, generationModel)
        val targets = set.toMutableList()
        targets.firstOrNull { it.className == generationModel.rootClassName }?.let {
            val index = targets.indexOf(it)
            targets.removeAt(index)
            targets.add(FIRST_TARGET_POSITION, it)
        }
        val rootClassBuilder = StringBuilder()
        targets.forEachIndexed { index, it ->
            it.apply {
                it.classImports.clear()
                it.packagePath = null
                if (index > 0) {
                    rootClassBuilder.append(ClassTemplate.NEW_LINE)
                }
                rootClassBuilder.append(prepareClass(it, generationModel))
            }
        }
        val classBody = kotlinDataClassPostProcessor.createClassItemText(
            packagePath = projectModel.packageName,
            classTemplate = rootClassBuilder.toString(),
            imports = kotlinDataClassPostProcessor.proceedClassImports(imports, generationModel).toString()
        )
        writeFile(
            className = generationModel.rootClassName,
            classItemBody = classBody,
            generationModel = generationModel,
            projectModel = projectModel
        )
    }

    private fun resolveImports(
        set: Set<ClassItem>,
        generationModel: GenerationModel
    ): HashSet<String> {
        val imports = HashSet<String>().apply {
            set.forEach { addAll(it.classImports) }
        }
        val universalClassItem = ClassItem()
        kotlinDataClassPostProcessor.applyAnnotations(generationModel, universalClassItem)
        imports.addAll(universalClassItem.classImports)
        return imports
    }
}

private const val FIRST_TARGET_POSITION = 0
