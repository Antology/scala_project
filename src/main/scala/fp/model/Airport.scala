package fp.model
import doobie._
import doobie.implicits._
import cats.effect.IO
import cats.implicits.catsSyntaxTuple2Semigroupal
import doobie.util.fragment
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


  def fromStrings (strs:Array[String]):Either[String,Airport]={

      val airportID = AirportID.build(strs(0).map(_.asDigit).toList).toOption
      val airportIdent = AirportIdent.build(strs(1).toList).toOption
      val airportType = AirportType.build(strs(2)).toOption
      val airportName = strs(3)

      (airportID, airportIdent,airportType,airportName) match {
        case (Some(id), Some(ident),Some(typ),name) => Right(Airport(id, ident, typ, name))
        case _ => Left("Error")
      }


  }
  def toSqlStr(airport:Airport):fragment.Fragment={
    val id = airport.id.list.mkString("")
    val ident =  airport.airportIdent.list.mkString("")
    val airportType = airport.airportType.toString
    val airportName = airport.airportName
    sql"""INSERT INTO public.airport (id,ident,type,name) VALUES ($id,$ident,$airportType,$airportName)"""
  }
}






