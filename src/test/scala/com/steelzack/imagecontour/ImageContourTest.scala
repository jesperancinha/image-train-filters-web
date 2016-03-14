package com.steelzack.imagecontour

import java.awt.image.BufferedImage
import java.io.{InputStream, ByteArrayInputStream}
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
  def  testPrintCharsToConsole_2 () {

    val sourceImage: BufferedImage = getBufferedResource("/testPanther.jpg")

    val imageContour = ImageContour

    imageContour.convertAndSaveImage(sourceImage, 0xFFFFFF, 0x000000, 800000, 2)
  }

  def getBufferedResource(resourcePath: String): BufferedImage = {
    val fileBytes = Files.readAllBytes(Paths.get(getClass().getResource(resourcePath).toURI))
    val byteStream: InputStream = new ByteArrayInputStream(fileBytes)
    val sourceImage: BufferedImage = ImageIO.read(byteStream);
    byteStream.close
    sourceImage
  }

  @Test
  def  testPrintCharsToConsole_1 () {
    val sourceImage: BufferedImage = getBufferedResource("/testPanther1.jpg")
    val imageContour = ImageContour
    imageContour.convertAndSaveImage(sourceImage, 0xFFFFFF, 0x000000, 150000, 1)
  }
}