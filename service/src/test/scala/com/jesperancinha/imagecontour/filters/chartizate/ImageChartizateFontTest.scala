package com.jesperancinha.imagecontour.filters.chartizate

import org.scalatest.funsuite.AnyFunSuite

class ImageChartizateFontTest extends AnyFunSuite {

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
