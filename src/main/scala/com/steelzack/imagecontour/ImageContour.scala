package com.steelzack.imagecontour

import scala.io.BufferedSource

/**
  * Created by joaofilipesabinoesperancinha on 03-03-16.
  */
object ImageContour extends App {

  override def main(args: Array[String]) {
    val fileName: String = args(0)
    val source = scala.io.Source.fromFile(fileName)
    printCharsToConsole(source)
  }

  def printCharsToConsole(source: BufferedSource): Unit = {
    val byteArray = source.map(_.toByte).toArray
    var i = 0
    for (i <- 1 to byteArray.length) {
      print(byteArray(i))
    }
    source.close()
  }

  def apply(): Unit = {

  }
}
