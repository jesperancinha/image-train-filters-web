package com.jesperancinha.imagecontour.filters.contour

import org.scalatest.FunSuite

class ImageContourThresholdTest extends FunSuite {

  test("testDiffThreshold") {
    val imageContourThreshold: ImageContourThreshold = new ImageContourThreshold(123D, 123)
    assertResult(imageContourThreshold.diffThreshold) {
      123D
    }
    assertResult(imageContourThreshold.radius) {
      123
    }
  }

}
