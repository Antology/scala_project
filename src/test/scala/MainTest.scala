import Main._
import org.scalatest.matchers.must.Matchers

class MainTest extends org.scalatest.flatspec.AnyFlatSpec with Matchers{
  "parse_airport" should "parse airports.csv" in{
    scala.io.Source.fromFile("resources/airports.csv").getLines.drop(1).toList(0).split(";").head mustBe ("6523")
  }
  "parse_country" should "parse countries.csv" in{
    scala.io.Source.fromFile("resources/countries.csv").getLines.drop(1).toList(0).split(";").head mustBe ("302672")
  }
  "parse_runway" should "parse runways.csv" in{
    scala.io.Source.fromFile("resources/runways.csv").getLines.drop(1).toList(0).split(";").head mustBe ("269408")
  }
}

