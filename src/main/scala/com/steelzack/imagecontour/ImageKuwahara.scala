package com.steelzack.imagecontour

import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

import sun.plugin2.util.ColorUtil.ColorRGB

import scala.util.control.Exception

/**
  * Created by joaofilipesabinoesperancinha on 09-03-16.
  */
object ImageKuwahara {

  def getAverage(source: BufferedImage, x1: Int, y1: Int, x2: Int, y2: Int): Array[Double] = {
    var numberOfPoints: Int = (y2 - y1 + 1) * (x2 - x1 + 1)
    val arr: Array[Double] = Array.fill[Double](4)(0)
    val total: Array[Double] = Array.fill[Double](4)(0)

    for (i <- x1 to x2) {
      for (j <- y1 to y2) {
        if (validatePoint(source, x1, y1, x2, y2)) {
          try {
            source.getData().getPixel(i, j, arr)
            total(0) = total(0) + arr(0)
            total(1) = total(1) + arr(1)
            total(2) = total(2) + arr(2)
            total(3) = total(3) + arr(3)
          } catch {
            case e: Exception => println("Get Average Out of bounds! ", i, j)
          }
        } else {
          numberOfPoints -= 1
        }
      }
    }

    total(0) = total(0) / numberOfPoints
    total(1) = total(1) / numberOfPoints
    total(2) = total(2) / numberOfPoints
    total(3) = total(3) / numberOfPoints

    total
  }

  def validatePoint(source: BufferedImage, x1: Int, y1: Int, x2: Int, y2: Int): Boolean = {
    x1 >= 0 && x2 < source.getWidth() && y1 >= 0 && y2 < source.getHeight()
  }

  def getAverageGrey(source: BufferedImage, x1: Int, y1: Int, x2: Int, y2: Int): Double = {
    var numberOfPoints: Int = (y2 - y1 + 1) * (x2 - x1 + 1)
    val arr = Array.fill[Int](4)(0)
    var total: Double = 0

    for (i <- x1 to x2) {
      for (j <- y1 to y2) {
        if (validatePoint(source, x1, y1, x2, y2)) {
          try {
            source.getData().getPixel(i, j, arr)
            val hsv = Array.fill[Float](4)(0)
            Color.RGBtoHSB(arr(0), arr(1), arr(2), hsv)
            total += hsv(3)

          }
          catch {
            case e: Exception => println("Get Average Grey Out of bounds! ", i, j)
          }
        } else {
          numberOfPoints -= 1
        }
      }
    }

    total / numberOfPoints
  }

  def getStandardDeviation(source: BufferedImage, x1: Int, y1: Int, x2: Int, y2: Int, avg: Double): Double = {
    var numberOfPoints: Int = (y2 - y1 + 1) * (x2 - x1 + 1)
    val arr = Array.fill[Int](4)(0)
    var total: Double = 0.0

    for (i <- x1 to x2) {
      for (j <- y1 to y2) {
        if (validatePoint(source, x1, y1, x2, y2)) {
          try {
            source.getData().getPixel(i, j, arr)
            val hsv = Array.fill[Float](4)(0)
            Color.RGBtoHSB(arr(0), arr(1), arr(2), hsv)
            total += math.pow(hsv(3) - avg, 2)
          }
          catch {
            case e: Exception => println("Get Standard Deviation Out of bounds! ", i, j)
          }
        } else {
          numberOfPoints -= 1
        }
      }
    }

    total = total / numberOfPoints
    total
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

  def getMinValue(std1: Double, std2: Double, std3: Double, std4: Double): Double = {

    var currentMin = getRealMinValue(std1, std2)
    currentMin = getRealMinValue(currentMin, std3)
    currentMin = getRealMinValue(currentMin, std4)
    currentMin
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
    }
  }

  def convertAndSaveImage(source: BufferedImage, squareSize: Int, iterations: Int = 1) {
    val w: Int = source.getWidth
    val h: Int = source.getHeight
    var out: BufferedImage = null
    var srcOut = source
    for (i <- 0 to iterations) {
      out = new BufferedImage(w, h, BufferedImage.TYPE_INT_BGR)
      performIteration(srcOut, squareSize, w, h, out)
      srcOut = out;
    }
    ImageIO.write(out, "jpg", new File("/tmp/copy.jpg"))
  }

  def performIteration(source: BufferedImage, squareSize: Int, w: Int, h: Int, out: BufferedImage): Unit = {
    for (i <- 0 to w - 1) {
      for (j <- 0 to h - 1) {
        val avg1: Double = getAverageGrey(source, i - squareSize, j - squareSize, i - 1, j - 1)
        val avg2: Double = getAverageGrey(source, i - squareSize, j + 1, i - 1, j + squareSize)
        val avg3: Double = getAverageGrey(source, i + 1, j - squareSize, i + squareSize, j - 1)
        val avg4: Double = getAverageGrey(source, i + 1, j + 1, i + squareSize, j + squareSize)

        val avg1Color: Array[Double] = getAverage(source, i - squareSize, j - squareSize, i - 1, j - 1)
        val avg2Color: Array[Double] = getAverage(source, i - squareSize, j + 1, i - 1, j + squareSize)
        val avg3Color: Array[Double] = getAverage(source, i + 1, j - squareSize, i + squareSize, j - 1)
        val avg4Color: Array[Double] = getAverage(source, i + 1, j + 1, i + squareSize, j + squareSize)

        val std1: Double = getStandardDeviation(source, i - squareSize, j - squareSize, i - 1, j - 1, avg1)
        val std2: Double = getStandardDeviation(source, i - squareSize, j + 1, i - 1, j + squareSize, avg2)
        val std3: Double = getStandardDeviation(source, i + 1, j - squareSize, i + squareSize, j - 1, avg3)
        val std4: Double = getStandardDeviation(source, i + 1, j + 1, i + squareSize, j + squareSize, avg4)

        val resultAvg: Array[Double] = getMinDeviationAverageColor(avg1Color, avg2Color, avg3Color, avg4Color, std1, std2, std3, std4)

        out.setRGB(i, j, new Color(resultAvg(0).toInt, resultAvg(1).toInt, resultAvg(2).toInt).getRGB)
      }
    }
  }

  def apply(): Unit = {

  }

}
