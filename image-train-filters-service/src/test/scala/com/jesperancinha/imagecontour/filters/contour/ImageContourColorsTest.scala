package com.jesperancinha.imagecontour.filters.contour

import org.scalatest.FunSuite

class ImageContourColorsTest extends FunSuite {

  test("testLnColor") {
    val imageContourColors: ImageContourColors = new ImageContourColors(123, 456)
    assertResult(imageContourColors.bgColor) {
      123
    }
    assertResult(imageContourColors.lnColor) {
      456
    }
  }

}
