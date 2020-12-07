import fp.model.{Airport, Country, Runway}

import scala.io.Source
import org.mongodb.scala._


object Main extends App {
  print()
  val mongoClient: MongoClient = MongoClient("mongodb://localhost")
  val runway_file = "resources/runways.csv"
  val airport_file = "resources/airports.csv"
  val coutry_file = "resources/countries.csv"
  def parse_airport(string: String)= {
    val data_airport = Source.fromFile(string).getLines
    data_airport.drop(1)
    data_airport.foreach {
      line =>
        println(line)
        val columns = line.split(";")
        val airport = Airport.fromStrings(columns)
        println(airport)
    }
  }
  def parse_country(string: String)= {
    val data_country = Source.fromFile(string).getLines
    data_country.drop(1)
    data_country.foreach {
      line =>
        println(line)
        val columns = line.split(";")
        val country = Country.fromStrings(columns)
        println(country)
    }
  }
  def parse_runways(string: String) = {
    val data_runway = Source.fromFile(string).getLines
    data_runway.drop(1)
    data_runway.foreach {
      line =>
        println(line)
        val columns = line.split(";")
        val runway = Runway.fromStrings(columns)
        println(runway)
    }

  }
}