logLevel := Level.Warn

resolvers ++= Seq(
  "Sonatype snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/",
  "Sonatype releases"  at "https://oss.sonatype.org/content/repositories/releases/"
)

addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.9.0")

addSbtPlugin("com.artima.supersafe" % "sbtplugin" % "1.1.0-RC6")

addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.6.0")

