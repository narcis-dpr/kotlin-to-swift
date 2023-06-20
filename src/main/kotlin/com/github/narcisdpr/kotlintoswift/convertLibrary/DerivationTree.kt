package com.github.narcisdpr.kotlintoswift.convertLibrary

sealed class DerivationTree {
    class Class(
        var constructorWritten: Boolean = false,
        var parentClass: String? = null,
        var parentInterfaces: MutableList<String> = mutableListOf()
    ) : DerivationTree()

    class Function : DerivationTree()
    class Block : DerivationTree()
    class SwitchCase : DerivationTree()
    class Enum : DerivationTree()
    open class AddLine(val lineToInsert: String, val nextLine: Boolean = false) : DerivationTree()
    class ComputedProperty : AddLine("}", nextLine = true)
    class CompanionObject : DerivationTree()
    class Interface(name: String) : DerivationTree()
}