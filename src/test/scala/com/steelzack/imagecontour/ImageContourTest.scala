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
class ImageContourTest extends FunSuite with MockitoSugar {

  @Test
  def testApplyImageContour() {
    val sourceImage: BufferedImage = getBufferedResource("/testPanther.jpg")
    val filter = new ImageContour(0xFFFFFF, 0x000000, 800000, 2)
    val out = filter(sourceImage)
  }

  def getBufferedResource(resourcePath: String): BufferedImage = {
    val fileBytes = Files.readAllBytes(Paths.get(getClass().getResource(resourcePath).toURI))
    val byteStream: InputStream = new ByteArrayInputStream(fileBytes)
    val sourceImage: BufferedImage = ImageIO.read(byteStream);
    byteStream.close
    sourceImage
  }

  @Test
  def testApplyImageContour_Panther() {
    val sourceImage: BufferedImage = getBufferedResource("/testPanther.jpg")
    val imageContour = new ImageContour(0xFFFFFF, 0x000000, 800000, 2)
    imageContour.apply(sourceImage)
  }

  @Test
  def testApplyImageContour_Panther1() {
    val sourceImage: BufferedImage = getBufferedResource("/testPanther1.jpg")
    val imageContour = new ImageContour(0xFFFFFF, 0x000000, 150000, 1)
    imageContour.apply(sourceImage)
  }
}