package com.jesperancinha.imagecontour.filters.chartizate

import java.awt.Color
import java.awt.image.BufferedImage
import java.io.{ByteArrayInputStream, ByteArrayOutputStream}
import java.lang.Character.UnicodeBlock

import com.jesperancinha.imagecontour.filters.ImageFilter
import javax.imageio.ImageIO
import org.jesperancinha.chartizate.ChartizateManagerBuilderImpl
import org.jesperancinha.chartizate.distributions.ChartizateDistributionType.Linear

class ImageChartizate(bgColor: Int, densityPro: Int, rangePro: Int, font: String, fontSize: Int, unicode: String, bufferedImage: BufferedImage) extends ImageFilter[BufferedImage] {
  override def apply(): BufferedImage = {
    val os = new ByteArrayOutputStream
    ImageIO.write(bufferedImage, "png", os)
    val imageInputStream = new ByteArrayInputStream(os.toByteArray)
    val manager = new ChartizateManagerBuilderImpl()
      .backgroundColor(new Color(bgColor))
      .densityPercentage(densityPro)
      .rangePercentage(rangePro)
      .distributionType(Linear)
      .fontName(font)
      .fontSize(fontSize)
      .block(UnicodeBlock.forName(unicode))
      .imageFullStream(imageInputStream)
      .build();
    manager.generateConvertedImageStream()
  }
}
