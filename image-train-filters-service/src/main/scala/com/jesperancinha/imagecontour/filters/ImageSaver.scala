package com.jesperancinha.imagecontour.filters

import java.awt.image.BufferedImage
import java.io.File

import javax.imageio.ImageIO

object ImageSaver {

  def copyBufferedImage(bufferedImage: BufferedImage, destinationFile: File): Unit = {
    ImageIO.write(bufferedImage, "png", destinationFile)
  }
}
