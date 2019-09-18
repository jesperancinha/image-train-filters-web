package com.jesperancinha.imagecontour.filters

import java.io.File
import java.net.URL

import org.scalatest.FunSuite

class ImageManagerTest extends FunSuite {
  test("testGetBufferedImage") {
    val url: URL = getClass.getResource("/testPanther.jpg")
    ImageManager.getBufferedImage(new File(url.getFile))
  }

}
