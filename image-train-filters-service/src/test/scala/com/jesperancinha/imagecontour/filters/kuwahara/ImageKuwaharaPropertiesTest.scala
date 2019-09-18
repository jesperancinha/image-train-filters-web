package com.jesperancinha.imagecontour.filters.kuwahara

import java.awt.image.Raster

import org.scalatest.FunSuite
import org.scalatest.mockito.MockitoSugar

class ImageKuwaharaPropertiesTest extends FunSuite with MockitoSugar {

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
