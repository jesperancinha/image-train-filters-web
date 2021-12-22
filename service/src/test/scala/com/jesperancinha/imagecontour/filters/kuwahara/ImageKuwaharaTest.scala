package com.jesperancinha.imagecontour.filters.kuwahara

import com.jesperancinha.imagecontour.filters.ImageTestUtils
import org.scalatest.BeforeAndAfterEach
import org.scalatest.funsuite.AnyFunSuite

import java.awt.image.BufferedImage

class ImageKuwaharaTest extends AnyFunSuite with BeforeAndAfterEach {

  test("testConvertAndSaveImage") {
    val sourceImage: BufferedImage = ImageTestUtils.getBufferedResource("/testMarket.jpg")
    val imageKuwahara = new ImageKuwahara(2, 1, sourceImage)
    val image = imageKuwahara.apply()
    image
  }

  test("testConvertAndSaveSmallImage") {
    val sourceImage: BufferedImage = ImageTestUtils.getBufferedResource("/testMarketSmall.jpg")
    val imageKuwahara = new ImageKuwahara(2, 1, sourceImage)
    val image = imageKuwahara.apply()
    image
  }

  test("testRefreshTotals") {
    val sourceImage: BufferedImage = ImageTestUtils.getBufferedResource("/testMarketSmallBlur.png")
    val imageKuwahara = new ImageKuwahara(2, 5, sourceImage)
    val results = imageKuwahara.refreshTotals(Array(4D, 6D, 8D, 10D), 2)
    assertResult(results(0)) {
      2D
    }
    assertResult(results(1)) {
      3D
    }
    assertResult(results(2)) {
      4D
    }
    assertResult(results(3)) {
      5D
    }
  }

  test("testProcessSquare") {
    val sourceImage: BufferedImage = ImageTestUtils.getBufferedResource("/testMarketSmallBlur.png")
    val imageKuwahara = new ImageKuwahara(2, 5, sourceImage)
    val totals: Array[Double] = Array(0D, 0D, 0D, 0D)

    imageKuwahara.processSquare((0 to 10, 0 to 10), totals, totals)

    assertResult(totals(0)) {
      206D
    }
    assertResult(totals(1)) {
      134D
    }
    assertResult(totals(2)) {
      62D
    }
    assertResult(totals(3)) {
      0D
    }
  }

  test("testGetAverageGrey") {
    val sourceImage: BufferedImage = ImageTestUtils.getBufferedResource("/testMarketSmallBlur.png")
    val imageKuwahara = new ImageKuwahara(11, 5, sourceImage)
    val resultingGrey = imageKuwahara.getAverageGrey(100 to 110, 100 to 110)
    assertResult(0D) {
      resultingGrey
    }
  }

  test("testGetStandardDeviation") {
    val sourceImage: BufferedImage = ImageTestUtils.getBufferedResource("/testMarketSmallBlur.png")
    val imageKuwahara = new ImageKuwahara(11, 5, sourceImage)
    val resultingStd = imageKuwahara.getStandardDeviation((100 to 110, 100 to 110), 123)
    assertResult(15129D) {
      resultingStd
    }
  }

  test("testGetRealMinValue1NaN") {
    val sourceImage: BufferedImage = ImageTestUtils.getBufferedResource("/testMarketSmallBlur.png")
    val imageKuwahara = new ImageKuwahara(11, 5, sourceImage)
    val realMinValue = imageKuwahara.getRealMinValue(Double.NaN, 123D)
    assertResult(123D) {
      realMinValue
    }
  }

  test("testGetRealMinValue2NaN") {
    val sourceImage: BufferedImage = ImageTestUtils.getBufferedResource("/testMarketSmallBlur.png")
    val imageKuwahara = new ImageKuwahara(11, 5, sourceImage)
    val realMinValue = imageKuwahara.getRealMinValue(222D, Double.NaN)
    assertResult(222D)(realMinValue)
  }

  test("testGetRealMinValue121Min") {
    val sourceImage: BufferedImage = ImageTestUtils.getBufferedResource("/testMarketSmallBlur.png")
    val imageKuwahara = new ImageKuwahara(11, 5, sourceImage)
    val realMinValue = imageKuwahara.getRealMinValue(222D, 555D)
    assertResult(222D)(realMinValue)
  }

  test("testGetRealMinValue122Min") {
    val sourceImage: BufferedImage = ImageTestUtils.getBufferedResource("/testMarketSmallBlur.png")
    val imageKuwahara = new ImageKuwahara(11, 5, sourceImage)
    val realMinValue = imageKuwahara.getRealMinValue(123D, 98D)
    assertResult(98D)(realMinValue)
  }

  test("testGetMinDeviationAverageColor") {
    val sourceImage: BufferedImage = ImageTestUtils.getBufferedResource("/testMarketSmallBlurXS.png")
    val imageKuwahara = new ImageKuwahara(5, 5, sourceImage)
    val realMinValue = imageKuwahara.getMinDeviationAverageColor(Map(45D ->
      Array[Double](34D), 1D -> Array[Double](3D), 5D -> Array[Double](10D)))
    assertResult(Array(3D))(realMinValue)
  }
}
