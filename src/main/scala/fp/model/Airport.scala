package fp.model
import fp.model.Airport.{AirportID, AirportIdent, AirportType}

case class Airport private (id:AirportID,airportIdent:AirportIdent,airportType:AirportType,airportName: String)

object Airport {

  final case class AirportID private(list: List[Int])
        object AirportID{
          def build(list:List[Int]): Either[String,AirportID] = list.size match {
            case 4 => Right(AirportID(list))
            case 5 => Right(AirportID(list))
            case 6 => Right(AirportID(list))
            case _ => Left("AirportID must be a list of 4,5 or 6 integers")
          }
  }

  final case class AirportIdent private(list: List[Char])
        object AirportIdent{
          def build(list: List[Char]): Either[String,AirportIdent] = list.size match {
            case 3 => Right(AirportIdent(list))
            case 4 => Right(AirportIdent(list))
            case _ => Left("AirportIdent must be a List of char")
          }
        }

  sealed trait AirportType
      object AirportType{
        case object Heliport extends AirportType
        case object SmallAirport extends AirportType
        case object SeaplaneBase extends AirportType
        case object Balloonport extends AirportType
        case object Closed extends AirportType
        case object MediumAirport extends AirportType
        case object LargeAirport extends AirportType
        def build(string: String):Either[String,AirportType] = string match {
          case "heliport" => Right(Heliport)
          case "small_airport" => Right(SmallAirport)
          case "seaplane_base" => Right(SeaplaneBase)
          case "balloonport" => Right(Balloonport)
          case "closed" => Right(Closed)
          case "medium_airport" => Right(MediumAirport)
          case "large_airport" => Right(LargeAirport)
          case _ => Left("Airport must be one of the defined case object")
        }

      }


  def fromStrings (strs:Array[String]):Either[String,Airport]={ //si liste contient 1 element L, si AirportID.build(L) renvoie une instance A d'AirportID, alors je renvoie Airport(A){
    if (strs.size == 4) {
      val airportID = AirportID.build(strs(0).map(_.asDigit).toList).toOption
      val airportIdent = AirportIdent.build(strs(1).toList).toOption
      val airportName = strs(3)
      val airportType = AirportType.build(strs(2)).toOption


      (airportID, airportIdent,airportType,airportName) match {
        case (Some(id), Some(ident),Some(typ),name) => Right(Airport(id, ident, typ, name))
        //case (None, None) => Left("id and ident non valide")
        //case (None, Some(ident)) => Left(s"Airport (ident:$ident) -> id non valide")
        //case (Some(id), None) => Left(s"Airport (id:$id) -> ident non valide")
      }
    }
    else {Left(s"${strs.mkString(";")} ne contient pas assez d'éléments pour construire un aéroport")}
  }
}

//Simplifier avec "For comprehension"
//Dans case classe airport, le champ name de type AirportName => Option[AirportName]


/* def fromStrings (strs:Array[String]):Either[String,Airport]={ //si liste contient 1 element L, si AirportID.build(L) renvoie une instance A d'AirportID, alors je renvoie Airport(A){
    if (strs.size == 4) {
      val airportID = AirportID.build(strs(0).map(_.asDigit).toList).toOption
      val airportIdent = AirportIdent.build(strs(1).toList).toOption
      val airportName = strs(3)


      (airportID, airportIdent) match {
        case (Some(id), Some(ident)) => Right(Airport(id, ident, null, null))
        case (None, None) => Left("id and ident non valide")
        case (None, Some(ident)) => Left(s"Airport (ident:$ident) -> id non valide")
        case (Some(id), None) => Left(s"Airport (id:$id) -> ident non valide")
      }
    }
    else {Left(s"${strs.mkString(";")} ne contient pas assez d'éléments pour construire un aéroport")}
  }
}*/





