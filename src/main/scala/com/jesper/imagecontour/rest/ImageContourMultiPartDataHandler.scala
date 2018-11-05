package com.jesper.imagecontour.rest

import java.awt.image.BufferedImage
import java.io.File

import akka.actor.ActorSystem
import akka.event.{LogSource, Logging}
import akka.http.scaladsl.model.Multipart.{BodyPart, FormData}
import akka.http.scaladsl.model.{HttpResponse, StatusCodes}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.stream.Materializer
import akka.stream.scaladsl.FileIO
import com.jesper.imagecontour.Boot
import com.jesper.imagecontour.filters.{ImageContour, ImageKuwahara, ImageManager, ImageSaver}
import com.jesper.imagecontour.objects.{Commands, JsonSupport}
import net.liftweb.json.{DefaultFormats, parse}

import scala.concurrent.duration._
import scala.concurrent.{ExecutionContextExecutor, Future}

trait ImageContourMultiPartDataHandler extends JsonSupport {

  implicit val system: ActorSystem

  implicit def executor: ExecutionContextExecutor

  implicit val materializer: Materializer

  implicit val formats = DefaultFormats

  implicit val logSource: LogSource[AnyRef] = new LogSource[AnyRef] {
    def genString(o: AnyRef): String = o.getClass.getName

    override def getClazz(o: AnyRef): Class[_] = o.getClass
  }

  def processMultiPartData: Route = pathPrefix("images") {
    pathEnd {
      (post & entity(as[FormData])) { formData =>
        complete {
          val log = Logging(system, this)
          //noinspection UnnecessaryPartialFunction

          val extractedData: Future[Map[String, Any]] = formData.parts.mapAsync[(String, Any)](1) {
            case bodyPart: BodyPart if bodyPart.name.equals("filename") =>
              log.info(s"received ${bodyPart.name} file")
              val tempFile: File = new File(Boot.fileRootSource, bodyPart.filename.orNull)
              val data: Future[(String, Any)] = bodyPart
                .entity
                .dataBytes
                .runWith(FileIO.toFile(tempFile)).map(_ => bodyPart.name -> bodyPart.getFilename.orElse(""))
              log.info("saved! - " + tempFile)
              data
            case bodyPart: BodyPart if bodyPart.name.equals("commands") =>
              Future[(String, Any)] {
                bodyPart.name ->
                  bodyPart.entity.toStrict(10 seconds).value.get.get.getData().decodeString("UTF-8")
              }
          }.runFold(Map.empty[String, Any])((map, tuple) => map + tuple)

          extractedData.map(data => {

            val commands: String = data.get("commands").orNull.asInstanceOf[String]
            val jsonParser = parse(commands)
            val commandsParsed = jsonParser.extract[Commands]
            val filename: String = data.get("filename").orNull.asInstanceOf[String]
            val tempFile: File = new File(Boot.fileRootSource, filename)
            var srcBuff = ImageManager.getBufferedImage(tempFile)

            var out: BufferedImage = null
            for (elem <- commandsParsed.commands) {
              val filter = elem.filter match {
                case "imageContour" => new ImageContour(
                  elem.settings.find(p => p.name.equals("bgColor")).orNull.value.toIntFromHex,
                  elem.settings.find(p => p.name.equals("lnColor")).orNull.value.toIntFromHex,
                  elem.settings.find(p => p.name.equals("diffThreshold")).orNull.value.toInt,
                  elem.settings.find(p => p.name.equals("radius")).orNull.value.toInt
                )
                case "imageKuwahara" => new ImageKuwahara(
                  elem.settings.find(p => p.name.equals("square-size")).orNull.value.toInt,
                  elem.settings.find(p => p.name.equals("iterations")).orNull.value.toInt
                )
              }
              log.info("applied - " + elem.filter)
              out = filter(srcBuff)
              srcBuff = out
            }

            val destinationFile: File = new File(Boot.fileRootDestination, filename)
            ImageSaver.copyBufferedImage(out, destinationFile)
            log.info("generated! - " + destinationFile)

            HttpResponse(StatusCodes.OK, entity = s"Ok. Got $data")
          })
            .recover {
              case ex: Exception => HttpResponse(StatusCodes.InternalServerError, entity = "Failed!")
            }
        }
      }
    }
  }

  val routes = processMultiPartData
}
