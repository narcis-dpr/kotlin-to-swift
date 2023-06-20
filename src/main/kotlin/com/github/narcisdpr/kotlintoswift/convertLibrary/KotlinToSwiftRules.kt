package com.github.narcisdpr.kotlintoswift.convertLibrary

import com.github.narcisdpr.kotlintoswift.convertLibrary.DefaultExpressions.COMPANION_OBJECT
import com.github.narcisdpr.kotlintoswift.convertLibrary.DefaultExpressions.DOT
import com.github.narcisdpr.kotlintoswift.convertLibrary.DefaultExpressions.NORMAL_WORD
import com.github.narcisdpr.kotlintoswift.convertLibrary.DefaultExpressions.NORMAL_WORD_
import com.github.narcisdpr.kotlintoswift.convertLibrary.DefaultExpressions.NORMAL_WORD_DOT_GENS_
import com.github.narcisdpr.kotlintoswift.convertLibrary.DefaultExpressions.NOT_NORMAL_WORD_
import com.github.narcisdpr.kotlintoswift.convertLibrary.DefaultExpressions.OF
import com.github.narcisdpr.kotlintoswift.convertLibrary.DefaultExpressions.OR
import com.github.narcisdpr.kotlintoswift.convertLibrary.DefaultExpressions.SPACE
import com.github.narcisdpr.kotlintoswift.convertLibrary.DefaultIdentifiers.ABSTRACT
import com.github.narcisdpr.kotlintoswift.convertLibrary.DefaultIdentifiers.ARRAY
import com.github.narcisdpr.kotlintoswift.convertLibrary.DefaultIdentifiers.ARRAY_LIST
import com.github.narcisdpr.kotlintoswift.convertLibrary.DefaultIdentifiers.ARRAY_LIST_OF
import com.github.narcisdpr.kotlintoswift.convertLibrary.DefaultIdentifiers.ARROW
import com.github.narcisdpr.kotlintoswift.convertLibrary.DefaultIdentifiers.CATCH
import com.github.narcisdpr.kotlintoswift.convertLibrary.DefaultIdentifiers.CLASS
import com.github.narcisdpr.kotlintoswift.convertLibrary.DefaultIdentifiers.CMAP
import com.github.narcisdpr.kotlintoswift.convertLibrary.DefaultIdentifiers.CONTINUE
import com.github.narcisdpr.kotlintoswift.convertLibrary.DefaultIdentifiers.DATA
import com.github.narcisdpr.kotlintoswift.convertLibrary.DefaultIdentifiers.ELSE
import com.github.narcisdpr.kotlintoswift.convertLibrary.DefaultIdentifiers.EMPTY_MAP
import com.github.narcisdpr.kotlintoswift.convertLibrary.DefaultIdentifiers.EMPTY_SET
import com.github.narcisdpr.kotlintoswift.convertLibrary.DefaultIdentifiers.ENUM
import com.github.narcisdpr.kotlintoswift.convertLibrary.DefaultIdentifiers.FOR
import com.github.narcisdpr.kotlintoswift.convertLibrary.DefaultIdentifiers.FUN
import com.github.narcisdpr.kotlintoswift.convertLibrary.DefaultIdentifiers.FUNC
import com.github.narcisdpr.kotlintoswift.convertLibrary.DefaultIdentifiers.GET
import com.github.narcisdpr.kotlintoswift.convertLibrary.DefaultIdentifiers.HASH_MAP
import com.github.narcisdpr.kotlintoswift.convertLibrary.DefaultIdentifiers.HASH_MAP_C
import com.github.narcisdpr.kotlintoswift.convertLibrary.DefaultIdentifiers.HASH_SET
import com.github.narcisdpr.kotlintoswift.convertLibrary.DefaultIdentifiers.HASH_SET_C
import com.github.narcisdpr.kotlintoswift.convertLibrary.DefaultIdentifiers.IF
import com.github.narcisdpr.kotlintoswift.convertLibrary.DefaultIdentifiers.IN
import com.github.narcisdpr.kotlintoswift.convertLibrary.DefaultIdentifiers.INIT
import com.github.narcisdpr.kotlintoswift.convertLibrary.DefaultIdentifiers.INTERNAL
import com.github.narcisdpr.kotlintoswift.convertLibrary.DefaultIdentifiers.IT
import com.github.narcisdpr.kotlintoswift.convertLibrary.DefaultIdentifiers.LET
import com.github.narcisdpr.kotlintoswift.convertLibrary.DefaultIdentifiers.LINKED_HASH_MAP
import com.github.narcisdpr.kotlintoswift.convertLibrary.DefaultIdentifiers.LINKED_HASH_SET
import com.github.narcisdpr.kotlintoswift.convertLibrary.DefaultIdentifiers.LINKED_LIST
import com.github.narcisdpr.kotlintoswift.convertLibrary.DefaultIdentifiers.LINKED_MAP
import com.github.narcisdpr.kotlintoswift.convertLibrary.DefaultIdentifiers.LINKED_SET
import com.github.narcisdpr.kotlintoswift.convertLibrary.DefaultIdentifiers.LIST
import com.github.narcisdpr.kotlintoswift.convertLibrary.DefaultIdentifiers.MAP
import com.github.narcisdpr.kotlintoswift.convertLibrary.DefaultIdentifiers.MUTABLE_MAP
import com.github.narcisdpr.kotlintoswift.convertLibrary.DefaultIdentifiers.MUTABLE_SET
import com.github.narcisdpr.kotlintoswift.convertLibrary.DefaultIdentifiers.OPEN
import com.github.narcisdpr.kotlintoswift.convertLibrary.DefaultIdentifiers.OVERRIDE
import com.github.narcisdpr.kotlintoswift.convertLibrary.DefaultIdentifiers.PRIVATE
import com.github.narcisdpr.kotlintoswift.convertLibrary.DefaultIdentifiers.PROTECTED
import com.github.narcisdpr.kotlintoswift.convertLibrary.DefaultIdentifiers.PUBLIC
import com.github.narcisdpr.kotlintoswift.convertLibrary.DefaultIdentifiers.RETURN
import com.github.narcisdpr.kotlintoswift.convertLibrary.DefaultIdentifiers.SET
import com.github.narcisdpr.kotlintoswift.convertLibrary.DefaultIdentifiers.SWITCH
import com.github.narcisdpr.kotlintoswift.convertLibrary.DefaultIdentifiers.THROW
import com.github.narcisdpr.kotlintoswift.convertLibrary.DefaultIdentifiers.TRY
import com.github.narcisdpr.kotlintoswift.convertLibrary.DefaultIdentifiers.VAR
import com.github.narcisdpr.kotlintoswift.convertLibrary.DefaultIdentifiers.WHEN
import com.github.narcisdpr.kotlintoswift.convertLibrary.DefaultIdentifiers.WHILE

object KotlinToSwiftRules {
  // Constructor parameters
  val detectConstructorVal = (DOT.starClosure().concat(" ") or "").captureGroup().concat(
    DefaultIdentifiers.VAR
  ).concat(DOT.starClosure())
  val detectConstructorLet = (DOT.starClosure().concat(" ") or "").captureGroup().concat(
    DefaultIdentifiers.LET
  ).concat(DOT.starClosure())

  // Detect SWIFT rewrite
  val swiftPattern =
    DOT.starClosure().captureGroup().concat(DefaultIdentifiers.SWIFT).concat(": ").concat(
      DOT.starClosure().captureGroup()
    )

  // Detect main function
  val detectMain = SPACE.starClosure().concat(DefaultIdentifiers.FUN).concat(" ").concat(
    DefaultIdentifiers.MAIN
  ).concat("\\(").concat(NORMAL_WORD_.positiveClosure())
    .concat(": ").concat(DefaultIdentifiers.MAIN_ARGS).concat("\\)").concat(" ").concat("\\{")

  // Comments lines are only detected when no additional code is in the line
  // Start multi line comments
  val multiLineStartDetect = SPACE.starClosure().concat("/\\*").concat(DOT.starClosure())


  // End multi line comments
  val multiLineEndDetect = DOT.starClosure().concat("\\*/").concat(SPACE.starClosure())

  // Don't inspect comment lines any further
  val notReadingComments = SPACE.starClosure().concat("//")

  // Make sure all classes end with {}  (.* |)class .*
  val checkClassEnding = (DOT.starClosure().concat(" ") or "").captureGroup().concat(
    DefaultIdentifiers.CLASS
  ).concat(DOT.starClosure())

  val checkCompanionObject = SPACE.starClosure().captureGroup().concat(COMPANION_OBJECT).concat("\\{")
  val checkInterface = SPACE.starClosure().captureGroup().concat(DefaultIdentifiers.INTERFACE)
    .concat(NORMAL_WORD_.positiveClosure().captureGroup()).concat(" \\{")
  val whenRule = SPACE.starClosure().captureGroup().concat(DefaultIdentifiers.WHEN).concat(
    DOT.starClosure().captureGroup()
  )

  val stringBlockMatch = (DOT.starClosure().concat("\\\\".everythingExcept()).captureGroup()).concat("\"(")
    .concat(DOT.positiveClosure().onceOrNotAtAll().concat("\\\\".everythingExcept())).concat(")\"")
    .concat(DOT.starClosure().captureGroup())

  val getterRule = SPACE.starClosure().concat((GET or SET).captureGroup()).concat("\\(").concat(DOT.starClosure())

  // Replace with SWIFT rewrite (keep indent and comment)
  val swiftRewriteRule = SPACE.starClosure().captureGroup().concat(DOT.starClosure().captureGroup())

  val replacementRuleOne = (DOT.starClosure().concat(NOT_NORMAL_WORD_) or "").captureGroup()
  val replacementRuleTwo = (NOT_NORMAL_WORD_.concat(DOT.starClosure() or "\\Z")).captureGroup()

  // Translate arrays
  val arrayRule = DOT.starClosure().captureGroup().concat(ARRAY)
    .concat(NORMAL_WORD_.positiveClosure().captureGroup().makeGenerics()).concat(DOT.starClosure().captureGroup())

  // Translate arrayListOf -> []
  val arrayListRule = DOT.starClosure().captureGroup().concat(ARRAY_LIST_OF.onceOrNotAtAll())
    .concat("\\(").concat(DOT.starClosure().captureGroup()).concat("\\)")
  val arrayListRuleReplacement = DOT.starClosure().captureGroup().concat(ARRAY_LIST_OF)
    .concat("\\(").concat(DOT.starClosure().captureGroup()).concat("\\)")

  // Translate List, ArrayList and LinkedList --> Array
  val unitArrayRule =
    DOT.starClosure().captureGroup().concat((ARRAY_LIST or LINKED_LIST or ARRAY_LIST_OF).captureGroup())
      .concat(DOT.starClosure().captureGroup().makeGenerics()).concat(DOT.starClosure().captureGroup())

  val listRule = DOT.starClosure().captureGroup().concat(LIST).concat(DOT.starClosure().captureGroup().makeGenerics())
    .concat(DOT.starClosure().captureGroup())

  val mapTranslationRule = DOT.starClosure().captureGroup().concat(
    (EMPTY_MAP or HASH_MAP_C or LINKED_HASH_MAP)
      .captureGroup()
  )
    .concat(
      (DOT.positiveClosure().onceOrNotAtAll().captureGroup() + "," + DOT.positiveClosure().onceOrNotAtAll()
        .captureGroup()).makeGenerics()
    )
    .concat("\\(\\)").concat(DOT.starClosure().captureGroup())

  val mapRuleTwo = DOT.starClosure().captureGroup().concat(CMAP)
    .concat(
      (DOT.positiveClosure().onceOrNotAtAll().captureGroup() + "," +
          DOT.positiveClosure().onceOrNotAtAll().captureGroup()).makeGenerics()
    )
    .concat(DOT.starClosure().captureGroup())

  val mapRuleThree = DOT.starClosure().captureGroup().concat(
    (HASH_MAP or MUTABLE_MAP or LINKED_MAP or MAP).captureGroup()
  ).concat(OF).concat("\\(")
    .concat(DOT.starClosure().captureGroup()).concat("\\)").concat(DOT.starClosure().captureGroup())

  val setRule = DOT.starClosure().captureGroup().concat((EMPTY_SET or HASH_SET_C or LINKED_HASH_SET).captureGroup())
    .concat(DOT.starClosure().captureGroup().makeGenerics()).concat("\\(\\)").concat(DOT.starClosure().captureGroup())

  val setRuleTwo =
    DOT.starClosure().captureGroup().concat((HASH_SET or MUTABLE_SET or LINKED_SET or SET).captureGroup())
      .concat(OF).concat("\\(").concat(DOT.starClosure().captureGroup()).concat("\\)").concat(
        DOT.starClosure()
          .captureGroup()
      )

  // Translate range .. --> ...
  val rangeRule = DOT.starClosure().captureGroup().concat("\\.\\.").concat(DOT.starClosure().captureGroup())

  // Translate if in range if x in 1..7 --> if case 1...7 = x
  val ifRule =
    DOT.starClosure().captureGroup().concat(IF).concat(" ").concat("\\(").concat(DOT.starClosure().captureGroup())
      .concat(IN).concat(DOT.starClosure().captureGroup()).concat("\\.\\.\\.").concat(DOT.starClosure().captureGroup())
      .concat("\\)").concat(DOT.starClosure().captureGroup())

  // Switch
  // Translate when -> switch
  val whenRuleTwo = SPACE.starClosure().captureGroup().concat(WHEN).concat(DOT.starClosure().captureGroup())
  val elseRule = SPACE.starClosure().captureGroup().concat(ELSE).concat(ARROW).concat(DOT.starClosure().captureGroup())
  val caseRule = SPACE.starClosure().captureGroup().concat(DOT.starClosure().captureGroup())
    .concat(ARROW).concat(DOT.starClosure().captureGroup())

  val annotationsRule = SPACE.starClosure().captureGroup().concat(
    ("\\@".concat(DefaultExpressions.NORMAL_WORD.positiveClosure()).concat(
      ("\\(".concat(DOT.positiveClosure().onceOrNotAtAll())
        .concat("\\)")).captureGroup()
    ).starClosure()).captureGroup().positiveClosure()
  )


  // Properties
  val propertiesRule = SPACE.starClosure().concat((VAR or LET).captureGroup()).concat(" ").concat(
    NORMAL_WORD_.positiveClosure().captureGroup().concat(DOT.starClosure())
  )


  // Custom getter+setter / computed properties
  val customGetter = SPACE.starClosure().concat((GET or SET).captureGroup()).concat("\\(").concat(DOT.starClosure())

  // Translate elvis operator if (a) b else c --> a ? b : c
  val elvisRule = SPACE.starClosure().captureGroup()
    .concat(DOT.starClosure().captureGroup())
    .concat(IF).concat(" ").concat("\\(")
    .concat(DOT.starClosure().captureGroup())
    .concat("\\)")
    .concat(" ")
    .concat(DOT.starClosure().captureGroup())
    .concat(" ")
    .concat(ELSE).concat(" ").concat(DOT.starClosure().captureGroup())

  // Translate if (no round brackets in swift)
  val ifRuleSwift = SPACE.starClosure().captureGroup().concat((IF or FOR or SWITCH or WHILE).captureGroup().space())
    .concat("\\(").concat(DOT.starClosure().captureGroup()).concat("\\)").concat(DOT.starClosure().captureGroup())

  // Translate string interpolation: ${value}
  val stringTranslateRule = DOT.starClosure().captureGroup().concat("\\\$\\{").concat(DOT.starClosure().captureGroup())
    .concat("\\}").concat(DOT.starClosure().captureGroup())

  // Translate string interpolation: $value
  val stringTranslateInterpolationRule = DOT.starClosure().captureGroup().concat("\\\$")
    .concat((DefaultExpressions.LITERAL_WORD_.concat(NORMAL_WORD_.starClosure())).captureGroup())
    .concat((NOT_NORMAL_WORD_.concat(DOT.starClosure()).captureGroup()))

  // Float --> Double
  val floatRule = (DOT.starClosure().concat(NOT_NORMAL_WORD_)).captureGroup().concat(
    DefaultExpressions.DIGITS.positiveClosure().captureGroup()
  ).concat(("\\.".concat(DefaultExpressions.DIGITS.starClosure())).captureGroup().onceOrNotAtAll())
    .concat("f").concat((NOT_NORMAL_WORD_.concat(DOT.starClosure()) or "\\Z").captureGroup())

  // 123L --> 123
  val numRule = (DOT.starClosure().concat(NOT_NORMAL_WORD_)).captureGroup().concat(
    DefaultExpressions.DIGITS.positiveClosure().captureGroup()
  ).concat("L").concat(
    (NOT_NORMAL_WORD_.concat(DOT.starClosure()) or "\\Z").captureGroup()
  )


  // Translate catch. Exception is stripped out, add type when needed
  val catchRule = SPACE.concat("\\}").putBetween("[]").starClosure().captureGroup()
    .concat(CATCH.space()).concat(DOT.starClosure().captureGroup().space()).concat("\\{")

  // x() --> try x()
  val tryRule = SPACE.starClosure().captureGroup().concat(DOT.starClosure().captureGroup())

  // Enums
  val enumRule = SPACE.starClosure().captureGroup().concat(ENUM).concat(CLASS).concat(DOT.starClosure().captureGroup())
  val enumRuleTwo = enumRule.concat("".space().concat("\\{")).concat(DOT.starClosure().captureGroup())


  // Data classes
  val dataClassRule = (DOT.starClosure().space() or "").captureGroup().concat(DATA).concat(DOT.starClosure())

  // Inheritance
  val inheritanceRule = NORMAL_WORD_.positiveClosure().captureGroup().concat(
    ((("\\(".captureGroup()).concat(DOT.starClosure()).concat(("\\)").captureGroup())).captureGroup()
        or "".captureGroup()).captureGroup()
  ).concat(DOT.starClosure().captureGroup())

  // Constructor
  val constructorRule = SPACE.starClosure().captureGroup().concat((OVERRIDE or "").captureGroup()).concat(
    INIT.concat("\\{")
  )
  val constructorRuleTwo = constructorRule.concat(DOT.starClosure().concat("}"))

  // Named arguments: name = x --> name: x
  val argumentRule = (DOT.starClosure().concat(NORMAL_WORD_.starClosure())).captureGroup()
    .concat((DOT.starClosure().makeGenerics() or "").captureGroup()).concat("\\(")
    .concat(DOT.starClosure().onceOrNotAtAll().captureGroup()).concat(NORMAL_WORD_.positiveClosure().captureGroup())
    .concat("".space().concat("=").space()).concat(DOT.starClosure().captureGroup()).concat("\\)")
    .concat(DOT.starClosure().captureGroup())

  // Arrays/Dictionaries should be always mutable, as mutation of elements changes array (in contrast to Kotlin)
  val mutationRule = SPACE.starClosure().captureGroup().concat(LET.space()).concat(
    (DOT.starClosure().space()
      .concat("=").space().concat("\\[").concat(DOT.starClosure()).concat("\\]")
      .concat("\\(?\\)?")).captureGroup()
  )

  // Extension functions
  val extensionFunctionRule = SPACE.starClosure().captureGroup().concat(FUNC).concat(" ").concat(ARRAY)
    .concat((DOT.starClosure().makeGenerics() or "").captureGroup()).concat("\\.")
    .concat(DOT.starClosure().captureGroup())

  val arrayRuleTwo =
    SPACE.starClosure().captureGroup().concat(FUNC).concat(" ").concat(NORMAL_WORD_.positiveClosure().captureGroup())
      .concat((DOT.starClosure().makeGenerics() or "").captureGroup()).concat("\\.")
      .concat(DOT.starClosure().captureGroup())

  // Companion object / static
  val staticRules = SPACE.starClosure().captureGroup().concat((FUNC or VAR or LET).captureGroup()).space().concat(
    DOT.starClosure().captureGroup()
  )

  // Lambdas: { x -> x } --> { x in x }   or  { x ->\n --> { x in\n
  val lambdaRule = DOT.starClosure().concat("\\{".concat(DOT.starClosure())).captureGroup().concat(" ->")
    .concat(
      ("".space().concat(((DOT.starClosure().concat("\\}").concat(DOT.starClosure())).captureGroup() or ""))
        .captureGroup())
    )

  // Lambdas: { it } --> { $0 }
  val lambdaRuleTwo = (DOT.starClosure().concat("\\{").concat(DOT.starClosure())).captureGroup()
    .concat(IT).concat((DOT.starClosure().concat("\\}").concat(DOT.starClosure())).captureGroup())

  // Null coalescing: ?: --> ?! (when right hand side throws)
  val nullThrowRule = DOT.starClosure().captureGroup().space().concat("=").space()
    .concat((TRY.space().concat(DOT.starClosure())).captureGroup()).space().concat("\\?\\?").space()
    .concat((THROW.space().concat(DOT.starClosure())).captureGroup())

  val throwRule = DOT.starClosure().captureGroup().space().concat("=").space().concat(
    DOT.starClosure().captureGroup().space()
  ).concat("\\?\\?").space()
    .concat((THROW.space().concat(DOT.starClosure())).captureGroup())

  // Null coalescing: ?: --> let;if;let (when right hand side is "continue|return"). Auto smart cast
  val castRule = SPACE.starClosure().captureGroup().concat((VAR.space() or LET.space() or "").captureGroup())
    .concat(DefaultExpressions.NORMAL_WORD.starClosure().captureGroup()).space().concat("=").space().concat(
      DOT.starClosure().captureGroup()
    ).space().concat("\\?\\?").space().concat(
      ((CONTINUE or RETURN.space()).concat("\\/".everythingExcept().starClosure())).captureGroup()
    )
    .concat(DOT.starClosure().captureGroup())

  // Smart casts on if-null-checks: if (x != null) --> if let x = x
  val smartCastRule = SPACE.starClosure().captureGroup().concat(IF.space())
    .concat((DefaultExpressions.LITERAL_WORD_.concat(NORMAL_WORD_.starClosure())).captureGroup()).space()
    .concat("!=").space().concat(DefaultExpressions.NIL).space().concat("\\{")


  val R_CLASS = Regex(
    SPACE.starClosure().captureGroup()
      .concat(
        (DefaultExpressions.OR or OPEN or DATA or ABSTRACT or PRIVATE or PROTECTED or INTERNAL or PUBLIC or "").captureGroup()
          .starClosure().captureGroup()
      )
      .concat(CLASS).concat(NORMAL_WORD_.positiveClosure().captureGroup())
      .concat((DOT.positiveClosure().onceOrNotAtAll().makeGenerics() or "").captureGroup())
      .concat(("\\(".concat("\\)".everythingExcept()).starClosure().concat("\\)") or "").captureGroup())
      .concat(((" ".onceOrNotAtAll()).concat(DOT.starClosure().captureGroup())).captureGroup()).concat(" \\{")
  )


  val R_FUN =
    Regex(
      SPACE.starClosure().captureGroup()
        .concat(
          (("\\@".concat(NORMAL_WORD).positiveClosure())
            .concat(
              ("\\(" + DOT.positiveClosure().onceOrNotAtAll() + "\\)").captureGroup().starClosure() + " "
            )).captureGroup().starClosure()
        )
        .concat(
          ((OR or OPEN or OVERRIDE or ABSTRACT or PRIVATE or PROTECTED or INTERNAL or PUBLIC or "")
            .captureGroup().starClosure()).captureGroup()
        ).concat(FUN)
        .concat(
          ((" " or (DOT.positiveClosure().onceOrNotAtAll().makeGenerics().captureGroup()))).captureGroup()
            .positiveClosure()
        )
        .concat(NORMAL_WORD_DOT_GENS_.positiveClosure().captureGroup())
        .concat(("\\(".concat(DOT.starClosure().concat("\\)")).concat(DOT.starClosure())).captureGroup())
    )
}