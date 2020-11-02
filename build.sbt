import sbt._
import Dependencies._
import Repositories._

scalaOrganization in ThisBuild := "org.typelevel"
scalaVersion in ThisBuild := Versions.scala

scalacOptions ++= Seq(
        "-deprecation",
        "-encoding", "UTF-8",
        "-explaintypes",
        "-unchecked",
        "-feature",
        "-target:jvm-1.8",
        "-language:existentials",
        "-language:higherKinds",
        "-language:implicitConversions",
        "-Xfatal-warnings",
        "-Xfuture",
        "-Ywarn-unused-import",
        "-Xlint",
        "-Yno-predef",
        "-Yno-imports",
        "-Ypartial-unification",
        "-Yliteral-types"
)

wartremoverWarnings ++= Warts.unsafe

resolvers += sonatypeSnapshots
