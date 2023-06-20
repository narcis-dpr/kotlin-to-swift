package com.github.narcisdpr.kotlintoswift.convertLibrary

import com.github.narcisdpr.kotlintoswift.convertLibrary.DefaultExpressions.CARET
import com.github.narcisdpr.kotlintoswift.convertLibrary.DefaultExpressions.GENERICS
import com.github.narcisdpr.kotlintoswift.convertLibrary.DefaultExpressions.ONCE_OR_NOT
import com.github.narcisdpr.kotlintoswift.convertLibrary.DefaultExpressions.OR
import com.github.narcisdpr.kotlintoswift.convertLibrary.DefaultExpressions.PARENTHESES
import com.github.narcisdpr.kotlintoswift.convertLibrary.DefaultExpressions.POSITIVE
import com.github.narcisdpr.kotlintoswift.convertLibrary.DefaultExpressions.SPACE
import com.github.narcisdpr.kotlintoswift.convertLibrary.DefaultExpressions.STAR

inline fun String.concat(str: String): String = this.plus(str)

inline fun String.starClosure(): String = this.plus(STAR)
inline fun String.positiveClosure(): String = this.plus(POSITIVE)

inline fun String.addSpace(): String = this.plus(SPACE)

inline fun String.captureGroup(): String = this putBetween PARENTHESES


inline fun String.makeGenerics(): String = this putBetween GENERICS

inline fun String.onceOrNotAtAll(): String = this.concat(ONCE_OR_NOT)

inline fun String.everythingExcept(): String = (CARET.concat(this)).putBetween("[]")
inline fun String.space(): String = this.concat(" ")

infix fun String.putBetween(brackets: String): String {
    if (brackets.length % 2 != 0)
        throw IllegalArgumentException()

    return brackets.substring(0 until brackets.length / 2).concat(this) +
            brackets.subSequence(brackets.length / 2 until brackets.length)
}

infix fun String.or(str: String): String {
    return this + OR + str
}

//infix fun String.or(symbol: String): String {
//  return this.concat(symbol)
//}