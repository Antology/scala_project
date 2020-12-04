package fp.model
import fp.model.Airport.{AirportID, AirportIdent, AirportName, AirportType}

case class Airport private (id:AirportID,airportIdent:AirportIdent,airportType:AirportType,airportName: AirportName)

object Airport {

  final case class AirportID private(list: List[Int])
        object AirportID{
          def build(list:List[Int]): Option[AirportID] = list.size match {
            case 4 => Some(AirportID(list))
            case 5 => Some(AirportID(list))
            case 6 => Some(AirportID(list))
            case _ => None
          }
  }
  final case class AirportIdent private(list: List[Char])
        object AirportIdent{
          def build(list: List[Char]): Option[AirportIdent] = list.size match {
            case 3 => Some(AirportIdent(list))
            case 4 => Some(AirportIdent(list))
            case _ => None
          }
        }

  final case class AirportType private(string: String)
      object AirportType{
        def build(string: String) = Some(AirportType(string))
      }

  final case class AirportName private(string: String)
      object AirportName{
        def build(string: String) = Some(AirportName(string))
      }

  def fromStrings (strs:Array[String])={ //si liste contient 1 element L, si AirportID.build(L) renvoie une instance A d'AirportID, alors je renvoie Airport(A){
    AirportID.build(strs(0).map(_.asDigit).toList)
    AirportIdent.build(strs(1).toList)
    AirportType.build(strs(2))
    AirportName.build(strs(3))
    //case _ => None
  }
}








