package com.jesperancinha.imagecontour.filters.kuwahara

import java.awt.image.{BufferedImage, Raster}
import java.awt.Point

import org.scalatest.funsuite.AnyFunSuite

class ImageKuwaharaPropertiesTest extends AnyFunSuite {

  test("testSourceData") {
    val sourceData: Raster = new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB).getData()
    val imageKuwaharaProperties: ImageKuwaharaProperties = new ImageKuwaharaProperties(sourceData, 10, 20)
    assertResult(imageKuwaharaProperties.sourceData) {
      sourceData
    }
    assertResult(10) {
      imageKuwaharaProperties.width
    }
    assertResult(20) {
      imageKuwaharaProperties.height
    }
  }

}
