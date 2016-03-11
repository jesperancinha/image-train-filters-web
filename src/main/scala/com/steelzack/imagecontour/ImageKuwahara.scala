package com.steelzack.imagecontour

import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

/**
  * Created by joaofilipesabinoesperancinha on 09-03-16.
  */
object ImageKuwahara {

  def getAverage(source: BufferedImage, x1: Int, y1: Int, x2: Int, y2: Int): Double = {
    val numberOfPoints: Int = (y2 - y1) * (x2 - x1)
    var total: Double = 0

    for (i <- x1 to x2) {
      for (j <- y1 to y2) {
        total += source.getRGB(i, j).toDouble;

      }
    }

    total / numberOfPoints
  }

  def getAverageGrey(source: BufferedImage, x1: Int, y1: Int, x2: Int, y2: Int): Double = {
    val numberOfPoints: Int = (y2 - y1) * (x2 - x1)
    val arr = Array.fill[Double](4)(0.0)
    var total: Double = 0

    for (i <- x1 to x2) {
      for (j <- y1 to y2) {
        source.getData().getPixel(i, j, arr)
        val grey: Double = 0.299 * arr(0) + 0.587 * arr(1) + 0.114 * arr(2)
        total += grey
      }
    }

    total / numberOfPoints
  }

  def getStandardDeviation(source: BufferedImage, x1: Int, y1: Int, x2: Int, y2: Int, avg: Double): Double = {
    val numberOfPoints: Int = (y2 - y1) * (x2 - x1)
    val arr = Array.fill[Double](4)(0.0)
    var total: Double = 0.0

    for (i <- x1 to x2) {
      for (j <- y1 to y2) {
        source.getData().getPixel(i, j, arr)
        val grey: Double = 0.299 * arr(0) + 0.587 * arr(1) + 0.114 * arr(2)
        total += math.pow(grey - avg, 2)
      }
    }

    total = total / numberOfPoints
    total
  }

  def getMinDeviationAverageColor(avg1: Double, //
                                  avg2: Double, //
                                  avg3: Double, //
                                  avg4: Double, //
                                  std1: Double, //
                                  std2: Double, //
                                  std3: Double, //
                                  std4: Double //
                                 ): Double = {
    val minValue = math.min(std1, math.min(std2, math.min(std3, std4)))

    minValue match {
      case `std1` => avg1
      case `std2` => avg2
      case `std3` => avg3
      case `std4` => avg4
    }
  }

  def convertAndSaveImage(source: BufferedImage, //
                          squareSize: Int
                         ) {
    val w: Int = source.getWidth
    val h: Int = source.getHeight
    val out = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB)


    for (i <- 0 to w - 1) {
      for (j <- 0 to h - 1) {
        if (i < squareSize  || j < squareSize || i > w - squareSize || j > h - squareSize) {
          out.setRGB(i, j, source.getRGB(i, j))
        } else {
          val avg1: Double = getAverageGrey(source, i - squareSize, j - squareSize, i, j)
          val avg2: Double = getAverageGrey(source, i - squareSize, j + squareSize, i, j)
          val avg3: Double = getAverageGrey(source, i + squareSize, j - squareSize, i, j)
          val avg4: Double = getAverageGrey(source, i + squareSize, j + squareSize, i, j)

          val avg1Color: Double = getAverage(source, i - squareSize, j - squareSize, i, j)
          val avg2Color: Double = getAverage(source, i - squareSize, j + squareSize, i, j)
          val avg3Color: Double = getAverage(source, i + squareSize, j - squareSize, i, j)
          val avg4Color: Double = getAverage(source, i + squareSize, j + squareSize, i, j)

          val std1: Double = getStandardDeviation(source, i - squareSize, j - squareSize, i, j, avg1)
          val std2: Double = getStandardDeviation(source, i - squareSize, j + squareSize, i, j, avg2)
          val std3: Double = getStandardDeviation(source, i + squareSize, j - squareSize, i, j, avg3)
          val std4: Double = getStandardDeviation(source, i + squareSize, j + squareSize, i, j, avg4)

          val resultAvg: Double = getMinDeviationAverageColor(avg1Color, avg2Color, avg3Color, avg4Color, std1, std2, std3, std4)

          out.setRGB(i, j, resultAvg.toInt)

        }
      }
    }
    ImageIO.write(out, "jpg", new File("/tmp/copy.jpg"))
  }

  def apply(): Unit = {

  }

}
