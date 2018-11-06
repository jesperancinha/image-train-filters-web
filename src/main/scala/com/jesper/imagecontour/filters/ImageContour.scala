package com.jesper.imagecontour.filters

import java.awt.image.BufferedImage

/**
  * Created by joaofilipesabinoesperancinha on 03-03-16.
  */


class ImageContour(bgColor: Int, lnColor: Int, diffThreshold: Double, radius: Int) extends ImageFilter[BufferedImage, BufferedImage] {
  def apply(source: BufferedImage): BufferedImage = {
    val w: Int = source.getWidth
    val h: Int = source.getHeight
    val out = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB)

    for (i <- 1 until w) {
      for (j <- 1 until h) {
        if (i + radius >= w || j + radius >= h || i <= radius || j <= radius) {
          out.setRGB(i, j, bgColor)
        } else {
          val currentColor: Double = source.getRGB(i, j)
          val colorTop: Double = currentColor + diffThreshold
          val colorBottom: Double = currentColor - diffThreshold
          val nextHColor: Double = source.getRGB(i + radius, j)
          val nextVColor: Double = source.getRGB(i, j + radius)
          val prevHColor: Double = source.getRGB(i - radius, j)
          val prevVColor: Double = source.getRGB(i, j - radius)
          val drawColor: Int = calculateDrawColor(colorTop, colorBottom, nextHColor, nextVColor, prevHColor, prevVColor)

          out.setRGB(i, j, drawColor)
        }
      }
    }
    out
  }

  private def calculateDrawColor(colorTop: Double, colorBottom: Double, nextHColor: Double, nextVColor: Double, prevHColor: Double, prevVColor: Double) = {
    if (colorTop < nextHColor || colorTop < prevHColor) {
      lnColor
    } else if (colorBottom > nextHColor || colorBottom > prevHColor) {
      lnColor
    } else if (colorTop < nextVColor || colorTop < prevVColor) {
      lnColor
    } else if (colorBottom > nextVColor || colorBottom > prevVColor) {
      lnColor
    } else
      bgColor
  }
}

