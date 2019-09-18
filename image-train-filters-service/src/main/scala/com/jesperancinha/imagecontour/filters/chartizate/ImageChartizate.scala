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
  private val graphics: ImageChartizateGraphics = imageChatizateConfig.imageChartizateGraphics
  private val bgColor: Int = graphics.bgColor
  private val densityPercentage: Int = graphics.densityPercentage
  private val imageChatizateChars: ImageChartizateChars = imageChatizateConfig.imageChartizateChars
  private val rangePercentage: Int = imageChatizateChars.imageChartizateRange.rangePercentage
  private val unicode: String = imageChatizateChars.imageChartizateRange.unicode
  private val imageChartizateFont: ImageChartizateFont = imageChatizateChars.imageChartizateFont
  private val fontName: String = imageChartizateFont.fontName
  private val fontSize: Int = imageChartizateFont.fontSize

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
