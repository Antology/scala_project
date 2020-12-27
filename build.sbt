name := "scala_project";
version := "0.1";
scalaVersion := "2.13.1";
libraryDependencies += "org.scalatest" % "scalatest_2.13" % "3.1.0" % "test";
libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.2"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.2" % "test"
libraryDependencies += "org.scala-lang.modules" %% "scala-swing" % "3.0.0"
libraryDependencies ++= Seq(

  // Start with this one
  "org.tpolecat" %% "doobie-core"      % "0.9.0",

  // And add any of these as needed
  "org.tpolecat" %% "doobie-h2"        % "0.9.0",          // H2 driver 1.4.200 + type mappings.
  "org.tpolecat" %% "doobie-hikari"    % "0.9.0",          // HikariCP transactor.
  "org.tpolecat" %% "doobie-postgres"  % "0.9.0",          // Postgres driver 42.2.12 + type mappings.
  "org.tpolecat" %% "doobie-quill"     % "0.9.0",          // Support for Quill 3.5.1
  "org.tpolecat" %% "doobie-specs2"    % "0.9.0" % "test", // Specs2 support for typechecking statements.
  "org.tpolecat" %% "doobie-scalatest" % "0.9.0" % "test"  // ScalaTest support for typechecking statements.

)