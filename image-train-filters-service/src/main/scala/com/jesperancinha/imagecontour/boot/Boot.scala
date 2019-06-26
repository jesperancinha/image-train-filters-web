package com.jesperancinha.imagecontour.boot

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.jesperancinha.imagecontour.config.Configuration
import com.jesperancinha.imagecontour.rest.ImageContourMultiPartDataHandler

import scala.concurrent.ExecutionContextExecutor

object Boot extends App with ImageContourMultiPartDataHandler with Configuration {

  override implicit val system: ActorSystem = ActorSystem()
  override implicit val executor: ExecutionContextExecutor = system.dispatcher
  override implicit val materializer: ActorMaterializer = ActorMaterializer()

  Http().bindAndHandle(uploadingData, serviceHost, servicePort)
}
