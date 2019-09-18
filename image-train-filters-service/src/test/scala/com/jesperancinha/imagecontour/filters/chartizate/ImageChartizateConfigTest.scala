package com.jesperancinha.imagecontour.filters.chartizate

import org.scalatest.FunSuite

class ImageChartizateConfigTest extends FunSuite {

  test("testConfiguration") {
    val config: ImageChartizateConfig = ImageChartizateConfig()
      .addFontName("WOW")
      .addFontSize(10)
      .addRangePercentage(15)
      .addUnicode("UTC")
      .addBgColor(255)
      .addDensityPercentage(55)

    assertResult(config.imageChartizateChars.imageChartizateFont.fontName) {
      "WOW"
    }
    assertResult(config.imageChartizateChars.imageChartizateFont.fontSize) {
      10
    }
    assertResult(config.imageChartizateChars.imageChartizateRange.rangePercentage) {
      15
    }
    assertResult(config.imageChartizateChars.imageChartizateRange.unicode) {
      "UTC"
    }
    assertResult(config.imageChartizateGraphics.bgColor) {
      255
    }
    assertResult(config.imageChartizateGraphics.densityPercentage) {
      55
    }
  }

}
