package fp.model

import fp.model.Airport.AirportType

sealed trait AirportType {
  case object Heliport extends AirportType
  case object Small_airport extends AirportType
  case object Seaplane_base extends AirportType
  case object Balloonport extends AirportType
  case object Closed extends AirportType
  case object Medium_airport extends AirportType
  case object Large_airport extends AirportType
/*
  def toAt(string: String):AirportType = string match {
    case heliport => val airportType:AirportType=Heliport
  }
  */

}
