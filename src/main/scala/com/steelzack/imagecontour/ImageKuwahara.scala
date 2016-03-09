package com.steelzack.imagecontour

import java.awt.image.BufferedImage

/**
  * Created by joaofilipesabinoesperancinha on 09-03-16.
  */
object ImageKuwahara {

  def getAverage(source: BufferedImage, i: Int, i1: Int, i2: Int, j: Int) : Int = {
    return 0
  }

  def getStandardDeviation(source: BufferedImage, i: Int, i1: Int, i2: Int, j: Int, avg2: Int) : Int = {
    return 0
  }

  def convertAndSaveImage(source: BufferedImage, //
                          squareSize: Int
                         ) {
    val w: Int = source.getWidth
    val h: Int = source.getHeight
    val arr = Array.fill[Double](3)(0.0)
    val out = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB)


    for (i <- 1 to w - 1) {
      for (j <- 1 to h - 1) {
        if (i < squareSize || j < squareSize || i > h - squareSize || j > w - squareSize) {
            out.setRGB(i,j,source.getRGB(i,j))
        } else {
            val avg1 = getAverage(source, i-squareSize, j-squareSize, i, j)
            val avg2 = getAverage(source, i-squareSize, j+squareSize, i, j)
            val avg3 = getAverage(source, i+squareSize, j-squareSize, i, j)
            val avg4 = getAverage(source, i+squareSize, j+squareSize, i, j)

            val std1 = getStandardDeviation(source, i-squareSize, j-squareSize, i, j, avg1)
            val std2 = getStandardDeviation(source, i-squareSize, j+squareSize, i, j, avg2)
            val std3 = getStandardDeviation(source, i+squareSize, j-squareSize, i, j, avg3)
            val std4 = getStandardDeviation(source, i+squareSize, j+squareSize, i, j, avg4)
        }
      }
    }
  }
}
