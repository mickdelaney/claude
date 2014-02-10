import org.scalatra.sbt._

import org.scalatra.sbt.PluginKeys._

name := "Claude"

version := "0.1"

scalaVersion := "2.10.2"

resolvers ++= Seq(
  "Templemore Repository" at "http://templemore.co.uk/repo",
  "Sonatype" at "http://oss.sonatype.org/content/repositories/public",
  "Typesafe" at "http://repo.typesafe.com/typesafe/releases/"
)

libraryDependencies ++= Seq(
  "org.scalatra" %% "scalatra" % "2.2.1",
  "org.eclipse.jetty" % "jetty-webapp" % "8.1.7.v20120910" % "container;compile",
  "org.eclipse.jetty.orbit" % "javax.servlet" % "3.0.0.v201112011016" % "container;compile" artifacts Artifact("javax.servlet", "jar", "jar")
)

seq(ScalatraPlugin.scalatraWithJRebel : _*)

seq(scalateSettings : _*)

mainClass := Some("claude.WebApp")
