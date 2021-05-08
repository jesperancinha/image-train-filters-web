package com.jesperancinha.imagecontour.filters.kuwahara

import java.awt.image.Raster

import org.mockito.MockitoSugar
import org.scalatest.funsuite.AnyFunSuite

class ImageKuwaharaPropertiesTest extends AnyFunSuite with MockitoSugar {

  test("testSourceData") {
    val sourceData: Raster = mock[Raster]
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
