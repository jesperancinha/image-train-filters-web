package com.steelzack.imagecontour

import org.scalatest.FunSuite
import org.scalatest.mock.MockitoSugar
import org.junit._
/**
  * Created by joaofilipesabinoesperancinha on 03-03-16.
  */
class ImageContourTest extends FunSuite with MockitoSugar{
  @Test
  def  printCharsToConsole () {

    val source = scala.io.Source.fromURL(getClass().getResource("/testPanther.jpg"))

    var imgContour = ImageContour;

    imgContour.printCharsToConsole(source)
  }
}