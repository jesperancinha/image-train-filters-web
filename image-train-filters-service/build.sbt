name := "image-train-filters-service"

version := "1.0.0-SNAPSHOT"

scalaVersion := "2.13.1"

javacOptions ++= Seq("-source", "11", "-target", "11")

resolvers += Resolver.sbtPluginRepo("releases")
resolvers += Resolver.DefaultMavenRepository
resolvers += Resolver.url("bintray-sbt-plugins", url("https://dl.bintray.com/eed3si9n/sbt-plugins/"))(Resolver.ivyStylePatterns)
resolvers += Resolver.bintrayIvyRepo("com.eed3si9n", "sbt-plugins")
resolvers += "Spray repository" at "https://repo.spray.io"
resolvers += "Typesafe repository" at "https://repo.typesafe.com/typesafe/releases/"
resolvers += Resolver.mavenCentral
resolvers += Resolver.mavenLocal

libraryDependencies += "io.spray" %% "spray-json" % "1.3.5"
libraryDependencies += "org.scalactic" %% "scalactic" % "3.1.0"
libraryDependencies += "net.liftweb" %% "lift-json" % "3.4.1"
libraryDependencies += "junit" % "junit" % "4.11" % "test"
libraryDependencies += "com.typesafe.slick" %% "slick" % "3.3.2"
libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.25"
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.0.13"
libraryDependencies += "com.typesafe.akka" %% "akka-http-core" % "10.1.11"
libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.6.3"
libraryDependencies += "com.typesafe.akka" %% "akka-stream" % "2.6.3"
libraryDependencies += "com.typesafe.akka" %% "akka-http" % "10.1.11"
libraryDependencies += "com.typesafe.akka" %% "akka-http-spray-json" % "10.1.11"
libraryDependencies += "com.typesafe.akka" %% "akka-http-testkit" % "10.1.11"
libraryDependencies += "org.jesperancinha.itf" % "itf-chartizate-java" % "5.0.0" exclude("org.fusesource.jansi", "jansi")
libraryDependencies += "org.mockito" % "mockito-core" % "3.2.4" % Test
libraryDependencies += "org.mockito" %% "mockito-scala" % "1.11.1" % Test
libraryDependencies += "org.scalatest" %% "scalatest" % "3.1.0" % Test
libraryDependencies += "org.specs2" %% "specs2-core" % "4.8.3" % Test

scalacOptions in Test ++= Seq("-Yrangepos")

mainClass := Some("com.jesperancinha.imagecontour.boot.Boot")
