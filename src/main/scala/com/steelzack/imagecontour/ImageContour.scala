package com.steelzack.imagecontour

import java.awt.image.BufferedImage
import java.io.{BufferedReader, ByteArrayInputStream, File}
import java.nio.file.{Files, Paths}
import javax.imageio.ImageIO

/**
  * Created by joaofilipesabinoesperancinha on 03-03-16.
  */
object ImageContour extends ImageFilter{
   var source: BufferedImage = null
   var bgColor: Int = null.asInstanceOf[Int]
   var lnColor: Int = null.asInstanceOf[Int]
   var diffThreshold: Double = null.asInstanceOf[Double]
   var radius: Int = null.asInstanceOf[Int]

  override  def  applyFilter: BufferedImage ={
    val w: Int = source.getWidth
    val h: Int = source.getHeight
    val out = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB)

    for (i <- 1 to w - 1) {
      for (j <- 1 to h - 1) {
        if (i + radius >= w || j + radius >= h || i <= radius || j <= radius) {
          out.setRGB(i, j, bgColor)
        } else {
          //source.getData().getPixel(i, j, arr)
          //print("(" + i + "," + j + ")")
          val currentColor: Double = source.getRGB(i, j)
          val colorTop: Double = currentColor + diffThreshold
          val colorBottom: Double = currentColor - diffThreshold
          val nextHColor: Double = source.getRGB(i + radius, j)
          val nextVColor: Double = source.getRGB(i, j + radius)
          val prevHColor: Double = source.getRGB(i - radius, j)
          val prevVColor: Double = source.getRGB(i, j - radius)
          var drawColor = bgColor;

          if (colorTop < nextHColor || colorTop < prevHColor) {
            drawColor = lnColor;
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

    ImageIO.write(out, "jpg", new File("/tmp/copy.jpg"))
    out
  }

  def apply() : Unit = {
  }

  def apply(source: BufferedImage, //
            bgColor: Int, //
            lnColor: Int, //
            diffThreshold: Double, //
            radius: Int): Unit = {
    this.source = source
    this.bgColor = bgColor
    this.lnColor = lnColor
    this.diffThreshold = diffThreshold
    this.radius = radius
  }
}
