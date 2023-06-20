package com.github.narcisdpr.kotlintoswift.convertLibrary

import com.github.narcisdpr.kotlintoswift.convertLibrary.DefaultIdentifiers.ABSTRACT
import com.github.narcisdpr.kotlintoswift.convertLibrary.DefaultIdentifiers.ARRAY_MAP
import com.github.narcisdpr.kotlintoswift.convertLibrary.DefaultIdentifiers.ARRAY_SET
import com.github.narcisdpr.kotlintoswift.convertLibrary.DefaultIdentifiers.HASH_MAP_C
import com.github.narcisdpr.kotlintoswift.convertLibrary.DefaultIdentifiers.HASH_SET_C
import com.github.narcisdpr.kotlintoswift.convertLibrary.DefaultIdentifiers.LINKED_HASH_MAP
import com.github.narcisdpr.kotlintoswift.convertLibrary.DefaultIdentifiers.LINKED_HASH_SET
import com.github.narcisdpr.kotlintoswift.convertLibrary.DefaultIdentifiers.OPEN
import com.github.narcisdpr.kotlintoswift.convertLibrary.DefaultIdentifiers.OVERRIDE
import com.github.narcisdpr.kotlintoswift.convertLibrary.KotlinToSwiftRules.R_CLASS
import com.github.narcisdpr.kotlintoswift.convertLibrary.KotlinToSwiftRules.R_FUN
import com.github.narcisdpr.kotlintoswift.convertLibrary.KotlinToSwiftRules.annotationsRule
import com.github.narcisdpr.kotlintoswift.convertLibrary.KotlinToSwiftRules.argumentRule
import com.github.narcisdpr.kotlintoswift.convertLibrary.KotlinToSwiftRules.arrayListRule
import com.github.narcisdpr.kotlintoswift.convertLibrary.KotlinToSwiftRules.arrayListRuleReplacement
import com.github.narcisdpr.kotlintoswift.convertLibrary.KotlinToSwiftRules.arrayRule
import com.github.narcisdpr.kotlintoswift.convertLibrary.KotlinToSwiftRules.arrayRuleTwo
import com.github.narcisdpr.kotlintoswift.convertLibrary.KotlinToSwiftRules.caseRule
import com.github.narcisdpr.kotlintoswift.convertLibrary.KotlinToSwiftRules.castRule
import com.github.narcisdpr.kotlintoswift.convertLibrary.KotlinToSwiftRules.catchRule
import com.github.narcisdpr.kotlintoswift.convertLibrary.KotlinToSwiftRules.checkClassEnding
import com.github.narcisdpr.kotlintoswift.convertLibrary.KotlinToSwiftRules.checkCompanionObject
import com.github.narcisdpr.kotlintoswift.convertLibrary.KotlinToSwiftRules.checkInterface
import com.github.narcisdpr.kotlintoswift.convertLibrary.KotlinToSwiftRules.constructorRule
import com.github.narcisdpr.kotlintoswift.convertLibrary.KotlinToSwiftRules.constructorRuleTwo
import com.github.narcisdpr.kotlintoswift.convertLibrary.KotlinToSwiftRules.customGetter
import com.github.narcisdpr.kotlintoswift.convertLibrary.KotlinToSwiftRules.dataClassRule
import com.github.narcisdpr.kotlintoswift.convertLibrary.KotlinToSwiftRules.detectConstructorLet
import com.github.narcisdpr.kotlintoswift.convertLibrary.KotlinToSwiftRules.detectConstructorVal
import com.github.narcisdpr.kotlintoswift.convertLibrary.KotlinToSwiftRules.detectMain
import com.github.narcisdpr.kotlintoswift.convertLibrary.KotlinToSwiftRules.elseRule
import com.github.narcisdpr.kotlintoswift.convertLibrary.KotlinToSwiftRules.elvisRule
import com.github.narcisdpr.kotlintoswift.convertLibrary.KotlinToSwiftRules.enumRule
import com.github.narcisdpr.kotlintoswift.convertLibrary.KotlinToSwiftRules.enumRuleTwo
import com.github.narcisdpr.kotlintoswift.convertLibrary.KotlinToSwiftRules.extensionFunctionRule
import com.github.narcisdpr.kotlintoswift.convertLibrary.KotlinToSwiftRules.floatRule
import com.github.narcisdpr.kotlintoswift.convertLibrary.KotlinToSwiftRules.getterRule
import com.github.narcisdpr.kotlintoswift.convertLibrary.KotlinToSwiftRules.ifRule
import com.github.narcisdpr.kotlintoswift.convertLibrary.KotlinToSwiftRules.ifRuleSwift
import com.github.narcisdpr.kotlintoswift.convertLibrary.KotlinToSwiftRules.inheritanceRule
import com.github.narcisdpr.kotlintoswift.convertLibrary.KotlinToSwiftRules.lambdaRule
import com.github.narcisdpr.kotlintoswift.convertLibrary.KotlinToSwiftRules.lambdaRuleTwo
import com.github.narcisdpr.kotlintoswift.convertLibrary.KotlinToSwiftRules.listRule
import com.github.narcisdpr.kotlintoswift.convertLibrary.KotlinToSwiftRules.mapRuleThree
import com.github.narcisdpr.kotlintoswift.convertLibrary.KotlinToSwiftRules.mapRuleTwo
import com.github.narcisdpr.kotlintoswift.convertLibrary.KotlinToSwiftRules.mapTranslationRule
import com.github.narcisdpr.kotlintoswift.convertLibrary.KotlinToSwiftRules.multiLineEndDetect
import com.github.narcisdpr.kotlintoswift.convertLibrary.KotlinToSwiftRules.multiLineStartDetect
import com.github.narcisdpr.kotlintoswift.convertLibrary.KotlinToSwiftRules.mutationRule
import com.github.narcisdpr.kotlintoswift.convertLibrary.KotlinToSwiftRules.notReadingComments
import com.github.narcisdpr.kotlintoswift.convertLibrary.KotlinToSwiftRules.nullThrowRule
import com.github.narcisdpr.kotlintoswift.convertLibrary.KotlinToSwiftRules.numRule
import com.github.narcisdpr.kotlintoswift.convertLibrary.KotlinToSwiftRules.propertiesRule
import com.github.narcisdpr.kotlintoswift.convertLibrary.KotlinToSwiftRules.rangeRule
import com.github.narcisdpr.kotlintoswift.convertLibrary.KotlinToSwiftRules.replacementRuleOne
import com.github.narcisdpr.kotlintoswift.convertLibrary.KotlinToSwiftRules.replacementRuleTwo
import com.github.narcisdpr.kotlintoswift.convertLibrary.KotlinToSwiftRules.setRule
import com.github.narcisdpr.kotlintoswift.convertLibrary.KotlinToSwiftRules.setRuleTwo
import com.github.narcisdpr.kotlintoswift.convertLibrary.KotlinToSwiftRules.smartCastRule
import com.github.narcisdpr.kotlintoswift.convertLibrary.KotlinToSwiftRules.staticRules
import com.github.narcisdpr.kotlintoswift.convertLibrary.KotlinToSwiftRules.stringBlockMatch
import com.github.narcisdpr.kotlintoswift.convertLibrary.KotlinToSwiftRules.stringTranslateInterpolationRule
import com.github.narcisdpr.kotlintoswift.convertLibrary.KotlinToSwiftRules.stringTranslateRule
import com.github.narcisdpr.kotlintoswift.convertLibrary.KotlinToSwiftRules.swiftPattern
import com.github.narcisdpr.kotlintoswift.convertLibrary.KotlinToSwiftRules.swiftRewriteRule
import com.github.narcisdpr.kotlintoswift.convertLibrary.KotlinToSwiftRules.throwRule
import com.github.narcisdpr.kotlintoswift.convertLibrary.KotlinToSwiftRules.tryRule
import com.github.narcisdpr.kotlintoswift.convertLibrary.KotlinToSwiftRules.unitArrayRule
import com.github.narcisdpr.kotlintoswift.convertLibrary.KotlinToSwiftRules.whenRule
import com.github.narcisdpr.kotlintoswift.convertLibrary.KotlinToSwiftRules.whenRuleTwo
import java.util.*

class KotlinToSwiftTokenizer(val replacements: List<Replacement>) {
    private val classesList = mutableListOf<Classes>()
    private val interfacesList = mutableListOf<Interfaces>()
    fun parse(source: List<String>) {
        parser(source)
    }

    fun parser(source: List<String>): List<String> {
        var hasMain = false

        val dest = ArrayList<String>(source.size)
        val derivationTree = ArrayList<DerivationTree>()

        var nextOutputLine: String? = null
        var nextInitBlockLine: String = ""

        var multiLineComment = false
        var insideTryCatch = false
        var nextLineForReplacement: String? = null
        var nextConstructor: String? = null
        var simulatedNextSourceLine: String? = null
        var nextLineAnnotation: String? = null
        var nullCoalescingCount = 0
        val builder = StringBuilder()

        fun parseConstructorParams(originalConstructor: String, dataClassName: String?) {
            val parameters = originalConstructor
                .substring(originalConstructor.indexOf('(') + 1, originalConstructor.indexOf(')'))
                .split(",").map { it.trim() }

            val parameterNames = ArrayList<String>()

            for (parameter in parameters) {
                if (parameter.matches(Regex(detectConstructorLet))
                    || parameter.matches(Regex(detectConstructorVal))
                ) {
                    if (nextOutputLine == null) {
                        nextOutputLine = ""
                        nextInitBlockLine = ""
                    }

                    var trimmedParameter =
                        if (parameter.contains('=')) {
                            parameter.substring(0, parameter.indexOf('=') - 1)
                        } else {
                            parameter
                        }

                    nextOutputLine += "  $trimmedParameter\n"
                    var parameterName = trimmedParameter.split(' ')[1].removeSuffix(":")
                    parameterNames += parameterName
                    nextInitBlockLine += "\n    self.$parameterName = $parameterName"

                    for (index in derivationTree.count() - 1 downTo 0) {
                        if (derivationTree[index] is DerivationTree.Class) {
                            classesList.last().properties.add(parameterName)
                            break
                        } else if (derivationTree[index] is DerivationTree.Interface) {
                            interfacesList.last().properties.add(parameterName)
                            break
                        }
                    }
                }
            }

            // Data classes
            if (dataClassName != null) {
                nextOutputLine += parameterNames.joinToString(
                    prefix = "\n  var description: String {\n    return \"$dataClassName(",
                    postfix = ")\"\n  }\n",
                    transform = { "$it=\\($it)" })
            }
        }

        var i = -1
        lineScope@ while (i < source.size - 1) {
            var line =
                if (simulatedNextSourceLine == null) {
                    i++
                    source[i].trimEnd()
                } else {
                    simulatedNextSourceLine
                }
            simulatedNextSourceLine = null
            val nextInputLine = if (i < source.size - 1) source[i + 1] else ""


            if (line.matches(Regex(swiftPattern))) {
                nextLineForReplacement = line.replace(Regex(swiftPattern), "$2")
                continue
            }

            if (line.matches(Regex(detectMain))) {
                hasMain = true
            }

            if (line.matches(Regex(multiLineStartDetect))) {
                multiLineComment = true
            }

            if (line.matches(Regex(multiLineEndDetect))) {
                multiLineComment = false
                dest.add(line)
                continue
            }

            if (multiLineComment || line.matches(Regex(notReadingComments))) {
                dest.add(line)
                continue
            }

            if (line.matches(Regex(checkClassEnding)) && !line.endsWith("{") && !line.endsWith("}")) {
                line += " {"
                simulatedNextSourceLine = "}"
            }

            var nextDerivationTreeElement =
                if (line.matches(R_FUN)) {
                    DerivationTree.Function()
                } else if (line.matches(R_CLASS)) {
                    val name = line.replace(R_CLASS, "$4")

                    if (classesList.find { it.name == name } == null) {
                        classesList.add(Classes(name, mutableListOf(), mutableListOf()))
                    }

                    DerivationTree.Class()
                } else if (line.matches(Regex(checkCompanionObject))) {
                    derivationTree.add(DerivationTree.CompanionObject())
                    continue
                } else if (line.matches(Regex(checkInterface))) {
                    val name = line.replace(Regex(checkInterface), "$2")

                    if (interfacesList.find { it.name == name } == null) {
                        interfacesList.add(Interfaces(name, mutableListOf(), mutableListOf()))
                    }

                    DerivationTree.Interface(name)
                } else if (line.matches(Regex(whenRule))) {
                    DerivationTree.SwitchCase()
                } else {
                    DerivationTree.Block()
                }

            var noStringLine = line
            while (noStringLine.matches(Regex(stringBlockMatch))) {
                noStringLine = noStringLine.replace(Regex(stringBlockMatch), "$1\"\"$3")
            }

            for (c in noStringLine) {
                if (c == '{') {
                    derivationTree.add(nextDerivationTreeElement)
                    nextDerivationTreeElement = DerivationTree.Block()
                } else if (c == '}') {
                    val lastElement = derivationTree.last()

                    if (lastElement is DerivationTree.Class && lastElement.constructorWritten == false && nextConstructor != "") {
                        val prefix = if (lastElement.parentClass != null) "override " else ""
                        dest.add("  $prefix$nextConstructor {$nextInitBlockLine\n  }")
                    }

                    if (lastElement is DerivationTree.CompanionObject) {
                        derivationTree.removeAt(derivationTree.lastIndex)
                        continue@lineScope
                    }

                    derivationTree.removeAt(derivationTree.lastIndex)
                    if (derivationTree.isNotEmpty() && derivationTree.last() is DerivationTree.AddLine) {
                        val addLine = (derivationTree.last() as DerivationTree.AddLine)
                        if (addLine.nextLine) {
                            if (addLine is DerivationTree.ComputedProperty && nextInputLine.matches(Regex(getterRule))) {
                                // Add closing bracket
                                derivationTree.add(DerivationTree.ComputedProperty())
                            } else {
                                nextOutputLine = addLine.lineToInsert
                            }
                        } else {
                            dest.add(addLine.lineToInsert)
                        }
                        derivationTree.removeAt(derivationTree.lastIndex)
                    }
                }
            }



            if (nextLineForReplacement != null) {
                dest.add(line.replace(Regex(swiftRewriteRule), "$1") + nextLineForReplacement)
                nextLineForReplacement = null
                continue
            }
            replacements.forEach {
                val regex = Regex("$replacementRuleOne${it.from}$replacementRuleTwo")
                if (it.multiple) {
                    while (line.matches(regex)) {
                        // Replace as much as possible
                        line = line.replace(regex, "$1${it.to}$2")
                    }
                } else {
                    if (line.matches(regex)) {
                        // Replace only once
                        line = line.replace(regex, "$1${it.to}$2")
                    }
                }
            }


            while (line.matches(Regex(arrayRule))) {
                line = line.replace(Regex(arrayRule), "$1[$2]$3")
            }


            while (line.matches(Regex(arrayListRule))) {
                line = line.replace(Regex(arrayListRuleReplacement), "$1[$2]")
            }

            while (line.matches(Regex(unitArrayRule))) {
                line = line.replace(Regex(unitArrayRule), "$1[$3]$4")
            }
            while (line.matches(Regex(listRule))) {
                line = line.replace(Regex(listRule), "$1Array<$2>$3")
            }

            line = line.replace(LINKED_HASH_MAP, HASH_MAP_C).replace(ARRAY_MAP, HASH_MAP_C)

            while (line.matches(Regex(mapTranslationRule))) {
                line = line.replace(Regex(mapTranslationRule), "$1[$3:$4]()$5")
            }

            while (line.matches(Regex(mapRuleTwo))) {
                line = line.replace(Regex(mapRuleTwo), "$1[$2:$3]$4")
            }


            while (line.matches(Regex(mapRuleThree))) {
                val pairList = line.replace(Regex(mapRuleThree), "$3")
                    .split("Pair(").filter { it.length > 2 }
                    .map { it.replace(Regex("(.*?)(\\),)?\\)?"), "$1") }
                    .joinToString(separator = ", ", transform = { it.trim().replace(", ", ": ") })
                line = line.replace(Regex(mapRuleThree), "$1[$pairList]$4")
            }

            line = line.replace(LINKED_HASH_SET, HASH_SET_C).replace(ARRAY_SET, HASH_SET_C)

            while (line.matches(Regex(setRule))) {
                line = line.replace(Regex(setRule), "$1Set<$3>()$4")
            }

            while (line.matches(Regex(setRuleTwo))) {
                line = line
                    .replace(Regex(setRuleTwo), "$1Set(arrayLiteral: $3)$4")
            }

            if (line.matches(Regex(rangeRule))) {
                line = line.replace(Regex(rangeRule), "$1...$2")
            }

            if (line.matches(Regex(ifRule))) {
                line = line.replace(Regex(ifRule), "$1if case $3...$4 = $2$5")
            }

            line = line.replace(Regex(whenRuleTwo), "$1switch $2")

            if (derivationTree.isNotEmpty() && derivationTree.last() is DerivationTree.SwitchCase) {
                // Translate "else ->" -> "default:"
                line = line.replace(Regex(elseRule), "$1default: $2")
                // Translate "x ->" -> "case x:"
                line = line.replace(Regex(caseRule), "$1case $2: $3")
            }

            if (line.matches(R_FUN)) {
                val functionName = line.replace(R_FUN, "$8")

                for (index in derivationTree.count() - 1 downTo 0) {
                    if (derivationTree[index] is DerivationTree.Class) {
                        classesList.last().functions.add(functionName)
                        break
                    } else if (derivationTree[index] is DerivationTree.Interface) {
                        interfacesList.last().functions.add(functionName)
                        break
                    }
                }

                val annotation = nextLineAnnotation ?: line.replace(R_FUN, "$2")
                val throws = if (annotation.startsWith("@Throws")) " throws" else ""

                line = line.replace(R_FUN, "$1$4func $8$7$9")
                line = line.replace(OPEN, "")
                line = line.replace("):", ") ->")
                line = line.replace(") :", ") ->") /// JAYE KAAR

                if (throws.isNotEmpty()) {
                    val functionNameEnd = line.lastIndexOf(')')
                    line = line.substring(0, functionNameEnd + 1) + throws + line.substring(functionNameEnd + 1)
                }

                for (index in derivationTree.count() - 1 downTo 0) {
                    val structureTreeNode = derivationTree[index]
                    if (structureTreeNode is DerivationTree.Class) {
                        for (parentInterface in structureTreeNode.parentInterfaces) {
                            if (interfacesList.find { it.name == parentInterface }?.functions?.find { it == functionName } != null) {
                                line = line.replace(OVERRIDE, "")
                            }
                        }
                        break
                    }
                }

                if (line.contains(ABSTRACT)) {
                    line = line.replace(ABSTRACT, "") + " {\n    fatalError(\"Method is abstract\")\n  }"
                }
            }

            nextLineAnnotation = null
            if (line.matches(Regex(annotationsRule))) {
                nextLineAnnotation = line.replace(Regex(annotationsRule), "$2")
                continue
            }

            if (line.matches(Regex(propertiesRule))) {
                for (index in derivationTree.count() - 1 downTo 0) {
                    if (derivationTree[index] is DerivationTree.Class) {
                        classesList.last().properties.add(line.replace(Regex(propertiesRule), "$2"))
                        break
                    } else if (derivationTree[index] is DerivationTree.Interface) {
                        interfacesList.last().properties.add(line.replace(Regex(propertiesRule), "$2"))
                        break
                    }
                }

                if (nextInputLine.matches(Regex(customGetter))) {
                    line = line.replace("let ", "var ")
                    line += " {"
                    derivationTree.add(DerivationTree.ComputedProperty())
                }
            }

            while (line.matches(Regex(elvisRule))) {
                line = line.replace(Regex(elvisRule), "$1$2$3 ? $4 : $5")
            }
            line = line.replace(Regex(ifRuleSwift), "$1$2 $3$4")

            while (line.matches(Regex(stringTranslateRule))) {
                line = line.replace(Regex(stringTranslateRule), "$1\\\\($2)$3")
            }

            while (line.matches(Regex(stringTranslateInterpolationRule))) {
                line = line.replace(Regex(stringTranslateInterpolationRule), "$1\\\\($2)$3")
            }

            line = line.replace(".toString()", "")

            line = line.replace("Math.", "")

            while (line.matches(Regex(floatRule))) {
                line = line.replace(Regex(floatRule), "$1$2$3$4")
            }

            while (line.matches(Regex(numRule))) {
                line = line.replace(Regex(numRule), "$1$2$3")
            }

            if (line.startsWith("package ") || line.startsWith("import ")) {
                continue
            }

            if (line.matches(Regex(catchRule))) {
                line = line.replace(Regex(catchRule), "$1catch {")
                insideTryCatch = false
            }

            if (insideTryCatch && line.trim().length > 0) {
                if (line.contains("return ")) {
                    // return x() --> return try x()
                    line = line.replace("return ", "return try ")
                } else if (line.contains(" = ")) {
                    // var x = a() --> var x = let a()
                    line = line.replace(" = ", " = try ")
                } else {

                    line = line.replace(Regex(tryRule), "$1") + "try " +
                            line.replace(Regex(tryRule), "$2")
                }
            }

            if (line.endsWith("try {")) {
                insideTryCatch = true
                line = line.replace("try", "do")
            }


            if (line.matches(Regex(enumRule))) {
                line = line.replace((Regex(enumRuleTwo)), "$1enum $2 { case$3")
            }

            if (line.matches(R_CLASS)) {   // Classes
                val modifier = line.replace(R_CLASS, "$2")
                if (!modifier.contains("private ") && !modifier.contains("protected ")
                    && !modifier.contains("internal ") && !modifier.contains("public ")
                ) {
                    line = "public " + line
                }

                nextConstructor = line.replace(R_CLASS, "init$6")

                val dataClassName =
                    if (line.matches(Regex(dataClassRule))) {
                        line = line.substring(0, line.length - 2) + ": CustomStringConvertible {"
                        line.replace(R_CLASS, "$4")
                    } else {
                        null
                    }
                if (!nextConstructor.contains("(")) {
                    nextConstructor = "init()"
                } else {
                    parseConstructorParams(nextConstructor, dataClassName)
                    nextConstructor = nextConstructor.replace("let ", "").replace("var ", "")
                }
                var derivedClasses = line.replace(R_CLASS, "$8")
                    .replace(Regex(": ".concat(inheritanceRule)), ": $1$4$5$7") // Remove arguments
                line = line.replace(R_CLASS, "$1$2class $4$5${derivedClasses.replace("()", "")} {")
                    .replace("open ", "").replace("abstract ", "").replace("data ", "")

                if (derivedClasses.startsWith(":")) {
                    for (derivedClass in derivedClasses.substring(1).split(", ")) {

                        if (derivedClass.trim().endsWith("()")) {
                            (derivationTree.last { it is DerivationTree.Class } as DerivationTree.Class).parentClass =
                                derivedClass.replace("()", "").trim()
                        } else {
                            (derivationTree.last { it is DerivationTree.Class } as DerivationTree.Class).parentInterfaces.add(
                                derivedClass.replace("()", "").trim()
                            )
                        }
                    }
                }
            }

            if (line.matches(Regex(constructorRule)) || line.matches(Regex(constructorRuleTwo))) {
                val prefix =
                    if ((derivationTree.last { it is DerivationTree.Class } as DerivationTree.Class).parentClass != null) "override " else ""

                line = line.replace(Regex(constructorRule), "$1$prefix$nextConstructor {$nextInitBlockLine")
                nextConstructor = ""

                (derivationTree.last { it is DerivationTree.Class } as DerivationTree.Class).constructorWritten = true
            }

            while (line.matches(Regex(argumentRule))) {
                line = line.replace(Regex(argumentRule), "$1$2($3$4: $5)$6")
            }

            if (line.matches(Regex(mutationRule))) {
                line = line.replace((Regex("(\\s*)let (.* = \\[.*\\]\\(?\\)?)")), "$1var $2")
            }

            if (line.matches(Regex(extensionFunctionRule))) {

                var listType = line.replace(Regex(extensionFunctionRule), "$2")
                if (listType == "<Int>" || listType == "<Double>") {
                    listType = "Addable"
                }
                listType = listType.replace("<", "").replace(">", "")

                line = line.replace(Regex(extensionFunctionRule), "extension Array where Element : $listType {\n") +
                        line.replace(Regex(arrayRuleTwo), "$1func $4")

                // Close extension
                derivationTree.add(derivationTree.size - 1, DerivationTree.AddLine("}"))

            } else if (line.matches(Regex(arrayRuleTwo))) {
                line = line.replace(Regex(arrayRuleTwo), "extension $1$2$3 {\n") +
                        line.replace(Regex(arrayRuleTwo), "$1func $4")

                derivationTree.add(derivationTree.size - 1, DerivationTree.AddLine("}"))
            }

            if (line.matches(Regex(staticRules)) && !derivationTree.none { it is DerivationTree.CompanionObject }) {
                line = line.replace(Regex(staticRules), "$1static $2 $3")
            }


            while (line.matches(Regex(lambdaRule))) {
                line = line.replace(Regex(lambdaRule), "$1 in$2")
            }

            while (line.matches(Regex(lambdaRuleTwo))) {
                line = line.replace(Regex(lambdaRuleTwo), "$1\\$0$2")
            }


            line = line.replace("!!", "!")

            line = line.replace(" ?: ", " ?? ")

            while (line.matches(Regex(nullThrowRule))) {
                line = line.replace(Regex(nullThrowRule), "$1 = $2 ?! { $3 }")
            }

            while (line.matches(Regex(throwRule))) {
                line = line.replace(Regex(throwRule), "$1 = try $2 ?! { $3 }")
            }



            while (line.matches(Regex(castRule))) {
                nullCoalescingCount++
                line = line.replace(
                    Regex(castRule),
                    "$1let _kotliftOptional$nullCoalescingCount = $4; if _kotliftOptional$nullCoalescingCount == nil { $5 }; $2$3 = _kotliftOptional$nullCoalescingCount!$6"
                )
            }

            if (line.matches(Regex(smartCastRule))) {
                line = line.replace(Regex(smartCastRule), "$1if let $2 = $2 {")
            }

            dest.add(line)

            if (nextOutputLine != null) {
                dest.add(nextOutputLine!!)
                nextOutputLine = null
            }
        }

        if (hasMain) {
            dest.add("")
            dest.add("main([])")
        }

        if (derivationTree.count() != 0) {
            throw IllegalStateException("Structure tree should be empty, but is $derivationTree")
        }

        return dest
    }

}


