package com.jesper.imagecontour

import java.awt.image.BufferedImage

import com.jesperancinha.imagecontour.filters.{ImageChartizate, ImageKuwahara}
import org.scalatest.{BeforeAndAfterEach, FunSuite}

/**
  * Created by joaofilipesabinoesperancinha on 10-03-16.
  */
class ImageChartizateTest extends FunSuite with BeforeAndAfterEach {

  test("testConvertAndSaveImage") {
    val sourceImage: BufferedImage = ImageTestUtils.getBufferedResource("/testKuwahara1.png")
    val imageChartizate = new ImageChartizate(0, 50, 10, "Arial", 5, "ARABIC")
    imageChartizate.apply(sourceImage)
  }

  test("testConvertAndSaveImageKuwahara2") {
    val sourceImage: BufferedImage = ImageTestUtils.getBufferedResource("/testKuwahara2.png")
    val imageChartizate = new ImageChartizate(0, 50, 10, "Arial", 5, "ARABIC")
    imageChartizate.apply(sourceImage)
  }

  test("testConvertAndSaveImageEyePantherBW") {
    val sourceImage: BufferedImage = ImageTestUtils.getBufferedResource("/testEyeBW.png")
    val imageChartizate = new ImageChartizate(0, 50, 10, "Arial", 5, "ARABIC")
    imageChartizate.apply(sourceImage)
  }

  test("testConvertAndSaveImageEyePanther") {
    val sourceImage: BufferedImage = ImageTestUtils.getBufferedResource("/testEye.png")
    val imageChartizate = new ImageChartizate(0, 50, 10, "Arial", 5, "ARABIC")
    imageChartizate.apply(sourceImage)
  }

  test("testConvertAndSaveImagePanther") {
    val sourceImage: BufferedImage = ImageTestUtils.getBufferedResource("/testPanther.jpg")
    val imageChartizate = new ImageKuwahara(4, 1)
    imageChartizate.apply(sourceImage)
  }

  test("testConvertAndSaveImagePanther5Its") {
    val sourceImage: BufferedImage = ImageTestUtils.getBufferedResource("/testPanther1.jpg")
    val imageChartizate = new ImageKuwahara(2, 5)
    imageChartizate.apply(sourceImage)
  }

}
