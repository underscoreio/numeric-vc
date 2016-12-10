import sbt._

object Dependencies {
  lazy val scalaTest  = "org.scalatest"  %% "scalatest"  % "3.0.0" % Test
  lazy val shapeless  = "com.chuusai"    %% "shapeless"  % "2.3.2"
  lazy val scalaCheck = "org.scalacheck" %% "scalacheck" % "1.13.4" % Test
}

object Options {
  lazy val scalac = Seq(
    "-deprecation",
    "-encoding", "UTF-8",
    "-unchecked",
    "-feature",
    "-language:implicitConversions",
    "-language:postfixOps",
    "-Ywarn-dead-code",
    "-Ywarn-value-discard",
    "-Xlint",
    "-Xfatal-warnings"
  )
}

