package com.jesperancinha.imagecontour.filters

import java.awt.Color
import java.awt.image.{BufferedImage, Raster}

import scala.Array.fill
import scala.util.{Failure, Success, Try}

class ImageKuwahara(squareSize: Int, iterations: Int) extends ImageFilter[BufferedImage, BufferedImage] {
  def apply(source: BufferedImage): BufferedImage = {
    val srcOut: Raster = source.getData()
    val w: Int = srcOut.getWidth
    val h: Int = srcOut.getHeight
    (0 to iterations).map(_ => {
      val out = new BufferedImage(w - squareSize, h - squareSize, BufferedImage.TYPE_INT_RGB)
      performIteration(srcOut, w, h, out)
      out.getRaster
      out
    }).lastOption.orNull
  }

  def performIteration(sourceData: Raster, w: Int, h: Int, out: BufferedImage): Unit = {
    for (i <- squareSize until w - squareSize by 1) {
      for (j <- squareSize until h - squareSize by 1) {
        val leftXRange = i - squareSize until i
        val downYRange = j - squareSize until j
        val upYRange = j + 1 to j + squareSize
        val rightXRange = i + 1 to i + squareSize
        val avg1: Double = getAverageGrey(sourceData, leftXRange, downYRange, squareSize)
        val avg2: Double = getAverageGrey(sourceData, leftXRange, upYRange, squareSize)
        val avg3: Double = getAverageGrey(sourceData, rightXRange, downYRange, squareSize)
        val avg4: Double = getAverageGrey(sourceData, rightXRange, upYRange, squareSize)

        val avg1Color: Array[Double] = getAverage(sourceData, leftXRange, downYRange, squareSize)
        val avg2Color: Array[Double] = getAverage(sourceData, leftXRange, upYRange, squareSize)
        val avg3Color: Array[Double] = getAverage(sourceData, rightXRange, downYRange, squareSize)
        val avg4Color: Array[Double] = getAverage(sourceData, rightXRange, upYRange, squareSize)

        val std1: Double = getStandardDeviation(sourceData, leftXRange, downYRange, avg1, squareSize)
        val std2: Double = getStandardDeviation(sourceData, leftXRange, upYRange, avg2, squareSize)
        val std3: Double = getStandardDeviation(sourceData, rightXRange, downYRange, avg3, squareSize)
        val std4: Double = getStandardDeviation(sourceData, rightXRange, upYRange, avg4, squareSize)

        val resultAvg: Array[Double] = getMinDeviationAverageColor(
          Map(std1 -> avg1Color, std2 -> avg2Color, std3 -> avg3Color, std4 -> avg4Color))
        createResult(out, i, j, resultAvg)
      }
    }
  }

  private def createResult(out: BufferedImage, i: Int, j: Int, resultAvg: Array[Double]): Unit = {
    val result = Try(out.setRGB(i - squareSize, j - squareSize, new Color(resultAvg(0).toInt, resultAvg(1).toInt, resultAvg(2).toInt).getRGB))
    result match {
      case Failure(exception) =>
        println("Failure in finding RGB deviation".concat(exception.getMessage))
        println(i, j)
      case Success(value) => value
    }
  }

  def refreshTotals(total: Array[Double], numberOfPoints: Int): Array[Double] = {
    total(0) = total(0) / numberOfPoints
    total(1) = total(1) / numberOfPoints
    total(2) = total(2) / numberOfPoints
    total(3) = total(3) / numberOfPoints
    total
  }

  def processSquare(xRange: Range, yRange: Range, sourceData: Raster, arr: Array[Double], total: Array[Double]): Unit = {
    (xRange).foreach(i => {
      (yRange).foreach(j => {
        val result = Try(adds4ChannelValuesToTotalArray(sourceData, arr, total, i, j))
        result match {
          case Failure(exception) => println("Get Average Out of bounds! ", i, j)
            throw exception
          case Success(value) => value
        }
      })
    })
  }

  def getAverage(sourceData: Raster, xRange: Range, yRange: Range, square: Int): Array[Double] = {
    val numberOfPoints: Int = calculateNumberOfPoints(xRange, yRange)
    val arr: Array[Double] = fill[Double](4)(0)
    val total: Array[Double] = fill[Double](4)(0)

    if (validatePoint(sourceData, xRange, yRange, square)) {
      processSquare(xRange, yRange, sourceData, arr, total)
      refreshTotals(total, numberOfPoints)
    } else {
      fill[Double](0)(0.0)
    }
  }

  private def adds4ChannelValuesToTotalArray(sourceData: Raster, arr: Array[Double], total: Array[Double], i: Integer, j: Integer): Unit = {
    sourceData.getPixel(i, j, arr)
    total(0) = total(0) + arr(0)
    total(1) = total(1) + arr(1)
    total(2) = total(2) + arr(2)
    total(3) = total(3) + arr(3)
  }

  def getAverageGrey(sourceData: Raster, xRange: Range, yRange: Range, square: Int): Double = {
    val numberOfPoints: Int = calculateNumberOfPoints(xRange, yRange)
    val arr = fill[Int](4)(0)

    if (validatePoint(sourceData, xRange, yRange, square)) {
      xRange.map(i => {
        yRange.map(j => {
          val hsvResult: Try[Double] = Try(calculateHsvValueFromSourceDataPositions(sourceData, arr, i, j))
          hsvResult match {


            case Failure(exception) => println("Get Average Grey Out of bounds! ", i, j)
              throw exception
            case Success(hsv) => hsv
          }
        }).sum
      }).sum / numberOfPoints
    } else {
      Double.NaN
    }
  }

  private def calculateHsvValueFromSourceDataPositions(sourceData: Raster, arr: Array[Int], i: Int, j: Int): Float = {
    sourceData.getPixel(i, j, arr)
    val hsv = fill[Float](4)(0)
    Color.RGBtoHSB(arr(0), arr(1), arr(2), hsv)
    hsv(3)
  }

  def validatePoint(source: Raster, xRange: Range, yRange: Range, square: Int): Boolean = {
    val minX = xRange.min
    val maxX = xRange.max
    val minY = yRange.min
    val maxY = yRange.max
    minX >= 0 && maxX < source.getWidth && minY >= 0 && maxY < source.getHeight && maxX - minX + 1 == square && maxY - minY + 1 == square
  }

  def getStandardDeviation(sourceData: Raster, xRange: Range, yRange: Range, avg: Double, square: Int): Double = {
    val numberOfPoints: Int = calculateNumberOfPoints(xRange, yRange)
    val arr = fill[Int](4)(0)
    if (validatePoint(sourceData, xRange, yRange, square)) {
      iterateRangeAndCalculateSum(xRange, yRange, sourceData, avg, arr) / numberOfPoints
    }
    else {
      Double.NaN
    }
  }

  private def iterateRangeAndCalculateSum(xRange: Range, yRange: Range, sourceData: Raster, avg: Double, arr: Array[Int]): Double = {
    (xRange).map(i => {
      (yRange).map(j => {
        val result = Try(calculateStdParticle(sourceData, avg, arr, i, j))
        result match {
          case Success(value) => value
          case Failure(exception) => println("Get Standard Deviation Out of bounds! ", i, j)
            throw exception
        }
      }).sum
    }).sum
  }

  private def calculateNumberOfPoints(xRange: Range, yRange: Range): Int = {
    (yRange.max - yRange.min + 1) * (xRange.max - xRange.min + 1)
  }

  private def calculateStdParticle(sourceData: Raster, avg: Double, arr: Array[Int], i: Int, j: Int): Double = {
    sourceData.getPixel(i, j, arr)
    val hsv = fill[Float](4)(0)
    Color.RGBtoHSB(arr(0), arr(1), arr(2), hsv)
    math.pow(hsv(3) - avg, 2)
  }

  def getMinDeviationAverageColor(stdAverages: Map[Double, Array[Double]]): Array[Double] = {
    val minValue: Double = stdAverages.keys.min
    stdAverages.get(minValue).orNull
  }

  def getRealMinValue(std1: Double, std2: Double): Double = {
    std1 match {
      case x if x.isNaN => std2
      case _ =>
        std2 match {
          case y if y.isNaN => std1
          case _ => math.min(std1, std2)
        }
    }
  }
}
