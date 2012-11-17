externalResolvers ~= (_.filter(_.name != "Scala-Tools Maven2 Repository"))

resolvers ++= Seq(
  Classpaths.typesafeResolver,
  "sonatype" at "http://oss.sonatype.org/content/repositories/releases/",
  "idea" at "http://mpeltonen.github.com/maven/",
  "less" at "http://repo.lessis.me"
)

libraryDependencies <+= sbtVersion(v => "com.github.siasia" %% "xsbt-web-plugin" % (v+"-0.2.11.1"))

addSbtPlugin("com.github.seratch" %% "testgenerator" % "1.1.0")

addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.1.0")

addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "2.0.0")

addSbtPlugin("com.typesafe.sbt" % "sbt-scalariform" % "1.0.0")

// for sonatype publishment

addSbtPlugin("com.jsuereth" % "xsbt-gpg-plugin" % "0.6")

