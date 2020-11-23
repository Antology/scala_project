object Model {
  //------------------------------------------------
  sealed trait Letter
  case object `A` extends Letter
  case object `B` extends Letter
  case object `C` extends Letter
  case object `D` extends Letter
  case object `E` extends Letter
  case object `F` extends Letter
  case object `G` extends Letter
  case object `H` extends Letter
  case object `I` extends Letter
  case object `J` extends Letter
  case object `K` extends Letter
  case object `L` extends Letter
  case object `M` extends Letter
  case object `N` extends Letter
  case object `O` extends Letter
  case object `P` extends Letter
  case object `Q` extends Letter
  case object `R` extends Letter
  case object `S` extends Letter
  case object `T` extends Letter
  case object `U` extends Letter
  case object `V` extends Letter
  case object `W` extends Letter
  case object `X` extends Letter
  case object `Y` extends Letter
  case object `Z` extends Letter

  sealed trait Chiffre
  case object `0` extends Chiffre
  case object `1` extends Chiffre
  case object `2` extends Chiffre
  case object `3` extends Chiffre
  case object `4` extends Chiffre
  case object `5` extends Chiffre
  case object `6` extends Chiffre
  case object `7` extends Chiffre
  case object `8` extends Chiffre
  case object `9` extends Chiffre

  //------------------------------------------------
  case class CountryCode2 private(list: List[Letter])
  object CountryCode2{
    def build(list:List[Letter]): Option[CountryCode2] = list.size match {
      case 2 => Some(CountryCode2(list))
      case _ => None
    }
  }

  sealed trait CountryName

  sealed trait CountryID
  case class CountryID6 private(list: List[Chiffre]) extends CountryID
  object CountryID6{
    def build(list:List[Chiffre]): Option[CountryID6] = list.size match {
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

  sealed trait AirportID
  case class AirportID4 private(list: List[Chiffre]) extends AirportID
  object AirportID4{
    def build(list:List[Chiffre]): Option[AirportID4] = list.size match {
      case 4 => Some(AirportID4(list))
      case _ => None
    }
  }

  sealed trait AirportIdent

  sealed trait AirportType
  case object heliport extends AirportType
  case object small_airport extends AirportType
  case object seaplane_base extends AirportType
  case object balloonport extends AirportType
  case object closed extends AirportType
  case object medium_airport extends AirportType
  case object large_airport extends AirportType

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
  case class RunwaysID6 private(list: List[Chiffre]) extends RunwaysID
  object RunwaysID6{
    def build(list:List[Chiffre]): Option[RunwaysID6] = list.size match {
      case 6 => Some(RunwaysID6(list))
      case _ => None
    }
  }

  sealed trait RunwaysAirportRef
  case class RunwaysAirportRef4 private(list: List[Chiffre]) extends RunwaysAirportRef
  object RunwaysAirportRef4{
    def build(list:List[Chiffre]): Option[RunwaysAirportRef4] = list.size match {
      case 4 => Some(RunwaysAirportRef4(list))
      case _ => None
    }
  }
  case class RunwaysAirportRef5 private(list: List[Chiffre]) extends RunwaysAirportRef
  object RunwaysAirportRef5{
    def build(list:List[Chiffre]): Option[RunwaysAirportRef5] = list.size match {
      case 5 => Some(RunwaysAirportRef5(list))
      case _ => None
    }
  }

  sealed trait RunwaysAirportIdent

  sealed trait RunwaysWidthFt

  sealed trait RunwaysSurface

}
