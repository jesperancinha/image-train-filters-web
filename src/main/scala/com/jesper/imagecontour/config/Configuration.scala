package com.jesper.imagecontour.config

import com.typesafe.config.ConfigFactory
import util.Try

/**
  * Image train filters setup
  */
trait Configuration {

  /**
    * Application configuration
    */
  val config = ConfigFactory.load()

  /** Host **/
  lazy val serviceHost = Try(config.getString("service.host")).getOrElse("localhost")

  /** Host Port **/
  lazy val servicePort = Try(config.getInt("service.port")).getOrElse(8080)

  /** Image saving folder **/
  lazy val serviceImageFolder = Try(config.getString("image-train-filters.image-path")).getOrElse(System.getProperty("java.io.tmpdir"))
}