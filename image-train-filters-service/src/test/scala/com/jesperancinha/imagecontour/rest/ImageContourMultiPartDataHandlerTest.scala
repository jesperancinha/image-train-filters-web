package com.jesperancinha.imagecontour.rest

import java.awt.image.{BufferedImage, Raster}

import akka.actor.ActorSystem
import akka.stream.Materializer
import com.jesperancinha.imagecontour.filters.chartizate.ImageChartizate
import com.jesperancinha.imagecontour.filters.contour.ImageContour
import com.jesperancinha.imagecontour.filters.kuwahara.ImageKuwahara
import com.jesperancinha.imagecontour.objects.{CommandContainer, SettingItem}
import org.mockito.Mockito._
import org.scalatest.FunSuite
import org.scalatest.mockito.MockitoSugar

import scala.concurrent.ExecutionContextExecutor

class ImageContourMultiPartDataHandlerTest extends FunSuite with MockitoSugar {

  val imageContourMultiPartDataHandler: ImageContourMultiPartDataHandler =
    new ImageContourMultiPartDataHandler {
      override implicit val system: ActorSystem = mock[ActorSystem]

      override implicit def executor: ExecutionContextExecutor = mock[ExecutionContextExecutor]

      override implicit val materializer: Materializer = mock[Materializer]
    }

  test("testCreateFilterFromCommandContainterImageContour") {
    val items: List[SettingItem] = List(
      SettingItem("bgColor", "123"),
      SettingItem("lnColor", "234"),
      SettingItem("diffThreshold", "555"),
      SettingItem("radius", "1444")
    )
    val container: CommandContainer = CommandContainer("imageContour", items)

    val mockImageBuffer = mock[BufferedImage]
    val result: ImageContour =
      imageContourMultiPartDataHandler
        .createFilterFromCommandContainter(container, mockImageBuffer)
        .asInstanceOf[ImageContour]

    assertResult(result.bgColor) {
      123
    }
    assertResult(result.lnColor) {
      234
    }
    assertResult(result.diffThreshold) {
      555
    }
    assertResult(result.radius) {
      1444
    }
  }

  test("testCreateFilterFromCommandContainterImageKuwahara") {
    val items: List[SettingItem] = List(
      SettingItem("square-size", "20"),
      SettingItem("iterations", "10"),
    )
    val container: CommandContainer = CommandContainer("imageKuwahara", items)

    val mockBufferedImage = mock[BufferedImage]
    val mockRaster = mock[Raster]
    when(mockBufferedImage.getData) thenReturn mockRaster
    val result: ImageKuwahara =
      imageContourMultiPartDataHandler
        .createFilterFromCommandContainter(container, mockBufferedImage)
        .asInstanceOf[ImageKuwahara]

    assertResult(result.squareSize) {
      20
    }
    assertResult(result.iterations) {
      10
    }
    assertResult(result.w) {
      0
    }
    assertResult(result.h) {
      0
    }
  }

  test("testCreateFilterFromCommandContainterImageChartizate") {
    val items: List[SettingItem] = List(
      SettingItem("font", "ARIAL"),
      SettingItem("fontSize", "10"),
      SettingItem("rangePer", "33"),
      SettingItem("densityPer", "44"),
      SettingItem("unicode", "UTF"),
      SettingItem("bgColor", "3333"),
    )
    val container: CommandContainer = CommandContainer("imageChartizate", items)

    val mockBufferedImage = mock[BufferedImage]
    val mockRaster = mock[Raster]
    when(mockBufferedImage.getData) thenReturn mockRaster
    val result: ImageChartizate =
      imageContourMultiPartDataHandler
        .createFilterFromCommandContainter(container, mockBufferedImage)
        .asInstanceOf[ImageChartizate]

    assertResult(result.fontName) {
      "ARIAL"
    }
    assertResult(result.fontSize) {
      10
    }
    assertResult(result.rangePercentage) {
      33
    }
    assertResult(result.densityPercentage) {
      44
    }
    assertResult(result.unicode) {
      "UTF"
    }
    assertResult(result.bgColor) {
      3333
    }
  }

}
