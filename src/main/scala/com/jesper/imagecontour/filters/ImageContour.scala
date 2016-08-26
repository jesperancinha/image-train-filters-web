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
          var drawColor = bgColor

          if (colorTop < nextHColor || colorTop < prevHColor) {
            drawColor = lnColor
          } else if (colorBottom > nextHColor || colorBottom > prevHColor) {
            drawColor = lnColor
          } else if (colorTop < nextVColor || colorTop < prevVColor) {
            drawColor = lnColor
          } else if (colorBottom > nextVColor || colorBottom > prevVColor) {
            drawColor = lnColor
          }

          out.setRGB(i, j, drawColor)
        }
      }
    }
    out
  }
}

