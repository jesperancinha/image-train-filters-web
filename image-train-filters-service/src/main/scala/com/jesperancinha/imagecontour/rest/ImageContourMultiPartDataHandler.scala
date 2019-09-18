package com.jesperancinha.imagecontour.rest

import java.awt.image.BufferedImage
import java.io.File

import akka.actor.ActorSystem
import akka.event.{LogSource, Logging, LoggingAdapter}
import akka.http.scaladsl.model.Multipart.{BodyPart, FormData}
import akka.http.scaladsl.model.StatusCodes.OK
import akka.http.scaladsl.model.{HttpEntity, HttpResponse, StatusCodes}
import akka.http.scaladsl.server.Route
import akka.stream.Materializer
import akka.stream.scaladsl.FileIO
import com.jesperancinha.imagecontour.boot.Boot
import com.jesperancinha.imagecontour.filters._
import com.jesperancinha.imagecontour.filters.chartizate._
import com.jesperancinha.imagecontour.filters.contour.{ImageContour, ImageContourConfig}
import com.jesperancinha.imagecontour.filters.kuwahara.ImageKuwahara
import com.jesperancinha.imagecontour.objects.{CommandContainer, Commands, Item, JsonSupport}
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64
import javax.imageio.ImageIO
import net.liftweb.json.{DefaultFormats, parse}
import org.jesperancinha.chartizate.ChartizateFonts.getAllAvailableFonts
import org.jesperancinha.chartizate.ChartizateUnicodes.getAllUniCodeBlocksJava

import scala.collection.JavaConverters._
import scala.concurrent.duration._
import scala.concurrent.{ExecutionContextExecutor, Future}
import scala.language.postfixOps

trait ImageContourMultiPartDataHandler extends JsonSupport {

  implicit val system: ActorSystem

  implicit def executor: ExecutionContextExecutor

  implicit val materializer: Materializer

  implicit val formats: DefaultFormats.type = DefaultFormats

  implicit val logSource: LogSource[AnyRef] = new LogSource[AnyRef] {
    def genString(o: AnyRef): String = o.getClass.getName

    override def getClazz(o: AnyRef): Class[_] = o.getClass
  }

  val itfRoutes: Route = itfDataRoutes

  def processImageRequestData(data: Map[String, Any]): HttpResponse = {
    val result: BufferedImage = handleRequest(data)
    import java.io.ByteArrayOutputStream
    val outputStream = new ByteArrayOutputStream
    ImageIO.write(result, "png", outputStream)
    HttpResponse(OK, entity = Base64.encode(outputStream.toByteArray))
  }

  def pathUnicodes: Route = {
    pathPrefix("unicodes") {
      pathEnd {
        get {
          complete(Item("unicodes", getAllUniCodeBlocksJava.asScala.map(x => x.toString).toArray))
        }
      }
    }
  }

  def pathFonts: Route = {
    pathPrefix("fonts") {
      pathEnd {
        get {
          complete(Item("fonts", getAllAvailableFonts.asScala.toArray))
        }
      }
    }
  }

  def pathListings: Route = {
    pathPrefix("listings") {
      pathUnicodes ~
        pathFonts
    }
  }

  def itfDataRoutes: Route = withSizeLimit(104857600) {
    pathPrefix("images") {
      pathEnd {
        ((post | options) & entity(as[FormData])) { formData =>
          complete {
            val extractedData: Future[Map[String, Any]] = extractRequestData(formData)
            extractedData.map(data => {
              processImageRequestData(data)
            })
              .recover {
                case e: Exception =>
                  val log: LoggingAdapter = Logging(system, this)
                  log.error(e.getMessage, e)
                  HttpResponse(StatusCodes.InternalServerError, entity = "Failed!")
              }
          }
        }
      }
    } ~
      pathListings
  }

  private def extractRequestData(formData: FormData): Future[Map[String, Any]] = {
    val log = Logging(system, this)
    val extractedData: Future[Map[String, Any]] = formData.parts.mapAsync[(String, Any)](1) {
      case bodyPart: BodyPart if bodyPart.name.equals("filename") =>
        processFileName(log, bodyPart)
      case bodyPart: BodyPart if bodyPart.name.equals("commands") =>
        processCommands(log, bodyPart)
    }.runFold(Map.empty[String, Any])((map, tuple) => map + tuple)
    extractedData
  }

  private def handleRequest(data: Map[String, Any]): BufferedImage = {
    val log: LoggingAdapter = Logging(system, this)
    val commands: String = data.get("commands").orNull.asInstanceOf[String]
    val jsonParser = parse(commands)
    val commandsParsed = jsonParser.extract[Commands]
    val filename: String = data.get("filename").orNull.asInstanceOf[String]
    val tempFile: File = new File(Boot.fileRootSource, filename)
    val output: BufferedImage = filterBufferedImage(log, ImageManager.getBufferedImage(tempFile), commandsParsed.commands.head, commandsParsed.commands.tail.toArray)
    if (Boot.saveImages) {
      val destinationFile: File = new File(Boot.fileRootDestination, filename)
      ImageSaver.copyBufferedImage(output, destinationFile)
      log.info("generated! - " + destinationFile)
    } else {
      tempFile.delete()
      log.info("removed temporary file - " + tempFile)
    }
    output
  }

  private def processCommands(log: LoggingAdapter, bodyPart: FormData.BodyPart): Future[(String, Any)] = {
    log.info(s"received ${bodyPart.toString} commands")
    Future[(String, Any)] {
      val eventualStrict = bodyPart.entity.toStrict(10 seconds)
      val triedStrict = eventualStrict.value.orNull
      if (triedStrict == null) {
        ("", None)
      }
      else {
        val value = triedStrict.getOrElse(HttpEntity.Empty)
        if (value == null) {
          ("", None)
        } else {
          bodyPart.name ->
            value.getData().decodeString("UTF-8")
        }
      }
    }
  }

  private def processFileName(log: LoggingAdapter, bodyPart: FormData.BodyPart): Future[(String, Any)] = {
    log.info(s"received ${bodyPart.toString} file")
    val tempFile: File = new File(Boot.fileRootSource, bodyPart.filename.orNull)
    val data: Future[(String, Any)] = bodyPart
      .entity
      .dataBytes
      .runWith(FileIO.toPath(tempFile.toPath)).map(_ => bodyPart.name -> bodyPart.getFilename.orElse(""))
    log.info("saved! - " + tempFile)
    data
  }

  @scala.annotation.tailrec
  private def filterBufferedImage(log: LoggingAdapter, srcBuff: BufferedImage, elem: CommandContainer, commands: Array[CommandContainer]): BufferedImage = {
    val filter: ImageFilter[BufferedImage] = createFilterFromCommandContainter(elem, srcBuff)
    log.info("applied - " + elem.filter)
    val out: BufferedImage = filter()
    if (commands.isEmpty) {
      out
    } else {
      filterBufferedImage(log, out, commands.head, commands.tail)
    }
  }

   def createFilterFromCommandContainter(elem: CommandContainer, srcBuff: BufferedImage): ImageFilter[BufferedImage] = {
    val filter = elem.filter match {
      case "imageContour" => new ImageContour(
        createImageContourConfigFromCommands(elem),
        srcBuff
      )
      case "imageKuwahara" => new ImageKuwahara(
        elem.settings.find(p => p.name.equals("square-size")).orNull.value.toInt,
        elem.settings.find(p => p.name.equals("iterations")).orNull.value.toInt,
        srcBuff
      )
      case "imageChartizate" =>
        new ImageChartizate(
          createImageConfigFromCommands(elem),
          srcBuff
        )
    }
    filter
  }

  private def createImageContourConfigFromCommands(elem: CommandContainer) = {
    ImageContourConfig().addBgColor(elem.settings.find(p => p.name.equals("bgColor")).orNull.value.toIntFromHex)
      .addLineColor(elem.settings.find(p => p.name.equals("lnColor")).orNull.value.toIntFromHex)
      .addDiffThreshold(elem.settings.find(p => p.name.equals("diffThreshold")).orNull.value.toInt)
      .addRadius(elem.settings.find(p => p.name.equals("radius")).orNull.value.toInt)
  }

  private def createImageConfigFromCommands(elem: CommandContainer): ImageChartizateConfig = {
    ImageChartizateConfig()
      .addFontName(elem.settings.find(p => p.name.equals("font")).orNull.value.toString)
      .addFontSize(elem.settings.find(p => p.name.equals("fontSize")).orNull.value.toInt)
      .addRangePercentage(elem.settings.find(p => p.name.equals("rangePer")).orNull.value.toInt)
      .addUnicode(elem.settings.find(p => p.name.equals("unicode")).orNull.value.toString)
      .addBgColor(elem.settings.find(p => p.name.equals("bgColor")).orNull.value.toIntFromHex)
      .addDensityPercentage(elem.settings.find(p => p.name.equals("densityPer")).orNull.value.toInt)
  }
}
