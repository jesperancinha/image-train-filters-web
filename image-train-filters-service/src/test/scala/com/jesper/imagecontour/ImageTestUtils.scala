package com.jesper.imagecontour

import java.awt.image.BufferedImage
import java.io.{ByteArrayInputStream, InputStream}
import java.nio.file.{Files, Paths}

import javax.imageio.ImageIO

object ImageTestUtils {

  def getBufferedResource(resourcePath: String): BufferedImage = {
    val fileBytes = Files.readAllBytes(Paths.get(getClass.getResource(resourcePath).toURI))
    val byteStream: InputStream = new ByteArrayInputStream(fileBytes)
    val sourceImage: BufferedImage = ImageIO.read(byteStream)
    byteStream.close()
    sourceImage
  }
}
