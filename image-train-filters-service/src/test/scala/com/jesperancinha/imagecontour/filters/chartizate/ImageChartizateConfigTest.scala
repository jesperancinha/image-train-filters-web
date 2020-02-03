package com.jesperancinha.imagecontour.filters.chartizate

import org.scalatest.funsuite.AnyFunSuite

class ImageChartizateConfigTest extends AnyFunSuite {

  test("testConfiguration") {
    val config: ImageChartizateConfig = ImageChartizateConfig()
      .addFontName("WOW")
      .addFontSize(10)
      .addRangePercentage(15)
      .addUnicode("UTF")
      .addBgColor(255)
      .addDensityPercentage(55)

    assertResult("WOW") {
      config.imageChartizateChars.imageChartizateFont.fontName
    }
    assertResult(10) {
      config.imageChartizateChars.imageChartizateFont.fontSize
    }
    assertResult(15) {
      config.imageChartizateChars.imageChartizateRange.rangePercentage
    }
    assertResult("UTF") {
      config.imageChartizateChars.imageChartizateRange.unicode
    }
    assertResult(255) {
      config.imageChartizateGraphics.bgColor
    }
    assertResult(55) {
      config.imageChartizateGraphics.densityPercentage
    }
  }

}
