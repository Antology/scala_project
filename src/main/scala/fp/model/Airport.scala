package fp.model
import fp.model.Airport.AirportID

case class Airport private (id:AirportID,airportType:AirportType,airportName: String)

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

  def fromStrings (strs:Array[String])={ //si liste contient 1 element L, si AirportID.build(L) renvoie une instance A d'AirportID, alors je renvoie Airport(A)
  strs.length match {
    case 18 => AirportID.build((strs(0).toList.flatMap{x => x.toInt::Nil}))
    case _ => None
  }
//Faire methode qui construit une liste de digit a partir d'un str 6523 -> List(6,5,2,3)
  }
}








