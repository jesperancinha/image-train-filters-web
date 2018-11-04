name := "image-train-filters"

version := "1.0"

scalaVersion := "2.12.1"

libraryDependencies += "org.scalactic" %% "scalactic" % "2.2.6"
libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.6" % "test"
libraryDependencies += "org.mockito" % "mockito-core" % "1.9.5" % "test"
libraryDependencies += "net.liftweb" % "lift-json_2.11" % "2.6.2"
libraryDependencies ++= Seq(
  // notice the double %% here
  "com.eed3si9n" %% "sbt-assembly" % "0.14.2",

)
libraryDependencies ++= Seq(
  "org.specs2" %% "specs2-core" % "3.7.2" % "test",
  "junit" % "junit" % "4.11" % "test",
  "net.liftweb" %% "lift-json" % "2.6",
  "com.typesafe.slick" %% "slick" % "3.1.1",
  "mysql" % "mysql-connector-java" % "5.1.25",
  "ch.qos.logback" % "logback-classic" % "1.0.13",
  "com.typesafe.akka" %% "akka-actor" % "2.4.3",
  "com.typesafe.akka" %% "akka-stream" % "2.4.3",
  "com.typesafe.akka" %% "akka-http-experimental" % "2.4.3",
  "com.typesafe.akka" %% "akka-http-spray-json-experimental" % "2.4.3",
  "com.typesafe.akka" %% "akka-http-testkit" % "2.4.3"
)

resolvers ++= Seq(
  "Spray repository" at "http://repo.spray.io",
  "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"
)


scalacOptions in Test ++= Seq("-Yrangepos")
