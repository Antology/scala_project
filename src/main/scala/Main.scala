import fp.model.{Airport, Country}

import scala.io.Source
import org.mongodb.scala._


object Main extends App {
  val mongoClient: MongoClient = MongoClient("mongodb://localhost")
  val data_airport = Source.fromFile("resources/airports.csv").getLines
    data_airport.drop(1)
    data_airport.foreach{
    line =>
      println(line)
      val columns = line.split(";")
      val airport = Airport.fromStrings(columns)
      println(airport)
  }
  val data_country = Source.fromFile("resources/countries.csv").getLines
  data_country.drop(1)
  data_country.foreach{
    line =>
      println(line)
      val columns = line.split(";")
      val country = Country.fromStrings(columns)
      println(country)
  }
  val data_runway = Source.fromFile("resources/runways.csv").getLines
  data_runway.drop(1)
  data_runway.foreach{
    line =>
      println(line)
      val columns = line.split(";")
      val runway = Runway.fromStrings(columns)
      println(runway)
  }
}
