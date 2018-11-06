package com.jesper.imagecontour.config

import java.io.File

import com.jesper.imagecontour.Boot
import com.typesafe.config.{Config, ConfigFactory}

import util.Try

/**
  * Image train filters setup
  */
trait Configuration {

  /**
    * Application configuration
    */
  val config: Config = ConfigFactory.load()

  /** Host **/
  lazy val serviceHost: String = Try(config.getString("service.host")).getOrElse("localhost")

  /** Host Port **/
  lazy val servicePort: Int = Try(config.getInt("service.port")).getOrElse(8080)

  /** Image source folder **/
  lazy val serviceImageSourceFolder: String = Try(config.getString("image-train-filters.image-source-path")).getOrElse(System.getProperty("java.io.tmpdir") + "/source")

  /** Image destination folder **/
  lazy val serviceImageDestinationFolder: String = Try(config.getString("image-train-filters.image-destination-path")).getOrElse(System.getProperty("java.io.tmpdir") + "/destination")

  val fileRootSource =  new File(Boot.serviceImageSourceFolder)
  fileRootSource.mkdirs()

  val fileRootDestination = new File(Boot.serviceImageDestinationFolder)
  fileRootDestination.mkdirs()
}