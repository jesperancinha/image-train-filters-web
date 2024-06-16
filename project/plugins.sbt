scalaVersion := "2.13.6"

ThisBuild / assumedVersionScheme := VersionScheme.PVP
ThisBuild / assumedVersionSchemeJava := VersionScheme.EarlySemVer
ThisBuild / assumedEvictionErrorLevel := Level.Warn
ThisBuild / evictionErrorLevel := Level.Info

resolvers += Resolver.mavenLocal
resolvers += Resolver.mavenCentral
resolvers +=
  "Maven Central" at "https://repo1.maven.org/maven2/"

addSbtPlugin("com.github.sbt" % "sbt-jacoco" % "3.4.0")
addSbtPlugin("org.jesperancinha.plugins" % "omni-reporter-sbt-plugin" % "0.0.1")
addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.8")
addSbtPlugin("com.timushev.sbt" % "sbt-updates" % "0.5.3")
