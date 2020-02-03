package com.jesperancinha.imagecontour.filters.contour

import org.scalatest.funsuite.AnyFunSuite

class ImageContourConfigTest extends AnyFunSuite {

  test("testContourConfig") {
    val imageContourConfig = ImageContourConfig()
      .addBgColor(123)
      .addLineColor(456)
      .addDiffThreshold(111D)
      .addRadius(999)
    assertResult(imageContourConfig.imageContourColors.bgColor) {
      123
    }
    assertResult(imageContourConfig.imageContourColors.lnColor) {
      456
    }
    assertResult(imageContourConfig.imageContourThreshold.diffThreshold) {
      111D
    }
    assertResult(imageContourConfig.imageContourThreshold.radius) {
      999
    }
  }

}
