name := "image-train-filters"

version := "1.0"

scalaVersion := "2.12.7"

resolvers += "Artima Maven Repository" at "http://repo.artima.com/releases"
resolvers += Resolver.sbtPluginRepo("releases")
resolvers += Resolver.url("bintray-sbt-plugins", url("https://dl.bintray.com/eed3si9n/sbt-plugins/"))(Resolver.ivyStylePatterns)
resolvers += Resolver.bintrayIvyRepo("com.eed3si9n", "sbt-plugins")
resolvers ++= Seq(
  "Spray repository" at "http://repo.spray.io",
  "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"
)

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.5"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "test"
libraryDependencies += "org.mockito" % "mockito-core" % "1.9.5" % "test"
libraryDependencies += "net.liftweb" % "lift-json_2.11" % "2.6.2"
libraryDependencies ++= Seq(
  "org.specs2" %% "specs2-core" % "4.3.5" % "test",
  "junit" % "junit" % "4.11" % "test",
  "net.liftweb" %% "lift-json" % "3.3.0",
  "com.typesafe.slick" %% "slick" % "3.2.3",
  "mysql" % "mysql-connector-java" % "5.1.25",
  "ch.qos.logback" % "logback-classic" % "1.0.13",
  "com.typesafe.akka" %% "akka-actor" % "2.5.17",
  "com.typesafe.akka" %% "akka-stream" % "2.5.17",
  "com.typesafe.akka" %% "akka-http" % "10.1.5",
  "com.typesafe.akka" %% "akka-http-spray-json" % "10.1.5",
  "com.typesafe.akka" %% "akka-http-testkit" % "10.1.5",
  "com.eed3si9n" %% "sbt-assembly" % "0.14.6",
)

scalacOptions in Test ++= Seq("-Yrangepos")
