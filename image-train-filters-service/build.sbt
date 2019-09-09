name := "image-train-filters-service"

version := "1.0.0-SNAPSHOT"

scalaVersion := "2.12.7"

javacOptions ++= Seq("-source", "11", "-target", "11")

resolvers += Resolver.sbtPluginRepo("releases")
resolvers += Resolver.DefaultMavenRepository
resolvers += Resolver.url("bintray-sbt-plugins", url("https://dl.bintray.com/eed3si9n/sbt-plugins/"))(Resolver.ivyStylePatterns)
resolvers += Resolver.bintrayIvyRepo("com.eed3si9n", "sbt-plugins")
resolvers += "Spray repository" at "http://repo.spray.io"
resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"
resolvers += Resolver.mavenCentral


libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.5"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "test"
libraryDependencies += "org.mockito" % "mockito-core" % "1.9.5" % "test"
libraryDependencies += "net.liftweb" %% "lift-json" % "3.3.0"
libraryDependencies += "org.specs2" %% "specs2-core" % "4.3.5" % "test"
libraryDependencies += "junit" % "junit" % "4.11" % "test"
libraryDependencies += "com.typesafe.slick" %% "slick" % "3.2.3"
libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.25"
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.0.13"
libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.5.17"
libraryDependencies += "com.typesafe.akka" %% "akka-stream" % "2.5.17"
libraryDependencies += "com.typesafe.akka" %% "akka-http" % "10.1.5"
libraryDependencies += "com.typesafe.akka" %% "akka-http-spray-json" % "10.1.5"
libraryDependencies += "com.typesafe.akka" %% "akka-http-testkit" % "10.1.5"
libraryDependencies += "org.jesperancinha.itf" % "itf-chartizate-java" % "4.1.2" exclude("org.fusesource.jansi", "jansi")


scalacOptions in Test ++= Seq("-Yrangepos")
