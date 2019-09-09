package com.jesperancinha.imagecontour.filters

import java.awt.Color
import java.awt.image.BufferedImage
import java.io.{ByteArrayInputStream, ByteArrayOutputStream}
import java.lang.Character.UnicodeBlock

import javax.imageio.ImageIO
import org.jesperancinha.chartizate.ChartizateManagerImpl
import org.jesperancinha.chartizate.distributions.ChartizateDistributionType.Linear

class ImageChartizate(bgColor: Int, densityPro: Int, rangePro: Int, font: String, fontSize: Int, unicode: String) extends ImageFilter[BufferedImage, BufferedImage] {
  override def apply(bufferedImage: BufferedImage): BufferedImage = {
    val os = new ByteArrayOutputStream
    ImageIO.write(bufferedImage, "png", os)
    val imageInputStream = new ByteArrayInputStream(os.toByteArray)
    val manager = new ChartizateManagerImpl(new Color(bgColor), densityPro, rangePro, Linear, font, fontSize, UnicodeBlock.forName(unicode), imageInputStream)
    manager.generateConvertedImageStream()
  }
}
