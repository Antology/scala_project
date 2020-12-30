package fp.model

import fp.model.Airport.AirportType.Heliport
import fp.model.Airport.{AirportID, AirportIdent}
import fp.model.Country.CountryCode
import org.scalatest.matchers.must.Matchers

class AirportTest extends org.scalatest.flatspec.AnyFlatSpec with Matchers {
  "Airport fromStrings" should "parse a valid airport in serveral strings" in {
    Airport.fromStrings(Array("0123","00A","heliport","AP1","","","","","US")) mustBe Right(Airport(AirportID(List(0, 1, 2, 3)),AirportIdent(List('0','0','A')),Heliport,"AP1",CountryCode(List('U','S'))))
  }
}
