package fp.model

sealed trait AirportType {
  case object heliport extends AirportType
  case object small_airport extends AirportType
  case object seaplane_base extends AirportType
  case object balloonport extends AirportType
  case object closed extends AirportType
  case object medium_airport extends AirportType
  case object large_airport extends AirportType
}
