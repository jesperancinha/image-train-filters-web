package com.jesper.imagecontour

import java.awt.image.BufferedImage

import com.jesperancinha.imagecontour.filters.kuwahara.ImageKuwahara
import org.scalatest.{BeforeAndAfterEach, FunSuite}

class ImageKuwaharaTest extends FunSuite with BeforeAndAfterEach {

  test("testConvertAndSaveImage") {
    val sourceImage: BufferedImage = ImageTestUtils.getBufferedResource("/testKuwahara1.png")
    val imageKuwahara = new ImageKuwahara(2, 1, sourceImage)
    val image = imageKuwahara.apply()
    image
  }

  test("testConvertAndSaveImageKuwahara2") {
    val sourceImage: BufferedImage = ImageTestUtils.getBufferedResource("/testKuwahara2.png")
    val imageKuwahara = new ImageKuwahara(2, 1, sourceImage)
    imageKuwahara.apply()
  }

  test("testConvertAndSaveImageEyePantherBW") {
    val sourceImage: BufferedImage = ImageTestUtils.getBufferedResource("/testEyeBW.png")
    val imageKuwahara = new ImageKuwahara(2, 1, sourceImage)
    imageKuwahara.apply()
  }

  test("testConvertAndSaveImageEyePanther") {
    val sourceImage: BufferedImage = ImageTestUtils.getBufferedResource("/testEye.png")
    val imageKuwahara = new ImageKuwahara(4, 1, sourceImage)
    imageKuwahara.apply()
  }

  test("testConvertAndSaveImagePanther") {
    val sourceImage: BufferedImage = ImageTestUtils.getBufferedResource("/testPanther.jpg")
    val imageKuwahara = new ImageKuwahara(4, 1, sourceImage)
    imageKuwahara.apply()
  }

  test("testConvertAndSaveImagePanther5Its") {
    val sourceImage: BufferedImage = ImageTestUtils.getBufferedResource("/testPanther1.jpg")
    val imageKuwahara = new ImageKuwahara(2, 5, sourceImage)
    imageKuwahara.apply()
  }

}
