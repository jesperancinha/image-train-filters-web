package com.jesperancinha.imagecontour.filters.kuwahara

import java.awt.image.Raster

import org.scalatest.FunSuite
import org.scalatest.mockito.MockitoSugar

class ImageKuwaharaPropertiesTest extends FunSuite with MockitoSugar {

  test("testSrcOut") {
    val sourceData: Raster = mock[Raster]
    val imageKuwaharaProperties: ImageKuwaharaProperties = new ImageKuwaharaProperties(sourceData, 10, 20)
    assertResult(imageKuwaharaProperties.sourceData) {
      sourceData
    }
    assertResult(imageKuwaharaProperties.width) {
      10
    }
    assertResult(imageKuwaharaProperties.height) {
      20
    }
  }

}
