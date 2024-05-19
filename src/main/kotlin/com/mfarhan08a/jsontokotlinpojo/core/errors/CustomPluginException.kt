package com.mfarhan08a.jsontokotlinpojo.core.errors

open class CustomPluginException(
    val header: String,
    message: String?
) : Exception(message)

class FileWriteException(
    message: String?
) : CustomPluginException("File creation exception:", message)

class JSONStructureException :
    CustomPluginException("JSON exception:", "incorrect structure")

class PathException :
    CustomPluginException(
        "No directory was selected:",
        "You should choose directory for POJO files, before call running this tools"
    )

class WrongClassNameException :
    CustomPluginException("Wrong class name:", "you should set output file name")