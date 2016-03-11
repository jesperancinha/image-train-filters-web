package com.steelzack.imagecontour

import java.awt.image.BufferedImage

/**
  * Created by joaofilipesabinoesperancinha on 09-03-16.
  */
object ImageKuwahara {

  def getAverage(source: BufferedImage, x1: Int, y1: Int, x2: Int, y2: Int): Array[Double] = {
    val numberOfPoints: Int = (y2 - y1) * (x2 - x1)
    val arr = Array.fill[Double](3)(0.0)
    val total = Array.fill[Double](3)(0.0)

    for (i <- y1 to y2) {
      for (j <- x1 to x2) {
        source.getData().getPixel(i, j, arr)
        total(0) = total(0) + arr(0)
        total(1) = total(1) + arr(1)
        total(2) = total(2) + arr(2)
      }
    }

    total(0) = total(0) / numberOfPoints
    total(1) = total(1) / numberOfPoints
    total(2) = total(2) / numberOfPoints

    total
  }

  def getStandardDeviation(source: BufferedImage, x1: Int, y1: Int, x2: Int, y2: Int, avg: Array[Double]): Double = {
    val numberOfPoints: Int = (y2 - y1) * (x2 - x1)
    val arr = Array.fill[Double](3)(0.0)
    var total: Double = 0.0

    for (i <- y1 to y2) {
      for (j <- x1 to x2) {
        source.getData().getPixel(i, j, arr)
        val grey: Double = 0.299 * arr(0) + 0.587 * arr(1) + 0.114 * arr(2)
        total += grey
      }
    }

    total /= numberOfPoints

    return total
  }

  def getMinDeviationAverageColor(avg1: Array[Double], //
                                  avg2: Array[Double], //
                                  avg3: Array[Double], //
                                  avg4: Array[Double], //
                                  std1: Double, //
                                  std2: Double, //
                                  std3: Double, //
                                  std4: Double //
                                 ) : Array[Double] = {

    val minValue =  math.min(std1, math.min(std2, math.min(std3, std4)))

    



  return null
  }

  def convertAndSaveImage(source: BufferedImage, //
                          squareSize: Int
                         ) {
    val w: Int = source.getWidth
    val h: Int = source.getHeight
    val out = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB)


    for (i <- 1 to w - 1) {
      for (j <- 1 to h - 1) {
        if (i < squareSize || j < squareSize || i > h - squareSize || j > w - squareSize) {
          out.setRGB(i, j, source.getRGB(i, j))
        } else {
          val avg1: Array[Double] = getAverage(source, i - squareSize, j - squareSize, i, j)
          val avg2: Array[Double] = getAverage(source, i - squareSize, j + squareSize, i, j)
          val avg3: Array[Double] = getAverage(source, i + squareSize, j - squareSize, i, j)
          val avg4: Array[Double] = getAverage(source, i + squareSize, j + squareSize, i, j)

          val std1 = getStandardDeviation(source, i - squareSize, j - squareSize, i, j, avg1)
          val std2 = getStandardDeviation(source, i - squareSize, j + squareSize, i, j, avg2)
          val std3 = getStandardDeviation(source, i + squareSize, j - squareSize, i, j, avg3)
          val std4 = getStandardDeviation(source, i + squareSize, j + squareSize, i, j, avg4)

          getMinDeviationAverageColor(avg1,avg2,avg3,avg4,std1,std2,std3,std4)
        }
      }
    }
  }
}
