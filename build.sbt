name := "OMPL4j"

version := "0.1"

scalaSource in Compile <<= baseDirectory(_ / "src")

// scalaSource in Test <<= baseDirectory(_ / "tests")

scalaVersion := "2.10.2"

// libraryDependencies += "commons-logging" % "commons-logging" % "1.1.3+"

scalacOptions ++= Seq("-deprecation", "-feature")