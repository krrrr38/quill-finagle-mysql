val scala2Version = "2.13.12"

addCommandAlias("fmt", "all scalafmtSbt scalafmt test:scalafmt")
addCommandAlias(
  "fmtCheck",
  "all scalafmtSbtCheck scalafmtCheck test:scalafmtCheck"
)

lazy val root = project
  .in(file("."))
  .settings(
    name          := "quill-finagle-mysql",
    organization  := "com.krrrr38",
    versionScheme := Some("early-semver"),
    homepage := Some(
      url("https://github.com/krrrr38/quill-finagle-mysql")
    ),
    licenses := List(
      ("Apache License 2.0", url("https://www.apache.org/licenses/LICENSE-2.0"))
    ),
    developers := List(
      Developer(
        "krrrr38",
        "Ken Kaizu",
        "k.kaizu38@gmail.com",
        url("https://krrrr38.com")
      )
    ),
    scalaVersion := scala2Version,
    libraryDependencies ++= Seq(
      "io.getquill"   %% "quill-sql"       % "4.8.3",
      "com.twitter"   %% "finagle-mysql"   % "23.11.0",
      "org.scalatest" %% "scalatest"       % "3.2.18" % Test,
      "ch.qos.logback" % "logback-classic" % "1.5.3"  % Test
    ),
    scalacOptions ++= Seq(
      "-encoding",
      "utf8",
      "-deprecation",
      "-unchecked",
      "-feature",
      "-Xlint",
      "-Xfatal-warnings",
      "-Ywarn-value-discard"
    ),
    Test / parallelExecution := false
  )
