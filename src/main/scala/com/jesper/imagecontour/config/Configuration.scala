package com.jesper.imagecontour.config

import java.io.File

import com.jesper.imagecontour.Boot
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

  /** Image source folder **/
  lazy val serviceImageSourceFolder = Try(config.getString("image-train-filters.image-source-path")).getOrElse(System.getProperty("java.io.tmpdir") + "/source")

  /** Image destination folder **/
  lazy val serviceImageDestinationFolder = Try(config.getString("image-train-filters.image-destination-path")).getOrElse(System.getProperty("java.io.tmpdir") + "/destination")

  val fileRootSource =  new File(Boot.serviceImageSourceFolder)
  fileRootSource.mkdirs()

  val fileRootDestination = new File(Boot.serviceImageDestinationFolder)
  fileRootDestination.mkdirs()
}