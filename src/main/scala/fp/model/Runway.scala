package fp.model
import fp.model.Runway.RunwayID

case class Runway private (id:RunwayID)

object Runway {
  final case class RunwayID private(list: List[Int])
        object RunwayID{
          def build(list: List[Int]):Option[RunwayID] = list.size match {
            case 6 => Some(RunwayID(list))
            case _ => None
          }
        }

  def fromStrings(strs:Array[String])={
    RunwayID.build(strs(0).map(_.asDigit).toList)
  }
}
