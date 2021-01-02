package fp.model
import doobie.implicits.toSqlInterpolator
import doobie.util.fragment
import fp.model.Airport.AirportID
import fp.model.Runway.RunwayID

case class Runway private (id:RunwayID,airport_ref: AirportID,surface:String,le_ident:String)

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
    val surface = strs(5)
    val le_ident = strs(8)

    (runwayID,airport_ref,surface,le_ident) match {
      case (Some(id),Some(airport_ref),surface,le_ident) => Right(Runway(id,airport_ref,surface,le_ident))
      case _ => Left("Error")
    }
  }

  def toSqlStr(runway: Runway):fragment.Fragment={
    val id = runway.id.list.mkString("")
    val airport_ref = runway.airport_ref.list.mkString("")
    val surface = runway.surface
    val le_ident = runway.le_ident
    sql"INSERT INTO public.runway (id,airport_ref,surface,le_ident) VALUES ($id,$airport_ref,$surface,$le_ident)"
  }
}
