package com.jesper.imagecontour.filters

import java.awt.image.BufferedImage
import java.io.{ByteArrayInputStream, File}
import java.nio.file.{Files, Paths}
import javax.imageio.ImageIO

/**
  * Created by joaofilipesabinoesperancinha on 09-03-16.
  */
object ImageManager extends App {

  override def main(args: Array[String]) {
    val imageManager = new ImageContour(0xFFFFFF, 0x000000, 800000, 2)
    if (args.length == 1) {
      val fileName: String = args(0)
      val fileBytes = Files.readAllBytes(Paths.get(fileName))
      val byteStream: java.io.InputStream = new ByteArrayInputStream(fileBytes)
      val bImageFromConvert: BufferedImage = ImageIO.read(byteStream);
      byteStream.close()
      val out = imageManager.apply(bImageFromConvert)
      ImageIO.write(out, "jpg", new File("/tmp/copy.jpg"))
    }
  }
}
