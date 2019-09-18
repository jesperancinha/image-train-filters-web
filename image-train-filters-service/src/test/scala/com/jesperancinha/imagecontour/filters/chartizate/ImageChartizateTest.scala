package com.jesperancinha.imagecontour.filters.chartizate

import java.awt.image.BufferedImage

import com.jesperancinha.imagecontour.filters.ImageTestUtils
import com.jesperancinha.imagecontour.filters.kuwahara.ImageKuwahara
import org.scalatest.{BeforeAndAfterEach, FunSuite}

class ImageChartizateTest extends FunSuite with BeforeAndAfterEach {

  test("testConvertAndSaveImage") {
    val sourceImage: BufferedImage = ImageTestUtils.getBufferedResource("/testKuwahara1.png")
    val imageChartizate = new ImageChartizate(ImageChartizateConfig()
      .addDensityPercentage(50)
      .addRangePercentage(10)
      .addFontName("Arial")
      .addFontSize(5)
      .addUnicode("LATIN_EXTENDED_A"),
      sourceImage)
    imageChartizate.apply()
  }

  test("testConvertAndSaveImageKuwahara2") {
    val sourceImage: BufferedImage = ImageTestUtils.getBufferedResource("/testKuwahara2.png")
    val imageChartizate = new ImageChartizate(ImageChartizateConfig()
      .addDensityPercentage(50)
      .addRangePercentage(10)
      .addFontName("Arial")
      .addFontSize(5)
      .addUnicode("LATIN_EXTENDED_A"),
      sourceImage)
    imageChartizate.apply()
  }

  test("testConvertAndSaveImageEyePantherBW") {
    val sourceImage: BufferedImage = ImageTestUtils.getBufferedResource("/testEyeBW.png")
    val imageChartizate = new ImageChartizate(ImageChartizateConfig()
      .addDensityPercentage(50)
      .addRangePercentage(10)
      .addFontName("Arial")
      .addFontSize(5)
      .addUnicode("LATIN_EXTENDED_A"),
      sourceImage)
    imageChartizate.apply()
  }

  test("testConvertAndSaveImageEyePanther") {
    val sourceImage: BufferedImage = ImageTestUtils.getBufferedResource("/testEye.png")
    val imageChartizate = new ImageChartizate(ImageChartizateConfig()
      .addDensityPercentage(50)
      .addRangePercentage(10)
      .addFontName("Arial")
      .addFontSize(5)
      .addUnicode("LATIN_EXTENDED_A"),
      sourceImage)
    imageChartizate.apply()
  }

  test("testConvertAndSaveImagePanther") {
    val sourceImage: BufferedImage = ImageTestUtils.getBufferedResource("/testPanther.jpg")
    val imageChartizate = new ImageKuwahara(4, 1, sourceImage)
    imageChartizate.apply()
  }

  test("testConvertAndSaveImagePanther5Its") {
    val sourceImage: BufferedImage = ImageTestUtils.getBufferedResource("/testPanther1.jpg")
    val imageChartizate = new ImageKuwahara(2, 5, sourceImage)
    imageChartizate.apply()
  }

}
