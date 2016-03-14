package com.steelzack.imagecontour

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
    val imageContour = ImageKuwahara
    imageContour.applyFilter(sourceImage, 2)
  }

  test("testConvertAndSaveImageKuwahara2") {
    val sourceImage: BufferedImage = getBufferedResource("/testKuwahara2.png")
    val imageContour = ImageKuwahara
    imageContour.applyFilter(sourceImage, 2)
  }

  test("testConvertAndSaveImageEyePantherBW") {
    val sourceImage: BufferedImage = getBufferedResource("/testEyeBW.png")
    val imageContour = ImageKuwahara
    imageContour.applyFilter(sourceImage, 2, 1)
  }

  test("testConvertAndSaveImageEyePanther") {
    val sourceImage: BufferedImage = getBufferedResource("/testEye.png")
    val imageContour = ImageKuwahara
    imageContour.applyFilter(sourceImage, 4, 1)
  }

  test("testConvertAndSaveImagePanther") {
    val sourceImage: BufferedImage = getBufferedResource("/testPanther.jpg")
    val imageContour = ImageKuwahara
    imageContour.applyFilter(sourceImage, 4, 1)
  }

  test("testConvertAndSaveImagePanther5Its") {
    val sourceImage: BufferedImage = getBufferedResource("/testPanther1.jpg")
    val imageContour = ImageKuwahara
    imageContour.applyFilter(sourceImage, 2, 5)
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
