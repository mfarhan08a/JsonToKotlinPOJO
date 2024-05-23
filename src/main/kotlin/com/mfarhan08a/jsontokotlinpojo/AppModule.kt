package com.mfarhan08a.jsontokotlinpojo

import com.mfarhan08a.jsontokotlinpojo.core.delegates.*
import com.mfarhan08a.jsontokotlinpojo.core.delegates.EnvironmentDelegateImpl
import com.mfarhan08a.jsontokotlinpojo.core.delegates.MessageDelegateImpl
import com.mfarhan08a.jsontokotlinpojo.generator.ClassCreator
import com.mfarhan08a.jsontokotlinpojo.generator.GenerationDelegate
import com.mfarhan08a.jsontokotlinpojo.generator.GenerationDelegateImpl
import com.mfarhan08a.jsontokotlinpojo.generator.JsonToKotlinClassGenerator
import com.mfarhan08a.jsontokotlinpojo.generator.filewriter.FileDelegateFactory
import com.mfarhan08a.jsontokotlinpojo.generator.filewriter.FileWriter
import com.mfarhan08a.jsontokotlinpojo.generator.filewriter.common.CommonFileWriterDelegate
import com.mfarhan08a.jsontokotlinpojo.generator.filewriter.common.KotlinSingleFileWriterDelegate
import com.mfarhan08a.jsontokotlinpojo.generator.parser.InputDataParser
import com.mfarhan08a.jsontokotlinpojo.generator.parser.JsonArrayParser
import com.mfarhan08a.jsontokotlinpojo.generator.parser.JsonObjectParser
import com.mfarhan08a.jsontokotlinpojo.generator.postprocessing.KotlinDataClassPostProcessor
import com.mfarhan08a.jsontokotlinpojo.generator.utils.ClassGenerateHelper
import com.mfarhan08a.jsontokotlinpojo.generator.utils.ClassTemplateHelper
import com.mfarhan08a.jsontokotlinpojo.generator.utils.ProcessingModelResolver
import com.mfarhan08a.jsontokotlinpojo.main.controllers.JsonToKotlinClassActionController
import com.mfarhan08a.jsontokotlinpojo.main.persistense.ViewStateService
import com.mfarhan08a.jsontokotlinpojo.main.view.GeneratorViewBinder
import com.mfarhan08a.jsontokotlinpojo.main.view.GeneratorViewFactory
import com.mfarhan08a.jsontokotlinpojo.main.view.PropertiesFactory
import com.mfarhan08a.jsontokotlinpojo.main.view.ViewModelMapper
import com.mfarhan08a.jsontokotlinpojo.main.view.ViewStateManager
import org.koin.dsl.module

val appModule = module {

    //app
    single {
        JsonToKotlinClassActionController(get(), get(), get(), get(), get())
    }

    single {
        GeneratorViewFactory(get(), get(), get(), get())
    }

    single {
        ViewStateManager(get())
    }

    single {
        ViewModelMapper(get())
    }

    single {
        GeneratorViewBinder(get(), get())
    }

    single {
        PropertiesFactory()
    }

    single {
        ViewStateService()
    }

    //core
    single<EnvironmentDelegate> {
        EnvironmentDelegateImpl()
    }

    single<MessageDelegate> {
        MessageDelegateImpl()
    }

    single<PreWriterDelegate> {
        PreWriterDelegateImpl(get())
    }

    single {
        IndentationDelegate()
    }

    //generator
    single {
        ClassCreator(get(), get())
    }

    single {
        FileDelegateFactory(get(), get())
    }

    single {
        KotlinSingleFileWriterDelegate(get(), get(), get(), get(), get())
    }

    single<GenerationDelegate> {
        GenerationDelegateImpl(get(), get(), get())
    }

    single {
        FileWriter()
    }

    single {
        CommonFileWriterDelegate(get(), get(), get(), get())
    }

    single {
        KotlinDataClassPostProcessor(get(), get())
    }

    single {
        ClassTemplateHelper()
    }

    single {
        JsonToKotlinClassGenerator(get(), get())
    }

    single {
        ProcessingModelResolver()
    }

    single {
        InputDataParser(get(), get(), get())
    }

    single {
        JsonObjectParser(get())
    }

    single {
        JsonArrayParser(get())
    }

    single {
        ClassGenerateHelper()
    }
}