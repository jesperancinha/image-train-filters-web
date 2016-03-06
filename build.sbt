name := "image-contour"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies += "org.scalactic" %% "scalactic" % "2.2.6"
libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.6" % "test"
libraryDependencies += "org.mockito" % "mockito-core" % "1.9.5" % "test"
libraryDependencies ++= Seq(
  "org.specs2" %% "specs2-core" % "3.7.2" % "test",
  "junit" % "junit" % "4.11" % "test"
)

scalacOptions in Test ++= Seq("-Yrangepos")
