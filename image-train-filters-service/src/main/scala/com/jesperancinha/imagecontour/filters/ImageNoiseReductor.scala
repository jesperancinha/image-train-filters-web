package com.jesperancinha.imagecontour.filters

import java.awt.image.BufferedImage

class ImageNoiseReductor(bgColor: Int, lnColor: Int, diffThreshold: Double, radius: Int, source: BufferedImage) extends ImageFilter[BufferedImage] {
  override def apply(): BufferedImage = {
    throw new NotImplementedError("Noise reductor not implemented yet!")
  }
}
