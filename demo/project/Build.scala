import sbt._ 
import Keys._

object ScalatraExtBuild extends Build {

  lazy val thymeleafDemo = Project(
    id = "demo", 
    base = file("demo"), 
    settings = Defaults.defaultSettings ++ Seq(
      sbtPlugin := false,
      organization := "demo",
      name := "demo",
      version := "0.1.0-SNAPSHOT",
      scalaVersion := "2.9.2",
      resolvers += "Sonatype Nexus Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
      libraryDependencies <++= (scalaVersion) { scalaVersion =>
        _scalatraDependencies ++ Seq(
          "javax.servlet"     %  "servlet-api" % "2.5" % "container",
          "org.eclipse.jetty" % "jetty-webapp" % "7.6.4.v20120524" % "container"
            exclude("org.eclipse.jetty.orbit", "javax.servlet"),
          "org.thymeleaf"            %  "thymeleaf" % "2.0.8",
          "com.github.seratch"       %  "scalatra-thymeleaf-support" % "1.0.0",
          "net.sourceforge.nekohtml" %  "nekohtml"  % "1.9.15"
        )
      },
      scalacOptions ++= Seq("-deprecation", "-unchecked")
    ) ++ _jettyOrbitHack
  )

  lazy val _jettyOrbitHack = Seq(
    ivyXML := <dependencies>
      <exclude org="org.eclipse.jetty.orbit" />
    </dependencies>
  )

  //lazy val _scalatraVersion = "2.0.4"
  lazy val _scalatraVersion = "2.1.0-SNAPSHOT"

  lazy val _scalatraDependencies = Seq(
    "org.scalatra"      %  "scalatra_2.9.1" % _scalatraVersion,
    "org.scalatra"      %  "scalatra-scalatest_2.9.1" % _scalatraVersion % "test",
    "ch.qos.logback"    %  "logback-classic" % "1.0.2" % "runtime",
    "javax.servlet"     %  "servlet-api" % "2.5" % "provided"
  )

}


