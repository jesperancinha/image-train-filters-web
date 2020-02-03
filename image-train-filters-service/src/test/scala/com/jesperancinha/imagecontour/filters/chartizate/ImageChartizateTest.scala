package com.jesperancinha.imagecontour.filters.chartizate

import java.awt.image.BufferedImage

import com.jesperancinha.imagecontour.filters.ImageTestUtils
import com.jesperancinha.imagecontour.filters.kuwahara.ImageKuwahara
import org.scalatest.BeforeAndAfterEach
import org.scalatest.funsuite.AnyFunSuite

class ImageChartizateTest extends AnyFunSuite with BeforeAndAfterEach {

  test("testConvertAndSaveMarketImage") {
    val sourceImage: BufferedImage = ImageTestUtils.getBufferedResource("/testMarket.jpg")
    val imageChartizate = new ImageChartizate(ImageChartizateConfig()
      .addDensityPercentage(50)
      .addRangePercentage(10)
      .addFontName("Arial")
      .addFontSize(5)
      .addUnicode("LATIN_EXTENDED_A"),
      sourceImage)
    val image = imageChartizate.apply()
    image
  }

  test("testConvertAndSaveMarketSmallImage") {
    val sourceImage: BufferedImage = ImageTestUtils.getBufferedResource("/testMarketSmall.jpg")
    val imageChartizate = new ImageChartizate(ImageChartizateConfig()
      .addDensityPercentage(50)
      .addRangePercentage(10)
      .addFontName("Arial")
      .addFontSize(5)
      .addUnicode("LATIN_EXTENDED_A"),
      sourceImage)
    val image = imageChartizate.apply()
    image
  }

  test("testConvertAndSaveImagePanther") {
    val sourceImage: BufferedImage = ImageTestUtils.getBufferedResource("/testMarket.jpg")
    val imageChartizate = new ImageKuwahara(4, 1, sourceImage)
    val image = imageChartizate.apply()
    image
  }

  test("testConvertAndSaveImagePanther5Its") {
    val sourceImage: BufferedImage = ImageTestUtils.getBufferedResource("/testMarketSmallBlur.png")
    val imageChartizate = new ImageKuwahara(2, 5, sourceImage)
    val image = imageChartizate.apply()
    image
  }

}
