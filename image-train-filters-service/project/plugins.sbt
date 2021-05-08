logLevel := Level.Warn

resolvers += Resolver.sbtPluginRepo("releases")
resolvers += Resolver.url("bintray-sbt-plugins", url("https://dl.bintray.com/eed3si9n/sbt-plugins/"))(Resolver.ivyStylePatterns)
resolvers += Resolver.bintrayIvyRepo("com.eed3si9n", "sbt-plugins")

addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.6")
addSbtPlugin("org.scoverage" % "sbt-scoverage" % "1.7.3")
addSbtPlugin("com.github.sbt" % "sbt-jacoco" % "3.2.0")
addSbtPlugin("org.scoverage" % "sbt-coveralls" % "1.2.7")

// plugins.sbt
val jacocoVersion = "0.8.5"
dependencyOverrides ++= Seq(
  "org.jacoco" % "org.jacoco.core" % jacocoVersion,
  "org.jacoco" % "org.jacoco.report" % jacocoVersion,
  "org.jacoco" % "org.jacoco.agent" % jacocoVersion)