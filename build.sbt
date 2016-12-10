import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "io.underscore",
      scalaVersion := "2.12.0"
    )),
    name := "numeric-vc",
    scalacOptions := Options.scalac,
    libraryDependencies ++= Seq(shapeless),
    libraryDependencies ++= Seq(scalaTest, scalaCheck)
  )
