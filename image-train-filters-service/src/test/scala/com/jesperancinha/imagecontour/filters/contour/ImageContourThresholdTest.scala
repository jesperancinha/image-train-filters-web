package com.jesperancinha.imagecontour.filters.contour

import org.scalatest.funsuite.AnyFunSuite

class ImageContourThresholdTest extends AnyFunSuite {

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
