package com.jesper.imagecontour.rest

import java.io.File

import akka.actor.ActorSystem
import akka.event.{LogSource, Logging}
import akka.http.scaladsl.model.Multipart.BodyPart
import akka.http.scaladsl.model.{HttpResponse, Multipart, StatusCodes}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.stream.Materializer
import akka.stream.scaladsl.FileIO
import com.jesper.imagecontour.Boot
import com.jesper.imagecontour.filters.{ImageContour, ImageKuwahara, ImageManager, ImageSaver}

import scala.concurrent.duration._
import scala.concurrent.{ExecutionContextExecutor, Future}

trait ImageContourMultiPartDataHandler {

  implicit val system: ActorSystem

  implicit def executor: ExecutionContextExecutor

  implicit val materializer: Materializer

  implicit val logSource: LogSource[AnyRef] = new LogSource[AnyRef] {
    def genString(o: AnyRef): String = o.getClass.getName

    override def getClazz(o: AnyRef): Class[_] = o.getClass
  }

  def processMultiPartData: Route = path("images" / Rest) {
    restCommand =>
      (post & entity(as[Multipart.FormData])) { formData =>
        complete {
          val log = Logging(system, this)
          //noinspection UnnecessaryPartialFunction

          val extractedData: Future[Map[String, String]] = formData.parts.mapAsync[(String, String)](1) {
            case bodyPart: BodyPart if bodyPart.name.equals("filename") =>
              log.info(s"received ${bodyPart.name} file")
              log.info(s"command $restCommand")
              val tempFile: File = new File(Boot.fileRootSource, bodyPart.filename.orNull)
              val data: Future[(String, String)] = bodyPart
                .entity
                .dataBytes
                .runWith(FileIO.toFile(tempFile)).map(_ => bodyPart.name -> bodyPart.getFilename.orElse(""))
              log.info("saved! - " + tempFile)
              data
            case bodyPart: BodyPart if bodyPart.name.equals("commands") =>
              Future[(String, String)] {
                bodyPart.name ->
                  bodyPart.entity.toStrict(10 seconds).value.get.get.getData().decodeString("UTF-8")
              }
          }.runFold(Map.empty[String, String])((map, tuple) => map + tuple)

          extractedData.map(data => {

            val filename: String = data.get("filename").orNull
            val tempFile: File = new File(Boot.fileRootSource, filename)
            val srcBuff = ImageManager.getBufferedImage(tempFile)
            val filter = restCommand match {
              case "imageContour" => new ImageContour(0xFFFFFF, 0x000000, 800000, 2)
              case "imageKuwahara" => new ImageKuwahara(2, 1)
            }
            val out = filter(srcBuff)
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

  val routes = processMultiPartData
}
