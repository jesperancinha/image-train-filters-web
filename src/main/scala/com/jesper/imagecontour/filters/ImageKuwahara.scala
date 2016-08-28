package com.jesper.imagecontour.filters

import java.awt.Color
import java.awt.image.{BufferedImage, Raster}

/**
  * Created by joaofilipesabinoesperancinha on 09-03-16.
  */
class ImageKuwahara(squareSize: Int, iterations: Int) extends ImageFilter[BufferedImage, BufferedImage] {
  def apply(source: BufferedImage): BufferedImage = {
     var out: BufferedImage = null
      var srcOut: Raster = source.getData()
      val w: Int = srcOut.getWidth
      val h: Int = srcOut.getHeight
      for (i <- 0 to iterations) {
        out = new BufferedImage(w - squareSize, h - squareSize, BufferedImage.TYPE_INT_RGB)
        performIteration(srcOut, squareSize, w, h, out)
        srcOut = out.getRaster
      }
      out
  }

  def performIteration(sourceData: Raster, squareSize: Int, w: Int, h: Int, out: BufferedImage): Unit = {
    for (i <- squareSize until w - squareSize by 1) {
      for (j <- squareSize until h - squareSize by 1) {
        val avg1: Double = getAverageGrey(sourceData, i - squareSize, j - squareSize, i - 1, j - 1, squareSize)
        val avg2: Double = getAverageGrey(sourceData, i - squareSize, j + 1, i - 1, j + squareSize, squareSize)
        val avg3: Double = getAverageGrey(sourceData, i + 1, j - squareSize, i + squareSize, j - 1, squareSize)
          val avg4: Double = getAverageGrey(sourceData, i + 1, j + 1, i + squareSize, j + squareSize, squareSize)

          val avg1Color: Array[Double] = getAverage(sourceData, i - squareSize, j - squareSize, i - 1, j - 1, squareSize)
          val avg2Color: Array[Double] = getAverage(sourceData, i - squareSize, j + 1, i - 1, j + squareSize, squareSize)
          val avg3Color: Array[Double] = getAverage(sourceData, i + 1, j - squareSize, i + squareSize, j - 1, squareSize)
          val avg4Color: Array[Double] = getAverage(sourceData, i + 1, j + 1, i + squareSize, j + squareSize, squareSize)

          val std1: Double = getStandardDeviation(sourceData, i - squareSize, j - squareSize, i - 1, j - 1, avg1, squareSize)
          val std2: Double = getStandardDeviation(sourceData, i - squareSize, j + 1, i - 1, j + squareSize, avg2, squareSize)
          val std3: Double = getStandardDeviation(sourceData, i + 1, j - squareSize, i + squareSize, j - 1, avg3, squareSize)
          val std4: Double = getStandardDeviation(sourceData, i + 1, j + 1, i + squareSize, j + squareSize, avg4, squareSize)

          try {
            val resultAvg: Array[Double] = getMinDeviationAverageColor(avg1Color, avg2Color, avg3Color, avg4Color, std1, std2, std3, std4)
            out.setRGB(i - squareSize, j - squareSize, new Color(resultAvg(0).toInt, resultAvg(1).toInt, resultAvg(2).toInt).getRGB)
          } catch {
            case e: Exception => println(i, j)
          }
        }
      }
    }

    def getAverage(sourceData: Raster, x1: Int, y1: Int, x2: Int, y2: Int, square: Int): Array[Double] = {
      val numberOfPoints: Int = (y2 - y1 + 1) * (x2 - x1 + 1)
      val arr: Array[Double] = Array.fill[Double](4)(0)
      val total: Array[Double] = Array.fill[Double](4)(0)

      if (validatePoint(sourceData, x1, y1, x2, y2, square)) {

        for (i <- x1 to x2) {
          for (j <- y1 to y2) {
            try {
              sourceData.getPixel(i, j, arr)
              total(0) = total(0) + arr(0)
              total(1) = total(1) + arr(1)
              total(2) = total(2) + arr(2)
              total(3) = total(3) + arr(3)
            } catch {
              case e: Exception => println("Get Average Out of bounds! ", i, j)
                throw e
            }
          }
        }

        total(0) = total(0) / numberOfPoints
        total(1) = total(1) / numberOfPoints
        total(2) = total(2) / numberOfPoints
        total(3) = total(3) / numberOfPoints

        total
      } else {
        Array.fill[Double](0)(0.0)
      }
    }

    def getAverageGrey(sourceData: Raster, x1: Int, y1: Int, x2: Int, y2: Int, square: Int): Double = {
      val numberOfPoints: Int = (y2 - y1 + 1) * (x2 - x1 + 1)
      val arr = Array.fill[Int](4)(0)
      var total: Double = 0

      if (validatePoint(sourceData, x1, y1, x2, y2, square)) {
        for (i <- x1 to x2) {
          for (j <- y1 to y2) {
            try {
              sourceData.getPixel(i, j, arr)
              val hsv = Array.fill[Float](4)(0)
              Color.RGBtoHSB(arr(0), arr(1), arr(2), hsv)
              total += hsv(3)

            }
            catch {
              case e: Exception => println("Get Average Grey Out of bounds! ", i, j)
                throw e
            }
          }
        }

        total / numberOfPoints
      } else {
        Double.NaN
      }
    }

    def getStandardDeviation(sourceData: Raster, x1: Int, y1: Int, x2: Int, y2: Int, avg: Double, square: Int): Double = {
      val numberOfPoints: Int = (y2 - y1 + 1) * (x2 - x1 + 1)
      val arr = Array.fill[Int](4)(0)
      var total: Double = 0.0

      if (validatePoint(sourceData, x1, y1, x2, y2, square)) {
        for (i <- x1 to x2) {
          for (j <- y1 to y2) {
            try {
              sourceData.getPixel(i, j, arr)
              val hsv = Array.fill[Float](4)(0)
              Color.RGBtoHSB(arr(0), arr(1), arr(2), hsv)
              total += math.pow(hsv(3) - avg, 2)
            }
            catch {
              case e: Exception => println("Get Standard Deviation Out of bounds! ", i, j)
                throw e
            }
          }
        }

        total = total / numberOfPoints
        total
      }
      else {
        Double.NaN
      }
    }

    def validatePoint(source: Raster, x1: Int, y1: Int, x2: Int, y2: Int, square: Int): Boolean = {
      x1 >= 0 && x2 < source.getWidth && y1 >= 0 && y2 < source.getHeight && x2 - x1 + 1 == square && y2 - y1 + 1 == square
    }

    def getMinDeviationAverageColor(avg1: Array[Double], //
                                    avg2: Array[Double], //
                                    avg3: Array[Double], //
                                    avg4: Array[Double], //
                                    std1: Double, //
                                    std2: Double, //
                                    std3: Double, //
                                    std4: Double //
                                   ): Array[Double] = {

      val minValue: Double = getMinValue(std1, std2, std3, std4)
      minValue match {
        case `std1` => avg1
        case `std2` => avg2
        case `std3` => avg3
        case `std4` => avg4
        case x if x.isNaN => avg4
      }
    }

    def getMinValue(std1: Double, std2: Double, std3: Double, std4: Double): Double = {

      var currentMin = getRealMinValue(std1, std2)
      currentMin = getRealMinValue(currentMin, std3)
      currentMin = getRealMinValue(currentMin, std4)
      currentMin
    }

    def getRealMinValue(std1: Double, std2: Double): Double = {
      std1 match {
        case x if x.isNaN => std2
        case default => {
          std2 match {
            case y if y.isNaN => std1
            case default => math.min(std1, std2)
          }
        }
      }
    }
}
