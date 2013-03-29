addSbtPlugin("com.github.seratch" %% "testgenerator" % "1.1.0")

addSbtPlugin("org.scalatra.sbt" % "scalatra-sbt" % "0.2.0")

addSbtPlugin("com.typesafe.sbt" % "sbt-scalariform" % "1.0.0")

libraryDependencies <+= sbtVersion(v => "com.github.siasia" %% "xsbt-web-plugin" % (v+"-0.2.11"))


