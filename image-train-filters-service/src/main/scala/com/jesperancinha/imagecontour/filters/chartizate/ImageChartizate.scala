package com.jesperancinha.imagecontour.filters.chartizate

import java.awt.Color
import java.awt.image.BufferedImage
import java.io.{ByteArrayInputStream, ByteArrayOutputStream}
import java.lang.Character.UnicodeBlock

import com.jesperancinha.imagecontour.filters.ImageFilter
import javax.imageio.ImageIO
import org.jesperancinha.chartizate.ChartizateManagerBuilderImpl
import org.jesperancinha.chartizate.distributions.ChartizateDistributionType.Linear

class ImageChartizate(imageChatizateConfig: ImageChartizateConfig, bufferedImage: BufferedImage) extends ImageFilter[BufferedImage] {
  val graphics: ImageChartizateGraphics = imageChatizateConfig.imageChartizateGraphics
  val bgColor: Int = graphics.bgColor
  val densityPercentage: Int = graphics.densityPercentage
  val imageChatizateChars: ImageChartizateChars = imageChatizateConfig.imageChartizateChars
  val rangePercentage: Int = imageChatizateChars.imageChartizateRange.rangePercentage
  val unicode: String = imageChatizateChars.imageChartizateRange.unicode
  val imageChartizateFont: ImageChartizateFont = imageChatizateChars.imageChartizateFont
  val fontName: String = imageChartizateFont.fontName
  val fontSize: Int = imageChartizateFont.fontSize

  override def apply(): BufferedImage = {
    val os = new ByteArrayOutputStream
    ImageIO.write(bufferedImage, "png", os)
    val imageInputStream = new ByteArrayInputStream(os.toByteArray)
    val manager = new ChartizateManagerBuilderImpl()
      .backgroundColor(new Color(bgColor))
      .densityPercentage(densityPercentage)
      .rangePercentage(rangePercentage)
      .distributionType(Linear)
      .fontName(fontName)
      .fontSize(fontSize)
      .block(UnicodeBlock.forName(unicode))
      .imageFullStream(imageInputStream)
      .build();
    manager.generateConvertedImageStream()
  }
}
