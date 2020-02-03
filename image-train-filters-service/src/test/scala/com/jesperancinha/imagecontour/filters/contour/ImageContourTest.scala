package com.jesperancinha.imagecontour.filters.contour

import java.awt.image.BufferedImage

import com.jesperancinha.imagecontour.filters.ImageTestUtils.getBufferedResource
import org.scalatest.BeforeAndAfterEach
import org.scalatest.funsuite.AnyFunSuite

class ImageContourTest extends AnyFunSuite with BeforeAndAfterEach {

  test("testApplyImageContour") {
    val sourceImage: BufferedImage = getBufferedResource("/testMarket.jpg")
    val imageContour = new ImageContour(
      ImageContourConfig()
        .addBgColor(0xFFFFFF)
        .addLineColor(0x000000)
        .addDiffThreshold(800000)
        .addRadius(2),
      sourceImage)
    imageContour.apply()
  }

  test("testApplyImageContourPanther") {
    val sourceImage: BufferedImage = getBufferedResource("/testMarket.jpg")
    val imageContour = new ImageContour(
      ImageContourConfig()
        .addBgColor(0xFFFFFF)
        .addLineColor(0x000000)
        .addDiffThreshold(800000)
        .addRadius(2),
      sourceImage)
    imageContour.apply()
  }

  test("testApplyImageContourPanther1") {
    val sourceImage: BufferedImage = getBufferedResource("/testMarketSmallBlur.png")
    val imageContour = new ImageContour(
      ImageContourConfig()
        .addBgColor(0xFFFFFF)
        .addLineColor(0x000000)
        .addDiffThreshold(150000)
        .addRadius(1),
      sourceImage)
    imageContour.apply()
  }

}