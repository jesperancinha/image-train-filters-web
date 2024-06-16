name := "image-train-filters-scala"
scalaVersion := "2.13.6"
version := "1.0.0"
resolvers += Resolver.mavenLocal
resolvers += Resolver.mavenCentral
resolvers +=
  "Maven Central" at "https://repo1.maven.org/maven2/"

ThisBuild / evictionErrorLevel := Level.Info
ThisBuild / assumedVersionScheme := VersionScheme.PVP
ThisBuild / assumedVersionSchemeJava := VersionScheme.EarlySemVer
ThisBuild / assumedEvictionErrorLevel := Level.Warn

lazy val global = project
  .in(file("."))
  .settings(commonSettings)
  .aggregate(
    service
  )

lazy val service = project
  .settings(
    name := "image-train-filters-service",
    version := "1.0.0",
    commonSettings,
    libraryDependencies ++= commonDependencies,
    assemblySettings,
    mainClass := Some("com.jesperancinha.imagecontour.boot.Boot"),
    assembly / mainClass := Some("com.jesperancinha.imagecontour.boot.Boot"),
    Compile / mainClass  := Some("com.jesperancinha.imagecontour.boot.Boot"),
  )


lazy val commonDependencies = Seq(
  "io.spray" %% "spray-json" % "1.3.6",
  "org.scalactic" %% "scalactic" % "3.2.18",
  "net.liftweb" %% "lift-json" % "3.5.0",
  "com.typesafe.slick" %% "slick" % "3.5.1",
  "mysql" % "mysql-connector-java" % "8.0.33",
  "ch.qos.logback" % "logback-classic" % "1.5.6",
  "com.typesafe.akka" %% "akka-http-core" % "10.5.3",
  "com.typesafe.akka" %% "akka-actor" % "2.8.5",
  "com.typesafe.akka" %% "akka-stream" % "2.8.5",
  "com.typesafe.akka" %% "akka-http" % "10.5.3",
  "com.typesafe.akka" %% "akka-http-spray-json" % "10.5.3",
  "com.typesafe.akka" %% "akka-http-testkit" % "10.5.3",
  "org.jesperancinha.itf" % "itf-chartizate-java" % "5.0.0" exclude("org.fusesource.jansi", "jansi"),
  "org.mockito" %% "mockito-scala" % "1.17.31" % Test,
  "org.scalatest" %% "scalatest" % "3.2.18" % Test,
  "org.scalatest" %% "scalatest-flatspec" % "3.3.0-alpha.1" % Test,
  "org.scalatest" %% "scalatest-wordspec" % "3.3.0-alpha.1" % Test,
  "org.scalatest" %% "scalatest-core" % "3.3.0-alpha.1" % Test
)

lazy val compilerOptions = Seq(
  "-language:existentials",
  "-language:higherKinds",
  "-language:implicitConversions",
  "-language:postfixOps",
  "-encoding",
  "utf8"
)

lazy val commonSettings = Seq(
  javacOptions ++= Seq("-source", "11", "-target", "11"),
  scalacOptions ++= compilerOptions,
  resolvers ++= Seq(
    Resolver.sbtPluginRepo("releases"),
    Resolver.DefaultMavenRepository,
    Resolver.url("bintray-sbt-plugins", url("https://dl.bintray.com/eed3si9n/sbt-plugins/"))(Resolver.ivyStylePatterns),
    Resolver.bintrayIvyRepo("com.eed3si9n", "sbt-plugins"),
    "Spray repository" at "https://repo.spray.io",
    "Typesafe repository" at "https://repo.typesafe.com/typesafe/releases/",
    Resolver.mavenCentral,
    Resolver.mavenLocal
  )
)
lazy val assemblySettings = Seq(
  assembly / assemblyJarName := name.value + ".jar",
  assembly / assemblyMergeStrategy  := {
    case PathList("META-INF", xs @ _*) => MergeStrategy.discard
    case "application.conf"            => MergeStrategy.concat
    case x =>
      val oldStrategy = (assemblyMergeStrategy in assembly).value
      oldStrategy(x)
  }
)