package com.github.narcisdpr.kotlintoswift.convertLibrary

data class Classes(
  val name: String,
  var functions: MutableList<String>,
  var properties: MutableList<String>
)
