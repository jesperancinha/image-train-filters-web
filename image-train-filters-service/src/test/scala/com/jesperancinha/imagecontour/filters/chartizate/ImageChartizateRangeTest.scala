package com.jesperancinha.imagecontour.filters.chartizate

import org.scalatest.FunSuite

class ImageChartizateRangeTest extends FunSuite {

  test("testUnicode") {

    val imageChartizateRange: ImageChartizateRange = new ImageChartizateRange(55, "UNO")
    assertResult(imageChartizateRange.rangePercentage) {
      55
    }
    assertResult(imageChartizateRange.unicode) {
      "UNO"
    }
  }

}
