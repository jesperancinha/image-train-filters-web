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
    rest =>
      (post & entity(as[Multipart.FormData])) { formData =>
        complete {
          val log = Logging(system, this)
          //noinspection UnnecessaryPartialFunction
          val extractedData: Future[Map[String, Any]] = formData.parts.mapAsync[(String, Any)](1) {
            case file: BodyPart =>

              log.info(s"received ${file.name} file")
              val tempFile: File = new File(Boot.serviceImageFolder, file.name)
              file.entity.dataBytes.runWith(FileIO.toFile(tempFile)).map(_ => file.name -> file.getFilename())

          }.runFold(Map.empty[String, Any])((map, tuple) => map + tuple)
          extractedData.map(data => HttpResponse(StatusCodes.OK, entity = s"Ok. Got $data"))
            .recover {
              case ex: Exception => HttpResponse(StatusCodes.InternalServerError, entity = "Failed!")
            }
        }
      }
  }

  val routes = processMultiPartData
}
