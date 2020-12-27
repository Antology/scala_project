import doobie._
import doobie.implicits._
import cats.effect.IO
import cats.implicits.catsSyntaxTuple2Semigroupal
import fp.model.{Airport, Country, Runway}

import scala.concurrent.ExecutionContext
import scala.io.Source

object Main extends App {

  implicit val cs = IO.contextShift(ExecutionContext.global)

  val xa = Transactor.fromDriverManager[IO](
    "org.postgresql.Driver", "jdbc:postgresql:project", "postgres", "azer"
  )
  def Create_tables() {
    val drop_airport =
      sql"""DROP TABLE IF EXISTS public.airport""".update.run

    val create_airport =
      sql"""
    CREATE TABLE public.airport (
      id   SERIAL,
      name VARCHAR NOT NULL UNIQUE,
      age  SMALLINT)""".update.run

    val drop_country =
      sql"""DROP TABLE IF EXISTS public.country""".update.run

    val create_country =
      sql"""
    CREATE TABLE public.country (
      id   SERIAL,
      name VARCHAR NOT NULL UNIQUE,
      age  SMALLINT)""".update.run

    val drop_runway =
      sql"""DROP TABLE IF EXISTS public.runway""".update.run

    val create_runway =
      sql"""
    CREATE TABLE public.runway (
      id   SERIAL,
      name VARCHAR NOT NULL UNIQUE,
      age  SMALLINT)""".update.run

    (drop_airport,create_airport).mapN(_+_).transact(xa).unsafeRunSync
    (drop_country,create_country).mapN(_+_).transact(xa).unsafeRunSync
    (drop_runway,create_runway).mapN(_+_).transact(xa).unsafeRunSync

  }

  val runway_file = "resources/runways.csv"
  val airport_file = "resources/airports.csv"
  val coutry_file = "resources/countries.csv"

  def parseCsvFile[A](fileName: String, parser: Array[String] => Either[String, A]) = {
    val data = Source.fromFile(fileName).getLines().drop(1).foreach {
      line =>
        val columns = line.split(";")
        val parse = parser(columns)
    }
  }
  Create_tables()
  parseCsvFile(airport_file,Airport.fromStrings)
  parseCsvFile(runway_file,Runway.fromStrings)
  parseCsvFile(coutry_file,Country.fromStrings)
  fp.model.GuiProgramOne.display()
  }

