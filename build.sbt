name := "tujr"

version := "0.1"

scalaSource in Compile <<= baseDirectory(_ / "src")

scalaVersion := "2.10.2"

scalacOptions ++= Seq("-deprecation", "-feature")