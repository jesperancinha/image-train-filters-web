package com.jesper.imagecontour

import java.awt.image.BufferedImage

import com.jesperancinha.imagecontour.filters.ImageKuwahara
import org.scalatest.{BeforeAndAfterEach, FunSuite}

/**
  * Created by joaofilipesabinoesperancinha on 10-03-16.
  */
class ImageKuwaharaTest extends FunSuite with BeforeAndAfterEach {

  test("testConvertAndSaveImage") {
    val sourceImage: BufferedImage = ImageTestUtils.getBufferedResource("/testKuwahara1.png")
    val imageKuwahara = new ImageKuwahara(2, 1)
    imageKuwahara.apply(sourceImage)
  }

  test("testConvertAndSaveImageKuwahara2") {
    val sourceImage: BufferedImage = ImageTestUtils.getBufferedResource("/testKuwahara2.png")
    val imageKuwahara = new ImageKuwahara(2, 1)
    imageKuwahara.apply(sourceImage)
  }

  test("testConvertAndSaveImageEyePantherBW") {
    val sourceImage: BufferedImage = ImageTestUtils.getBufferedResource("/testEyeBW.png")
    val imageKuwahara = new ImageKuwahara(2, 1)
    imageKuwahara.apply(sourceImage)
  }

  test("testConvertAndSaveImageEyePanther") {
    val sourceImage: BufferedImage = ImageTestUtils.getBufferedResource("/testEye.png")
    val imageKuwahara = new ImageKuwahara(4, 1)
    imageKuwahara.apply(sourceImage)
  }

  test("testConvertAndSaveImagePanther") {
    val sourceImage: BufferedImage = ImageTestUtils.getBufferedResource("/testPanther.jpg")
    val imageKuwahara = new ImageKuwahara(4, 1)
    imageKuwahara.apply(sourceImage)
  }

  test("testConvertAndSaveImagePanther5Its") {
    val sourceImage: BufferedImage = ImageTestUtils.getBufferedResource("/testPanther1.jpg")
    val imageKuwahara = new ImageKuwahara(2, 5)
    imageKuwahara.apply(sourceImage)
  }

}
