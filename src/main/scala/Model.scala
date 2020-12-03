/*object Model {
  //------------------------------------------------


  //------------------------------------------------
  case class CountryCode2 private(list: List[String])
  object CountryCode2{
    def build(list:List[String]): Option[CountryCode2] = list.size match {
      case 2 => Some(CountryCode2(list))
      case _ => None
    }
  }

  sealed trait CountryName

  sealed trait CountryID
  case class CountryID6 private(list: List[Digit]) extends CountryID
  object CountryID6{
    def build(list:List[Digit]): Option[CountryID6] = list.size match {
      case 6 => Some(CountryID6(list))
      case _ => None
    }
  }

  sealed trait Continent
  object Continent {

    case object NA extends Continent

    case object SA extends Continent

    case object EU extends Continent

    case object AS extends Continent

    case object AF extends Continent

    case object OC extends Continent

  }

  sealed trait CountryWikilink

  sealed trait CountryKeywords

  //------------------------------------------------






  sealed trait AirportName

  sealed trait AirportLatitudeDeg

  sealed trait AirportLongitudeDeg

  sealed trait AirportElevationFt

  sealed trait AirportIsoCountry

  sealed trait AirportIsoRegion

  sealed trait AirportMunicipality

  sealed trait AirportScheduledService
  case object AirportYes extends AirportScheduledService
  case object AirportNo extends AirportScheduledService

  sealed trait AirportGPSCode

  sealed trait AirportIataCode

  sealed trait AirportLocalCode

  sealed trait AirportHomeLink

  sealed trait AirportWikilink

  sealed trait AirportKeywords

  //------------------------------------------------

  sealed trait RunwaysID
  case class RunwaysID6 private(list: List[Digit]) extends RunwaysID
  object RunwaysID6{
    def build(list:List[Digit]): Option[RunwaysID6] = list.size match {
      case 6 => Some(RunwaysID6(list))
      case _ => None
    }
  }

  sealed trait RunwaysAirportRef
  case class RunwaysAirportRef4 private(list: List[Digit]) extends RunwaysAirportRef
  object RunwaysAirportRef4{
    def build(list:List[Digit]): Option[RunwaysAirportRef4] = list.size match {
      case 4 => Some(RunwaysAirportRef4(list))
      case _ => None
    }
  }
  case class RunwaysAirportRef5 private(list: List[Digit]) extends RunwaysAirportRef
  object RunwaysAirportRef5{
    def build(list:List[Digit]): Option[RunwaysAirportRef5] = list.size match {
      case 5 => Some(RunwaysAirportRef5(list))
      case _ => None
    }
  }

  sealed trait RunwaysAirportIdent

  sealed trait RunwaysWidthFt

  sealed trait RunwaysSurface

}
*/