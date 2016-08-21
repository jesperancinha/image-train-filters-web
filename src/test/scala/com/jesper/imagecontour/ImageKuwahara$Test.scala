package com.jesper.imagecontour

import java.awt.image.BufferedImage
import java.io.{ByteArrayInputStream, InputStream}
import java.nio.file.{Paths, Files}
import javax.imageio.ImageIO

import org.scalatest.{BeforeAndAfterEach, FunSuite}

/**
  * Created by joaofilipesabinoesperancinha on 10-03-16.
  */
class ImageKuwahara$Test extends FunSuite with BeforeAndAfterEach {

  override def beforeEach() {

  }

  override def afterEach() {

  }

  test("testGetStandardDeviation") {
    val imageKuwahara = ImageKuwahara
    val sourceImage: BufferedImage = getBufferedResource("/testKuwahara1.png")
  }

  test("testConvertAndSaveImage") {
    val sourceImage: BufferedImage = getBufferedResource("/testKuwahara1.png")
    val imageKuwahara = ImageKuwahara
    imageKuwahara.apply(sourceImage, 2)
    imageKuwahara.applyFilter
  }

  test("testConvertAndSaveImageKuwahara2") {
    val sourceImage: BufferedImage = getBufferedResource("/testKuwahara2.png")
    val imageKuwahara = ImageKuwahara
    imageKuwahara.apply(sourceImage, 2)
    imageKuwahara.applyFilter
  }

  test("testConvertAndSaveImageEyePantherBW") {
    val sourceImage: BufferedImage = getBufferedResource("/testEyeBW.png")
    val imageKuwahara = ImageKuwahara
    imageKuwahara.apply(sourceImage, 2, 1)
    imageKuwahara.applyFilter
  }

  test("testConvertAndSaveImageEyePanther") {
    val sourceImage: BufferedImage = getBufferedResource("/testEye.png")
    val imageKuwahara = ImageKuwahara
    imageKuwahara.apply(sourceImage, 4, 1)
    imageKuwahara.applyFilter
  }

  test("testConvertAndSaveImagePanther") {
    val sourceImage: BufferedImage = getBufferedResource("/testPanther.jpg")
    val imageKuwahara = ImageKuwahara
    imageKuwahara.apply(sourceImage, 4, 1)
    imageKuwahara.applyFilter
  }

  test("testConvertAndSaveImagePanther5Its") {
    val sourceImage: BufferedImage = getBufferedResource("/testPanther1.jpg")
    val imageKuwahara = ImageKuwahara
    imageKuwahara.apply(sourceImage, 2, 5)
    imageKuwahara.applyFilter
  }

  test("testGetAverage") {

  }

  def getBufferedResource(resourcePath: String): BufferedImage = {
    val fileBytes = Files.readAllBytes(Paths.get(getClass().getResource(resourcePath).toURI))
    val byteStream: InputStream = new ByteArrayInputStream(fileBytes)
    val sourceImage: BufferedImage = ImageIO.read(byteStream);
    byteStream.close
    sourceImage
  }

}
