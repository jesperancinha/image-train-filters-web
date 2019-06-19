package com.jesperancinha.imagecontour.filters

import java.awt.image.BufferedImage
import java.io.{ByteArrayInputStream, File}
import java.nio.file.{Files, Paths}

import javax.imageio.ImageIO

/**
  * Created by joaofilipesabinoesperancinha on 09-03-16.
  * Utility Class
  */
object ImageManager {

  def getBufferedImage(file: File): BufferedImage = {
    val fileBytes = Files.readAllBytes(Paths.get(file.toURI))
    val byteStream: java.io.InputStream = new ByteArrayInputStream(fileBytes)
    val bImageFromConvert: BufferedImage = ImageIO.read(byteStream);
    byteStream.close()
    bImageFromConvert
  }
}
