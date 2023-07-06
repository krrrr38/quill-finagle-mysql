val scala2Version = "2.13.11"

lazy val root = project
  .in(file("."))
  .settings(
    name := "quill-finagle-mysql",
    organization := "com.krrrr38",
    versionScheme := Some("early-semver"),
    homepage := None,
    licenses := List(
      ("Apache License 2.0", url("https://www.apache.org/licenses/LICENSE-2.0"))
    ),
    developers := Nil,
    scalaVersion := scala2Version,
    libraryDependencies ++= Seq(
      "io.getquill" %% "quill-sql" % "4.6.1",
      "com.twitter" %% "finagle-mysql" % "22.12.0",
      "org.scalatest" %% "scalatest" % "3.2.16" % Test,
      "ch.qos.logback" % "logback-classic" % "1.4.7" % Test
    ),
    Test / parallelExecution := false
  )
