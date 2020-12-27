package fp.model
import fp.model.Country.{CountryCode, CountryID}

case class Country private (id:CountryID,code:CountryCode,name:String)
object Country {

  final case class CountryID private(list: List[Int])

  object CountryID {
    def build(list: List[Int]): Either[String, CountryID] = list.size match {
      case 6 => Right(CountryID(list))
      case _ => Left("CountryID must be a list of 6 integers")
    }
  }

  final case class CountryCode private(list: List[Char])

  object CountryCode {
    def build(list: List[Char]): Either[String, CountryCode] = list.size match {
      case 2 => Right(CountryCode(list))
      case _ => Left("CountryCode must be a list of 2 char")
    }
  }


  def fromStrings(strs: Array[String]): Either[String, Country] = {
    val countryID = CountryID.build(strs(0).map(_.asDigit).toList).toOption
    val countryCode = CountryCode.build(strs(1).toList).toOption
    val countryName = strs(2)

    (countryID, countryCode, countryName) match {
      case (Some(id), Some(ident), name) => Right(Country(id, ident, name))
    }
  }
}