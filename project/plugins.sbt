logLevel := Level.Warn

resolvers += Resolver.sbtPluginRepo("releases")
resolvers += Resolver.url("bintray-sbt-plugins", url("https://dl.bintray.com/eed3si9n/sbt-plugins/"))(Resolver.ivyStylePatterns)
resolvers += Resolver.bintrayIvyRepo("com.eed3si9n", "sbt-plugins")

addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.6")
