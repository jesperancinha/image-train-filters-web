package com.jesperancinha.imagecontour.filters

import java.io.File
import java.net.URL

import org.scalatest.funsuite.AnyFunSuite

class ImageManagerTest extends AnyFunSuite {
  test("testGetBufferedImage") {
    val url: URL = getClass.getResource("/testMarket.jpg")
    ImageManager.getBufferedImage(new File(url.getFile))
  }

}
