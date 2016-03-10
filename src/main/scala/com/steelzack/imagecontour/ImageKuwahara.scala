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

    return total
  }

  def getStandardDeviation(source: BufferedImage, x1: Int, y1: Int, x2: Int, y2: Int, avg: Int): Array[Double] = {
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
          val avg1 = getAverage(source, i - squareSize, j - squareSize, i, j)
          val avg2 = getAverage(source, i - squareSize, j + squareSize, i, j)
          val avg3 = getAverage(source, i + squareSize, j - squareSize, i, j)
          val avg4 = getAverage(source, i + squareSize, j + squareSize, i, j)

          val std1 = getStandardDeviation(source, i - squareSize, j - squareSize, i, j, avg1)
          val std2 = getStandardDeviation(source, i - squareSize, j + squareSize, i, j, avg2)
          val std3 = getStandardDeviation(source, i + squareSize, j - squareSize, i, j, avg3)
          val std4 = getStandardDeviation(source, i + squareSize, j + squareSize, i, j, avg4)
        }
      }
    }
  }
}
