ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

lazy val root = (project in file("."))
  .settings(
    name := "crypto-scala",
    libraryDependencies ++= Seq(
      "org.scalactic" %% "scalactic" % "3.2.15",
      "org.scalatest" %% "scalatest" % "3.2.15" % "test",
      "org.backuity.clist" %% "clist-core" % "3.5.1",
      "org.backuity.clist" %% "clist-macros" % "3.5.1" % "provided"
    )
  )
