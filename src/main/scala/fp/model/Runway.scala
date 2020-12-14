package fp.model
import fp.model.Runway.RunwayID

case class Runway private (id:RunwayID)

object Runway {
  final case class RunwayID private[model](list: List[Int])
        object RunwayID{
          def build(list: List[Int]):Either[String,RunwayID] = list.size match {
            case 6 => Right(RunwayID(list))
            case _ => Left("RunwayID must be a list of 6 integers")
          }
        }

  def fromStrings(strs:Array[String])={
    RunwayID.build(strs(0).map(_.asDigit).toList)
  }
}
