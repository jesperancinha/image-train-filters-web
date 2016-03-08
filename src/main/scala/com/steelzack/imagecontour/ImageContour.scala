package com.steelzack.imagecontour

import java.awt.image.BufferedImage
import java.io.{File, ByteArrayInputStream}
import java.nio.file.{Paths, Files}
import javax.imageio.ImageIO

import scala.io.BufferedSource

/**
  * Created by joaofilipesabinoesperancinha on 03-03-16.
  */
object ImageContour extends App {

  override def main(args: Array[String]) {
    val fileName: String = args(0)
    val fileBytes = Files.readAllBytes(Paths.get(fileName))
    val byteStream : java.io.InputStream = new ByteArrayInputStream(fileBytes)
    val bImageFromConvert :  BufferedImage = ImageIO.read(byteStream);
    byteStream.close
    printCharsToConsole(bImageFromConvert)
  }


  def printCharsToConsole(source: BufferedImage) {
    val w: Int = source.getWidth
    val h: Int = source.getHeight
    val arr = Array.fill[Double](3)(0.0)
    var out = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB)

    var i = 0
    var j = 0
    for (i <- 1 to w - 1) {
      for (j <- 1 to h - 1) {
        //source.getData().getPixel(i, j, arr)
        //print("(" + i + "," + j + ")")
        out.setRGB(i,j,source.getRGB(i,j))
      }
      print("\n")
    }
  ImageIO.write(out, "jpg", new File("/tmp/copy.jpg"))
  }

  def apply(): Unit = {

  }
}
