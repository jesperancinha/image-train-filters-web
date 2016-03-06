package com.steelzack.imagecontour

import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream
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
    var i = 0
    for (i <- 1 to source.getWidth) {
      print(i)
    }
  }

  def apply(): Unit = {

  }
}
