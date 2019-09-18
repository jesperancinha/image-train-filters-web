package com.jesperancinha.imagecontour.filters.chartizate

import org.scalatest.FunSuite

class ImageChartizateFontTest extends FunSuite {

  test("testFontName") {
    val imageChartizateFont: ImageChartizateFont = new ImageChartizateFont("FONTI", 2)
    assertResult(imageChartizateFont.fontSize) {
      2
    }
    assertResult(imageChartizateFont.fontName) {
      "FONTI"
    }
  }

}
