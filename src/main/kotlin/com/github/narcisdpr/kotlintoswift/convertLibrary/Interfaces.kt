package com.github.narcisdpr.kotlintoswift.convertLibrary

data class Interfaces(
  val name: String,
  var functions: MutableList<String>,
  var properties: MutableList<String>
)
