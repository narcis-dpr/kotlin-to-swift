package com.github.narcisdpr.kotlintoswift.convertLibrary

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.readValue
import java.io.File
import java.util.*

fun listFiles(directoryName: String, files: ArrayList<File>, extension: String = "") {
    val directory = File(directoryName)

    val fList = directory.listFiles()

    if (fList != null) {
        for (file in fList) {
            if (file.isFile) {
                if (file.name.endsWith(extension)) {
                    files.add(file)
                }
            } else if (file.isDirectory) {
                listFiles(file.absolutePath, files, extension)
            }
        }
    }
}

fun loadReplacementList(file: File): List<Replacement> {
    val mapper = ObjectMapper().registerModule(KotlinModule())
    val result: List<Replacement> = mapper.readValue(file);
    return result;
}

fun validateError(fileName: String, hint: String) {
    println("$fileName: $hint")
}