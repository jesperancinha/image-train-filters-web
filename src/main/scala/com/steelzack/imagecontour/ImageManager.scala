package com.steelzack.imagecontour

import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream
import java.nio.file.{Paths, Files}
import javax.imageio.ImageIO

/**
  * Created by joaofilipesabinoesperancinha on 09-03-16.
  */
object ImageManager extends App{

  override def main(args: Array[String]) {
    val imageManager = ImageContour
    val fileName: String = args(0)
    val fileBytes = Files.readAllBytes(Paths.get(fileName))
    val byteStream: java.io.InputStream = new ByteArrayInputStream(fileBytes)
    val bImageFromConvert: BufferedImage = ImageIO.read(byteStream);
    byteStream.close
    imageManager.apply(bImageFromConvert, 0x0000, 0xFFFF, 500000, 5)
    imageManager.applyFilter
  }
}
