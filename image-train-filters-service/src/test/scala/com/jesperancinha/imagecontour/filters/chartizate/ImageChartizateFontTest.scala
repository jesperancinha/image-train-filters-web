package com.jesperancinha.imagecontour.filters.chartizate

import org.scalatest.FunSuite

class ImageChartizateFontTest extends FunSuite {

  test("testFontName") {
    val imageChartizateFont: ImageChartizateFont = new ImageChartizateFont("FONTI", 2)
    assertResult(2) {
      imageChartizateFont.fontSize
    }
    assertResult("FONTI") {
      imageChartizateFont.fontName
    }
  }

}
