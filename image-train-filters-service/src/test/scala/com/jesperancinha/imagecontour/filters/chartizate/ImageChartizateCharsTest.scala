package com.jesperancinha.imagecontour.filters.chartizate

import org.scalatest.FunSuite

class ImageChartizateCharsTest extends FunSuite {

  test("testImageChartizateRange") {
    val imageChartizateChars: ImageChartizateChars = new ImageChartizateChars()
    assertResult(imageChartizateChars.imageChartizateRange.unicode) {
      new String
    }
    assertResult(imageChartizateChars.imageChartizateRange.rangePercentage) {
      0
    }
    assertResult(imageChartizateChars.imageChartizateFont.fontName) {
      new String
    }
    assertResult(imageChartizateChars.imageChartizateFont.fontSize) {
      0
    }
  }

}
