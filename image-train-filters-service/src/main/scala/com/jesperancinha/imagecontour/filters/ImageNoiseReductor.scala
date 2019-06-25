package com.jesperancinha.imagecontour.filters

import java.awt.image.BufferedImage

class ImageNoiseReductor(bgColor: Int, lnColor: Int, diffThreshold: Double, radius: Int) extends ImageFilter[BufferedImage, BufferedImage] {
  override def apply(source: BufferedImage): BufferedImage = {
    throw new NotImplementedError("Noise reductor not implemented yet!")
  }
}
