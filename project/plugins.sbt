resolvers ++= Seq(
  Classpaths.typesafeResolver,
  "sonatype" at "http://oss.sonatype.org/content/repositories/releases/"
)

libraryDependencies += "com.github.siasia" % "xsbt-web-plugin_2.9.2" % "0.12.0-0.2.11.1"

addSbtPlugin("com.github.seratch" %% "testgenerator" % "1.1.0")

addSbtPlugin("com.typesafe.sbt" % "sbt-scalariform" % "1.0.1")

