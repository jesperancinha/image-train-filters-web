package com.jesperancinha.imagecontour.filters.chartizate

import org.scalatest.funsuite.AnyFunSuite

class ImageChartizateGraphicsTest extends AnyFunSuite {

  test("testBgColor") {
    val imageChartizateGraphics: ImageChartizateGraphics = new ImageChartizateGraphics(123, 4565)
    assertResult(imageChartizateGraphics.bgColor) {
      123
    }
    assertResult(imageChartizateGraphics.densityPercentage) {
      4565
    }
  }

}
