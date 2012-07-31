import sbt._
import sbt.Keys._
import com.typesafe.sbtscalariform.ScalariformPlugin._
import sbtassembly.Plugin._
import AssemblyKeys._

object Build extends Build {
  import Extra._

  val arch = "macosx" // "windows-amd64" "windows-i586" "linux-amd64" "linux-i586"
  
  lazy val root = Project(
    "benchmarkPlots",
    file("."),
    settings = commonSettings ++ Seq(
      libraryDependencies ++= Seq(
	 "org.jzy3d" % "jzy3d" % "0.9" from "http://www.jzy3d.org/release/0.9a3/org.jzy3d-0.9.jar" 
      ),
      mergeStrategy in assembly <<= (mergeStrategy in assembly) { (old) =>
	{
          case x if x startsWith "org/eclipse/swt" => MergeStrategy.first
          case x => old(x)
	}
      } 
    ) ++ 
    addZipJar("org.jzy3d" % "jzy3d-deps" % "0.9" from "http://www.jzy3d.org/release/0.9a3/org.jzy3d-0.9-dependencies.zip", Compile) ++
    addZipJar("org.jzy3d" % "jzy3d-native" % "0.9" from "http://www.jzy3d.org/release/0.9a3/org.jzy3d-0.9-binaries-%s.zip".format(arch), Compile) 
  )

  def commonSettings = 
    Defaults.defaultSettings ++ 
  Seq(
    organization := "mx.umich.fie.dep",
    scalaVersion := "2.10.0-M6",
    scalacOptions ++= Seq("-unchecked", "-deprecation"),
    libraryDependencies ++= Seq(
      Dependencies.Compile.Scalaz,
      Dependencies.Test.Specs2,
      Dependencies.Test.ScalaCheck,
      Dependencies.Test.Mockito,
      Dependencies.Test.Hamcrest
    )
  ) ++
  scalariformSettings ++
  assemblySettings 

  object Dependencies {

    object Compile {
      val Config = "com.typesafe" % "config" % "0.5.0"
      val Scalaz = "org.scalaz" % "scalaz-core_2.10.0-M6" % "7.0.0-M1"
    }

    object Test {
      val Specs2 = "org.specs2" %% "specs2" % "1.11" % "test"
      val ScalaCheck = "org.scalacheck" % "scalacheck_2.10.0-M6" % "1.10.0" % "test"
      val Mockito = "org.mockito" % "mockito-all" % "1.9.5-rc1" % "test"
      val Hamcrest = "org.hamcrest" % "hamcrest-all" % "1.3" % "test"
    }
  }
}


object Extra extends Plugin {

  /** Adds a dependency on a ZIP file, and modifies `unmanagedJars` to include the contents */
  def addZipJar(module: ModuleID, config: Configuration) = Seq[Project.Setting[_]](
    libraryDependencies += module,
    unmanagedJars in config <++= (update, cacheDirectory, target) map {
      (updateReport, cache, target) =>
	val moduleReports = updateReport.configuration(config.name).get.modules
      moduleReports.find(mr => (mr.module.organization, mr.module.name) == (module.organization, module.name)) match {
	case Some(x) =>
	  val zipFile = x.artifacts.head._2	
	val cachedUnzip = FileFunction.cached(cache / "zipJar", inStyle = FilesInfo.lastModified, outStyle = FilesInfo.exists) { (in: Set[File]) =>
          IO.unzip(in.head, target)
	}
	cachedUnzip(Set(zipFile)).toSeq
	case None =>
	  sys.error("could not find artifact [%s] in [%s]".format(module, moduleReports.map(_.module).mkString("\n")))
      }
    }
  )
}
