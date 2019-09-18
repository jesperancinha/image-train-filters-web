package com.jesperancinha.imagecontour.filters.chartizate

case class ImageChartizateConfig(imageChartizateChars: ImageChartizateChars = new ImageChartizateChars(),
                                 imageChartizateGraphics: ImageChartizateGraphics = new ImageChartizateGraphics()) {

  def addFontName(fontName: String): ImageChartizateConfig = {
    val fontSize: Int = imageChartizateChars.imageChartizateFont.fontSize
    val imageChartizateRange: ImageChartizateRange = imageChartizateChars.imageChartizateRange
    val imageChartizateCharsLocal: ImageChartizateChars = new ImageChartizateChars(new ImageChartizateFont(fontName, fontSize), imageChartizateRange)
    ImageChartizateConfig(imageChartizateCharsLocal, imageChartizateGraphics)
  }

  def addFontSize(fontSize: Int): ImageChartizateConfig = {
    val fontName: String = imageChartizateChars.imageChartizateFont.fontName
    val imageChartizateRange: ImageChartizateRange = imageChartizateChars.imageChartizateRange
    val imageChartizateCharsLocal: ImageChartizateChars = new ImageChartizateChars(new ImageChartizateFont(fontName, fontSize), imageChartizateRange)
    ImageChartizateConfig(imageChartizateCharsLocal, imageChartizateGraphics)
  }

  def addRangePercentage(range: Int): ImageChartizateConfig = {
    val imageChartizateFont: ImageChartizateFont = imageChartizateChars.imageChartizateFont
    val imageChartizateRange: ImageChartizateRange = imageChartizateChars.imageChartizateRange
    val unicode: String = imageChartizateRange.unicode
    val imageChartizateCharsLocal: ImageChartizateChars = new ImageChartizateChars(imageChartizateFont, new ImageChartizateRange(range, unicode))
    ImageChartizateConfig(imageChartizateCharsLocal, imageChartizateGraphics)
  }

  def addUnicode(unicode: String): ImageChartizateConfig = {
    val imageChartizateFont: ImageChartizateFont = imageChartizateChars.imageChartizateFont
    val imageChartizateRange: ImageChartizateRange = imageChartizateChars.imageChartizateRange
    val rangePercentage: Int = imageChartizateRange.rangePercentage
    val imageChartizateCharsLocal: ImageChartizateChars = new ImageChartizateChars(imageChartizateFont, new ImageChartizateRange(rangePercentage, unicode))
    ImageChartizateConfig(imageChartizateCharsLocal, imageChartizateGraphics)
  }

  def addBgColor(bgColor: Int): ImageChartizateConfig = {
    ImageChartizateConfig(imageChartizateChars, new ImageChartizateGraphics(bgColor, imageChartizateGraphics.densityPercentage))
  }

  def addDensityPercentage(densityPercentage: Int): ImageChartizateConfig = {
    ImageChartizateConfig(imageChartizateChars, new ImageChartizateGraphics(imageChartizateGraphics.bgColor, densityPercentage))
  }
}
