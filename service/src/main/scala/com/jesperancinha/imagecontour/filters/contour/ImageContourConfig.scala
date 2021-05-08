package com.jesperancinha.imagecontour.filters.contour

case class ImageContourConfig(imageContourColors: ImageContourColors = new ImageContourColors(), imageContourThreshold: ImageContourThreshold = new ImageContourThreshold()) {

  def addBgColor(bgColor: Int): ImageContourConfig = {
    val colors: ImageContourColors = new ImageContourColors(bgColor, imageContourColors.lnColor)
    ImageContourConfig(colors, imageContourThreshold)
  }

  def addLineColor(lnColor: Int): ImageContourConfig = {
    val colors: ImageContourColors = new ImageContourColors(imageContourColors.bgColor, lnColor)
    ImageContourConfig(colors, imageContourThreshold)
  }

  def addDiffThreshold(diffThreshold: Double): ImageContourConfig = {
    val threshold: ImageContourThreshold = new ImageContourThreshold(diffThreshold, imageContourThreshold.radius)
    ImageContourConfig(imageContourColors, threshold)
  }

  def addRadius(radius: Int): ImageContourConfig = {
    val threshold: ImageContourThreshold = new ImageContourThreshold(imageContourThreshold.diffThreshold, radius)
    ImageContourConfig(imageContourColors, threshold)
  }

}
