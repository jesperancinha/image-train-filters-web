package com.steelzack.imagecontour

import java.awt.image.BufferedImage

/**
  * Created by joao on 14-3-16.
  */
trait ImageFilter {
  def applyFilter: BufferedImage
}
