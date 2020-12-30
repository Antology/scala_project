package fp.model
import doobie.implicits.toSqlInterpolator
import doobie.util.fragment
import fp.model.Airport.AirportID
import fp.model.Runway.RunwayID

case class Runway private (id:RunwayID,airport_ref: AirportID)

object Runway {
  final case class RunwayID private[model](list: List[Int])
        object RunwayID{
          def build(list: List[Int]):Either[String,RunwayID] = list.size match {
            case 6 => Right(RunwayID(list))
            case _ => Left("RunwayID must be a list of 6 integers")
          }
        }

  def fromStrings(strs:Array[String]):Either[String,Runway]={
    val runwayID = RunwayID.build(strs(0).map(_.asDigit).toList).toOption
    val airport_ref = AirportID.build(strs(1).map(_.asDigit).toList).toOption

    (runwayID,airport_ref) match {
      case (Some(id),Some(airport_ref)) => Right(Runway(id,airport_ref))
      case _ => Left("Error")
    }
  }

  def toSqlStr(runway: Runway):fragment.Fragment={
    val id = runway.id.list.mkString("")
    val airport_ref = runway.airport_ref.list.mkString("")
    sql"INSERT INTO public.runway (id,airport_ref) VALUES ($id,$airport_ref)"
  }
}
