package com.jesperancinha.imagecontour.filters.contour

import java.awt.image.BufferedImage

import com.jesperancinha.imagecontour.filters.ImageFilter

class ImageContour(imageContourConfig: ImageContourConfig, bufferedImage: BufferedImage) extends ImageFilter[BufferedImage] {
  val diffThreshold: Double = imageContourConfig.imageContourThreshold.diffThreshold
  val radius: Int = imageContourConfig.imageContourThreshold.radius
  val lnColor: Int = imageContourConfig.imageContourColors.lnColor
  val bgColor: Int = imageContourConfig.imageContourColors.bgColor

  def apply(): BufferedImage = {
    val w: Int = bufferedImage.getWidth
    val h: Int = bufferedImage.getHeight
    val out = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB)
    for (i <- 1 until w) {
      for (j <- 1 until h) {
        if (i + radius >= w || j + radius >= h || i <= radius || j <= radius) {
          out.setRGB(i, j, bgColor)
        } else {
          val drawColor: Int = findEdge(i, j)
          out.setRGB(i, j, drawColor)
        }
      }
    }
    out
  }

  private def findEdge(x: Int, y: Int): Int = {
    val currentColor: Double = bufferedImage.getRGB(x, y)
    val colorTop: Double = currentColor + diffThreshold
    val colorBottom: Double = currentColor - diffThreshold
    val nextHColor: Double = bufferedImage.getRGB(x + radius, y)
    val nextVColor: Double = bufferedImage.getRGB(x, y + radius)
    val prevHColor: Double = bufferedImage.getRGB(x - radius, y)
    val prevVColor: Double = bufferedImage.getRGB(x, y - radius)
    val drawColor: Int = (colorTop, colorBottom) match {
      case (t, _) if t < nextHColor || t < prevHColor => lnColor
      case (_, b) if b > nextHColor || b > prevHColor => lnColor
      case (t, _) if t < nextVColor || t < prevVColor => lnColor
      case (_, b) if b > nextVColor || b > prevVColor => lnColor
      case (_, _) => bgColor
    }
    drawColor
  }

}

