package com.github.narcisdpr.kotlintoswift.action

import com.github.narcisdpr.kotlintoswift.convertLibrary.KotlinToSwiftTokenizer
import com.github.narcisdpr.kotlintoswift.convertLibrary.Replacement
import com.github.narcisdpr.kotlintoswift.convertLibrary.listFiles
import com.github.narcisdpr.kotlintoswift.convertLibrary.loadReplacementList
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ui.Messages
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption
import java.util.ArrayList


private const val sourcePath = "/Users/narges/IdeaProjects/kotlin-to-swift/kotlin-source"
private const val destinationPath = "/Users/narges/IdeaProjects/kotlin-to-swift/swift-generated"
private const val preInstructionPath = "/Users/narges/IdeaProjects/kotlin-to-swift/preInstructions.json"

class ConvertAction: AnAction() {
    override fun update(e: AnActionEvent) {
        super.update(e)
    }

    override fun actionPerformed(e: AnActionEvent) {

        val result =Messages.showOkCancelDialog(
            e.project,
            "Are you sure you want to convert to swift? there might be some errors",
            "Convert Kotlin To Swift",
            "OK",
            "Cancel",
            Messages.getInformationIcon()
        )

        if (result == Messages.OK) {
            // The user clicked the "OK" button
            // Call your function here
            demoParser()
        }

    }

    fun demoParser() {
        val sourcePath = sourcePath
        val destinationPath = destinationPath
        val replacements: List<Replacement> = loadReplacementList((Paths.get(preInstructionPath).toFile()))
        val sourceFiles = ArrayList<File>()
        val destinationFiles = ArrayList<File>()
        listFiles(sourcePath, sourceFiles, ".kt")
        val parser = KotlinToSwiftTokenizer(replacements)
        for (file in sourceFiles) {
            val lines = Files.readAllLines(Paths.get(file.path), Charsets.UTF_8)
            parser.parse(lines)
        }
        for (file in sourceFiles) {
            val lines = Files.readAllLines(Paths.get(file.path), Charsets.UTF_8)
            val destLines = parser.parser(lines)

            val destPath = Paths.get(file.path.replace(sourcePath, destinationPath).replace(".kt", ".swift"))
            destinationFiles.add(destPath.toFile())

            val parentDir = destPath.parent.toFile()
            if(!parentDir.exists()) {
                parentDir.mkdirs()
            }
            Files.write(destPath, destLines, Charsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)
        }

    }
}