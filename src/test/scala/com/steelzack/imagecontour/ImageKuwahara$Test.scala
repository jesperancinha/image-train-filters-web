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
    val sourceImage: BufferedImage = getBufferedResource("/testKuwahara.png")

    val averageArray1 = imageKuwahara.getAverage(sourceImage,0,0,1,1)
    val averageArray2 = imageKuwahara.getAverage(sourceImage,3,0,4,1)
    val averageArray3 = imageKuwahara.getAverage(sourceImage,0,3,1,4)
    val averageArray4 = imageKuwahara.getAverage(sourceImage,3,3,4,4)
  }

  test("testConvertAndSaveImage") {

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
