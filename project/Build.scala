import sbt._
import sbt.Keys._
import com.typesafe.sbt.SbtScalariform._

object Build extends Build {
  lazy val root = Project(
    "benchmarkPlots",
    file("."),
    settings = commonSettings ++ Seq(
      libraryDependencies ++= Seq(
        )
    ) 
  )

  def formattingPreferences = {
    import scalariform.formatter.preferences._
    FormattingPreferences()
      .setPreference(AlignParameters, true)
      .setPreference(AlignSingleLineCaseStatements, true)
      .setPreference(DoubleIndentClassDeclaration, true)
      .setPreference(PlaceScaladocAsterisksBeneathSecondAsterisk, true)
      .setPreference(RewriteArrowSymbols, true)
  }

  lazy val formatSettings = scalariformSettings ++ Seq(
    ScalariformKeys.preferences in Compile := formattingPreferences,
    ScalariformKeys.preferences in Test    := formattingPreferences
  )

  def commonSettings = 
    Defaults.defaultSettings ++
    formatSettings ++
  Seq(
    organization := "umich",
    scalaVersion := "2.10.1",
    scalacOptions ++= Seq(
      "unchecked",
      "-deprecation",
      "-Xlint",
      "-language:_",
      "-target:jvm-1.7",
      "-encoding", "UTF-8",
      "-feature"
    ),
    libraryDependencies ++= Seq(
      Dependencies.Compile.scalazCore,
      Dependencies.Compile.joglMain,
      Dependencies.Compile.gluegenRt
    ),
    resolvers ++= Seq(
      Resolver.sonatypeRepo("releases"),
      Resolver.sonatypeRepo("snapshots")
    ),
    fork := true
  )

  object Dependencies {
    object Compile {
      val joglMain = "org.jogamp.jogl" % "jogl-all-main" % "2.0-rc11"
      val gluegenRt = "org.jogamp.gluegen" % "gluegen-rt-main" % "2.0-rc11"
      val scalazCore = "org.scalaz" %% "scalaz-core" % "7.0.0"
    }
  }
}


