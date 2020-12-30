package fp.utilities
import doobie._
import doobie.implicits._
import cats.effect.IO
import cats.implicits.catsSyntaxTuple2Semigroupal
import shapeless.PolyDefns.Compose.composeCase
import shapeless.syntax.std.tuple.unitTupleOps

import scala.concurrent.ExecutionContext

object Sql {

  implicit val cs = IO.contextShift(ExecutionContext.global)

  val xa = Transactor.fromDriverManager[IO](
    "org.postgresql.Driver", "jdbc:postgresql:project", "postgres", "azer"
  )

  def Create_tables(): Any={

    val drop_airport =
      sql"""DROP TABLE IF EXISTS public.airport""".update.run

    val create_airport =
      sql"""
    CREATE TABLE public.airport (
      number   SERIAL,
      id VARCHAR,
      ident VARCHAR,
      type  VARCHAR,
      name VARCHAR,
      iso VARCHAR )""".update.run

    val drop_country =
      sql"""DROP TABLE IF EXISTS public.country""".update.run

    val create_country =
      sql"""
    CREATE TABLE public.country (
      number   SERIAL,
      id   VARCHAR ,
      code VARCHAR ,
      name  VARCHAR)""".update.run

    val drop_runway =
      sql"""DROP TABLE IF EXISTS public.runway""".update.run

    val create_runway =
      sql"""
    CREATE TABLE public.runway (
      number   SERIAL,
      id   VARCHAR,
      airport_ref VARCHAR )""".update.run

    (drop_airport,create_airport).mapN(_+_).transact(xa).unsafeRunSync
    (drop_country,create_country).mapN(_+_).transact(xa).unsafeRunSync
    (drop_runway,create_runway).mapN(_+_).transact(xa).unsafeRunSync

  }

  def QuerySearch(string: String):List[String]={sql"""SELECT name FROM public.airport WHERE (iso = $string) """.query[String].to[List].transact(xa).unsafeRunSync}
  def NameToIso(string: String):String={sql"SELECT code FROM public.country WHERE (name = $string)".query[String].to[List].transact(xa).unsafeRunSync().mkString("")}
  def NameToID(string:String):String ={sql"SELECT id FROM public.airport WHERE (name = $string)".query[String].to[List].transact(xa).unsafeRunSync().mkString("")}
  def IDToName(string:String):String ={sql"SELECT name FROM public.airport WHERE (id = $string)".query[String].to[List].transact(xa).unsafeRunSync().mkString("")}
  def GetRunways(string: String):List[String]={sql"SELECT id FROM public.runway WHERE (airport_ref = $string)".query[String].to[List].transact(xa).unsafeRunSync()}
  def HighNbAirports():List[(Int,String)]={
    val countryList:Seq[String] = sql"SELECT code FROM public.country".query[String].to[List].transact(xa).unsafeRunSync()
    val countList:Seq[Int] = countryList.map(x=>sql"SELECT name FROM public.airport WHERE (iso = $x)".query[String].to[List].transact(xa).unsafeRunSync().size)
    val temp:Seq[(Int,String)] = countList zip countryList
    temp.sortWith((a, b) => countList.indexOf(a._1) < countList.indexOf(b._1)).reverse.take(10).toList
  }
  def LowNbAirports():List[(Int,String)]={
    val countryList:Seq[String] = sql"SELECT code FROM public.country".query[String].to[List].transact(xa).unsafeRunSync()
    val countList:Seq[Int] = countryList.map(x=>sql"SELECT name FROM public.airport WHERE (iso = $x)".query[String].to[List].transact(xa).unsafeRunSync().size)
    val temp:Seq[(Int,String)] = countList zip countryList
    temp.sortWith((a, b) => countList.indexOf(a._1) < countList.indexOf(b._1)).take(10).toList
  }
  def SelResultAirportNb (string: String):String={
    string match {
      case "Highest number of airports" => fp.utilities.Sql.HighNbAirports().mkString(" // ")
      case "Lowest number of airports" => fp.utilities.Sql.LowNbAirports().mkString(" // ")
    }
  }

}
