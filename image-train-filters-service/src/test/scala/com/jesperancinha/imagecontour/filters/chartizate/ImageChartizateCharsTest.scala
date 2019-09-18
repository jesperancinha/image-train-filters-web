package com.jesperancinha.imagecontour.filters.chartizate

import org.scalatest.FunSuite

class ImageChartizateCharsTest extends FunSuite {

  test("testImageChartizateRange") {
    val imageChartizateChars: ImageChartizateChars = new ImageChartizateChars()
    assertResult(new String) {
      imageChartizateChars.imageChartizateRange.unicode
    }
    assertResult(0) {
      imageChartizateChars.imageChartizateRange.rangePercentage
    }
    assertResult(new String) {
      imageChartizateChars.imageChartizateFont.fontName
    }
    assertResult(0) {
      imageChartizateChars.imageChartizateFont.fontSize
    }
  }

}
