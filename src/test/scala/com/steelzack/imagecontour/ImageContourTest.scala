package com.steelzack.imagecontour

import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream
import java.nio.file.{Paths, Files}
import javax.imageio.ImageIO

import org.scalatest.FunSuite
import org.scalatest.mock.MockitoSugar
import org.junit._
/**
  * Created by joaofilipesabinoesperancinha on 03-03-16.
  */
class ImageContourTest extends FunSuite with MockitoSugar{

  @Test
  def  testPrintCharsToConsole () {

    val fileBytes = Files.readAllBytes(Paths.get(getClass().getResource("/testPanther.jpg").toURI))
    val byteStream : java.io.InputStream = new ByteArrayInputStream(fileBytes)
    val bImageFromConvert :  BufferedImage = ImageIO.read(byteStream);
    byteStream.close

    val imageContour = ImageContour

    imageContour.convertAndSaveImage(bImageFromConvert, 0x0000, 0xFFFFFF, 0.20)

    var i: Integer = 1
  }
}