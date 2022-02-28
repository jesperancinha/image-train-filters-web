name := "image-train-filters-scala"
scalaVersion := "2.13.6"
version := "1.0.0-SNAPSHOT"
resolvers += Resolver.mavenLocal
resolvers += Resolver.mavenCentral
resolvers +=
  "Maven Central" at "https://repo1.maven.org/maven2/"

lazy val global = project
  .in(file("."))
  .settings(commonSettings)
  .disablePlugins(AssemblyPlugin)
  .aggregate(
    service
  )

lazy val service = project
  .settings(
    name := "image-train-filters-service",
    version := "1.0.0-SNAPSHOT",
    commonSettings,
    libraryDependencies ++= commonDependencies,
    assemblySettings,
    mainClass := Some("com.jesperancinha.imagecontour.boot.Boot")
  )


lazy val commonDependencies = Seq(
  "io.spray" %% "spray-json" % "1.3.5",
  "org.scalactic" %% "scalactic" % "3.1.0",
  "net.liftweb" %% "lift-json" % "3.4.1",
  "com.typesafe.slick" %% "slick" % "3.3.2",
  "mysql" % "mysql-connector-java" % "5.1.25",
  "ch.qos.logback" % "logback-classic" % "1.0.13",
  "com.typesafe.akka" %% "akka-http-core" % "10.1.11",
  "com.typesafe.akka" %% "akka-actor" % "2.6.3",
  "com.typesafe.akka" %% "akka-stream" % "2.6.3",
  "com.typesafe.akka" %% "akka-http" % "10.1.11",
  "com.typesafe.akka" %% "akka-http-spray-json" % "10.1.11",
  "com.typesafe.akka" %% "akka-http-testkit" % "10.1.11",
  "org.jesperancinha.itf" % "itf-chartizate-java" % "5.0.0" exclude("org.fusesource.jansi", "jansi"),
  "org.mockito" %% "mockito-scala" % "1.11.1" % Test,
  "org.scalatest" %% "scalatest" % "3.1.0" % Test,
)

lazy val compilerOptions = Seq(
  "-unchecked",
  "-feature",
  "-language:existentials",
  "-language:higherKinds",
  "-language:implicitConversions",
  "-language:postfixOps",
  "-deprecation",
  "-encoding",
  "utf8"
)

lazy val commonSettings = Seq(
  javacOptions ++= Seq("-source", "11", "-target", "11"),
  scalacOptions in Test ++= Seq("-Yrangepos"),
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
  assemblyJarName in assembly := name.value + ".jar",
  assemblyMergeStrategy in assembly := {
    case PathList("META-INF", xs @ _*) => MergeStrategy.discard
    case "application.conf"            => MergeStrategy.concat
    case x =>
      val oldStrategy = (assemblyMergeStrategy in assembly).value
      oldStrategy(x)
  }
)