package com.jesperancinha.imagecontour.filters.contour

import java.awt.image.BufferedImage

import com.jesperancinha.imagecontour.filters.ImageTestUtils.getBufferedResource
import org.scalatest.{BeforeAndAfterEach, FunSuite}

class ImageContourTest extends FunSuite with BeforeAndAfterEach {

  test("testApplyImageContour") {
    val sourceImage: BufferedImage = getBufferedResource("/testPanther.jpg")
    val imageContour = new ImageContour(0xFFFFFF, 0x000000, 800000, 2, sourceImage)
    imageContour.apply()
  }

  test("testApplyImageContourPanther") {
    val sourceImage: BufferedImage = getBufferedResource("/testPanther.jpg")
    val imageContour = new ImageContour(0xFFFFFF, 0x000000, 800000, 2, sourceImage)
    imageContour.apply()
  }

  test("testApplyImageContourPanther1") {
    val sourceImage: BufferedImage = getBufferedResource("/testPanther1.jpg")
    val imageContour = new ImageContour(0xFFFFFF, 0x000000, 150000, 1, sourceImage)
    imageContour.apply()
  }

}