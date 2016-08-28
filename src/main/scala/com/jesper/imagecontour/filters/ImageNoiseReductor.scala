package com.jesper.imagecontour.filters

import java.awt.image.BufferedImage

/**
  * Created by joao on 14-3-16.
  */
class ImageNoiseReductor(bgColor: Int, lnColor: Int, diffThreshold: Double, radius: Int) extends ImageFilter[BufferedImage, BufferedImage] {
  override def apply(source: BufferedImage): BufferedImage = {
    //TODO Please implement the noise reductor algorithm
    null
  }
}
