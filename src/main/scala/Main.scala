import fp.model.{Airport, Country, Runway}
import scala.io.Source
import anorm._


object Main extends App {



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

    //parseCsvFile(runway_file,Runway.fromStrings)
    //parseCsvFile(airport_file,Airport.fromStrings)
    //fp.model.GuiProgramOne.display()
  }

