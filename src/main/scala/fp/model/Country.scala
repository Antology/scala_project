package fp.model
import fp.model.Country.{CountryCode, CountryID, CountryName}

case class Country private (id:CountryID,code:CountryCode,name:CountryName)

object Country {

  final case class CountryID private(list: List[Int])
        object CountryID{
         def build(list: List[Int]):Option[CountryID]=list.size match {
           case 6 => Some(CountryID(list))
           case _ => None
         }
        }

  final case class CountryCode private(list: List[Char])
        object CountryCode{
          def build(list: List[Char]):Option[CountryCode] = list.size match {
            case 2 => Some(CountryCode(list))
            case _ => None
          }

        }
  final case class CountryName private(string: String)
        object CountryName{
          def build(string: String) = Some(CountryName(string))
        }
  def fromStrings(strs:Array[String])={
    CountryID.build(strs(0).map(_.asDigit).toList)
    CountryCode.build(strs(1).toList)
    CountryName.build(strs(2))
  }
}