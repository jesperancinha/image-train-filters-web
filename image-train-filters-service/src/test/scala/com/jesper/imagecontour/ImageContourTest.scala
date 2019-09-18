package com.jesper.imagecontour

import java.awt.image.BufferedImage

import com.jesper.imagecontour.ImageTestUtils.getBufferedResource
import com.jesperancinha.imagecontour.filters.contour.ImageContour
import org.junit._
import org.scalatest.FunSuite
import org.scalatest.mock.MockitoSugar

class ImageContourTest extends FunSuite with MockitoSugar {

  @Test
  def testApplyImageContour() {
    val sourceImage: BufferedImage = getBufferedResource("/testPanther.jpg")
    val imageContour = new ImageContour(0xFFFFFF, 0x000000, 800000, 2, sourceImage)
    imageContour.apply()
  }


  @Test
  def testApplyImageContour_Panther() {
    val sourceImage: BufferedImage = getBufferedResource("/testPanther.jpg")
    val imageContour = new ImageContour(0xFFFFFF, 0x000000, 800000, 2, sourceImage)
    imageContour.apply()
  }

  @Test
  def testApplyImageContour_Panther1() {
    val sourceImage: BufferedImage = getBufferedResource("/testPanther1.jpg")
    val imageContour = new ImageContour(0xFFFFFF, 0x000000, 150000, 1, sourceImage)
    imageContour.apply()
  }

}