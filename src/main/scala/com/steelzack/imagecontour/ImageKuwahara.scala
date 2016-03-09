package com.steelzack.imagecontour

import java.awt.image.BufferedImage

/**
  * Created by joaofilipesabinoesperancinha on 09-03-16.
  */
object ImageKuwahara {

  def convertAndSaveImage(source: BufferedImage //
                         ) {
    val w: Int = source.getWidth
    val h: Int = source.getHeight
    val arr = Array.fill[Double](3)(0.0)
    val out = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB)

    for( i <- 1 to w-1) {
      for (j <- 1 to h -1){

      }
    }
  }
}
