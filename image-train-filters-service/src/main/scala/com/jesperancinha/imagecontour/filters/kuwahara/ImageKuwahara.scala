package com.jesperancinha.imagecontour.filters.kuwahara

import java.awt.Color
import java.awt.image.{BufferedImage, Raster}

import com.jesperancinha.imagecontour.filters.ImageFilter

import scala.Array.fill
import scala.util.{Failure, Success, Try}

class ImageKuwahara(val squareSize: Int, val iterations: Int, bufferedImage: BufferedImage) extends ImageFilter[BufferedImage] {
  val sourceData: Raster = bufferedImage.getData()
  val w: Int = sourceData.getWidth
  val h: Int = sourceData.getHeight

  def apply(): BufferedImage = {
    val imageKuwaharaProperties: ImageKuwaharaProperties = new ImageKuwaharaProperties(sourceData, w, h);
    (0 to iterations).map(_ => {
      val out = new BufferedImage(w - squareSize, h - squareSize, BufferedImage.TYPE_INT_RGB)
      performIteration(imageKuwaharaProperties, out)
      out.getRaster
      out
    }).lastOption.orNull
  }

  def performIteration(imageKuwaharaProperties: ImageKuwaharaProperties, out: BufferedImage): Unit = {
    val w = imageKuwaharaProperties.w
    val h = imageKuwaharaProperties.h
    for (i <- squareSize until w - squareSize by 1) {
      for (j <- squareSize until h - squareSize by 1) {
        processOutPixel(out, i, j)
      }
    }
  }

  private def processOutPixel(out: BufferedImage, x: Int, y: Int): Unit = {
    val leftXRange = x - squareSize until x
    val downYRange = y - squareSize until y
    val upYRange = y + 1 to y + squareSize
    val rightXRange = x + 1 to x + squareSize
    val std1: Double = getStandardDeviation(leftXRange, downYRange, getAverageGrey(leftXRange, downYRange))
    val std2: Double = getStandardDeviation(leftXRange, upYRange, getAverageGrey(leftXRange, upYRange))
    val std3: Double = getStandardDeviation(rightXRange, downYRange, getAverageGrey(rightXRange, downYRange))
    val std4: Double = getStandardDeviation(rightXRange, upYRange, getAverageGrey(rightXRange, upYRange))
    val resultAvg: Array[Double] = getMinDeviationAverageColor(
      Map(
        std1 -> getAverage(leftXRange, downYRange),
        std2 -> getAverage(leftXRange, upYRange),
        std3 -> getAverage(rightXRange, downYRange),
        std4 -> getAverage(rightXRange, upYRange))
    )
    createResult(out, x, y, resultAvg)
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

  def getAverage(xRange: Range, yRange: Range): Array[Double] = {
    val numberOfPoints: Int = calculateNumberOfPoints(xRange, yRange)
    val arr: Array[Double] = fill[Double](4)(0)
    val total: Array[Double] = fill[Double](4)(0)

    if (validatePoint(xRange, yRange)) {
      processSquare(xRange, yRange, arr, total)
      refreshTotals(total, numberOfPoints)
    } else {
      fill[Double](0)(0.0)
    }
  }

  def refreshTotals(total: Array[Double], numberOfPoints: Int): Array[Double] = {
    total(0) = total(0) / numberOfPoints
    total(1) = total(1) / numberOfPoints
    total(2) = total(2) / numberOfPoints
    total(3) = total(3) / numberOfPoints
    total
  }

  def processSquare(xRange: Range, yRange: Range, arr: Array[Double], total: Array[Double]): Unit = {
    xRange.foreach(i => {
      yRange.foreach(j => {
        val result = Try(adds4ChannelValuesToTotalArray(arr, total, i, j))
        result match {
          case Failure(exception) => println("Get Average Out of bounds! ", i, j)
            throw exception
          case Success(value) => value
        }
      })
    })
  }

  private def adds4ChannelValuesToTotalArray(arr: Array[Double], total: Array[Double], i: Integer, j: Integer): Unit = {
    sourceData.getPixel(i, j, arr)
    total(0) = total(0) + arr(0)
    total(1) = total(1) + arr(1)
    total(2) = total(2) + arr(2)
    total(3) = total(3) + arr(3)
  }

  def validatePoint(xRange: Range, yRange: Range): Boolean = {
    val minX = xRange.min
    val maxX = xRange.max
    val minY = yRange.min
    val maxY = yRange.max
    minX >= 0 && maxX < sourceData.getWidth && minY >= 0 && maxY < sourceData.getHeight && maxX - minX + 1 == squareSize && maxY - minY + 1 == squareSize
  }

  private def calculateNumberOfPoints(xRange: Range, yRange: Range): Int = {
    (yRange.max - yRange.min + 1) * (xRange.max - xRange.min + 1)
  }

  def getAverageGrey(xRange: Range, yRange: Range): Double = {
    val numberOfPoints: Int = calculateNumberOfPoints(xRange, yRange)
    val arr = fill[Int](4)(0)

    if (validatePoint(xRange, yRange)) {
      xRange.map(i => {
        yRange.map(j => {
          val hsvResult: Try[Double] = Try(calculateHsvValueFromSourceDataPositions(arr, i, j))
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

  private def calculateHsvValueFromSourceDataPositions(arr: Array[Int], i: Int, j: Int): Float = {
    sourceData.getPixel(i, j, arr)
    val hsv = fill[Float](4)(0)
    Color.RGBtoHSB(arr(0), arr(1), arr(2), hsv)
    hsv(3)
  }

  def getStandardDeviation(xRange: Range, yRange: Range, avg: Double): Double = {
    val numberOfPoints: Int = calculateNumberOfPoints(xRange, yRange)
    val arr = fill[Int](4)(0)
    if (validatePoint(xRange, yRange)) {
      iterateRangeAndCalculateSum(xRange, yRange, avg, arr) / numberOfPoints
    }
    else {
      Double.NaN
    }
  }

  private def iterateRangeAndCalculateSum(xRange: Range, yRange: Range, avg: Double, arr: Array[Int]) = {
    xRange.map(i => {
      yRange.map(j => {
        val result = Try(calculateStdParticle(avg, arr, i, j))
        result match {
          case Success(value) => value
          case Failure(exception) => println("Get Standard Deviation Out of bounds! ", i, j)
            throw exception
        }
      }).sum
    }).sum
  }

  private def calculateStdParticle(avg: Double, arr: Array[Int], i: Int, j: Int) = {
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
