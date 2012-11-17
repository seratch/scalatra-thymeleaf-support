import sbt._ 
import Keys._

object ScalatraExtBuild extends Build {

  lazy val thymeleaf = Project(
    id = "main",
    base = file("."),
    settings = Defaults.defaultSettings ++ Seq(
      sbtPlugin := false,
      organization := "com.github.seratch",
      name := "scalatra-thymeleaf-support",
      version := "2.0.1",
      scalaVersion := "2.9.2",
      crossScalaVersions := Seq("2.9.2", "2.9.1"),
      libraryDependencies <++= (scalaVersion) { scalaVersion =>
        _scalatraDependencies ++ Seq(
          "org.thymeleaf"               %  "thymeleaf" % "2.0.8",
          "net.sourceforge.nekohtml"    %  "nekohtml"  % "1.9.15",
          "nz.net.ultraq.web.thymeleaf" % "thymeleaf-layout-dialect" % "1.0.4" % "test",
          "org.scalatest"               %% "scalatest" % "1.7.2" % "test"
        )
      },
      publishTo <<= version { (v: String) =>
        val nexus = "https://oss.sonatype.org/"
        if (v.trim.endsWith("SNAPSHOT")) Some("snapshots" at nexus + "content/repositories/snapshots")
        else Some("releases"  at nexus + "service/local/staging/deploy/maven2")
      },
      publishMavenStyle := true,
      publishArtifact in Test := false,
      pomIncludeRepository := { x => false },
      pomExtra := _commonPomExtra,
      scalacOptions ++= Seq("-deprecation", "-unchecked")
    ) ++ _jettyOrbitHack
  )

  lazy val thymeleafDemo = Project(
    id = "demo", 
    base = file("demo"), 
    settings = Defaults.defaultSettings ++ Seq(
      sbtPlugin := false,
      organization := "demo",
      name := "demo",
      version := "0.1.0-SNAPSHOT",
      scalaVersion := "2.9.2",
      libraryDependencies <++= (scalaVersion) { scalaVersion =>
        _scalatraDependencies ++ _containerDepenedencies ++ Seq(
          "org.thymeleaf"            %  "thymeleaf" % "2.0.8",
          "net.sourceforge.nekohtml" %  "nekohtml"  % "1.9.15"
        )
      },
      scalacOptions ++= Seq("-deprecation", "-unchecked")
    ) ++ _jettyOrbitHack
  ).dependsOn(thymeleaf)

  lazy val _jettyOrbitHack = Seq(
    ivyXML := <dependencies>
      <exclude org="org.eclipse.jetty.orbit" />
    </dependencies>
  )

  lazy val _scalatraVersion = "2.0.4"
  lazy val _servletApi = "javax.servlet" % "servlet-api" % "2.5"

  lazy val _scalatraDependencies = Seq(
    "org.scalatra"      %  "scalatra_2.9.1" % _scalatraVersion,
    "org.scalatra"      %  "scalatra-scalatest_2.9.1" % _scalatraVersion % "test",
    "ch.qos.logback"    %  "logback-classic" % "1.0.2" % "runtime",
    _servletApi % "provided"
  )

  lazy val _containerDepenedencies = Seq(
    _servletApi % "container",
    "org.eclipse.jetty" % "jetty-webapp" % "7.6.4.v20120524" % "container" 
      exclude("org.eclipse.jetty.orbit", "javax.servlet")
  )

  lazy val _commonPomExtra = 
    <url>https://github.com/seratch/scalatra-thymeleaf-support</url>
    <licenses>
      <license>
        <name>Apache License, Version 2.0</name>
        <url>http://www.apache.org/licenses/LICENSE-2.0.html</url>
        <distribution>repo</distribution>
      </license>
    </licenses>
    <scm>
      <url>git@github.com:seratch/scalatra-thymeleaf-support.git</url>
      <connection>scm:git:git@github.com:seratch/scalatra-thymeleaf-support.git</connection>
    </scm>
    <developers>
      <developer>
        <id>seratch</id>
        <name>Kazuhiro Sera</name>
        <url>https://github.com/seratch</url>
      </developer>
    </developers>


}


