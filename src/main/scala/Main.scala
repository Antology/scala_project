import fp.model.Airport
import scala.io.Source
import org.mongodb.scala._


object Main extends App {
  val mongoClient: MongoClient = MongoClient("mongodb://localhost")
  val data = Source.fromFile("resources/airports.csv").getLines
    data.drop(1)
    data.foreach{
    line =>
      println(line)
      val columns = line.split(",")
      val airport = Airport.fromStrings(columns)
  }
}
