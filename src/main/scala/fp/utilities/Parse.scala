package fp.utilities

import doobie.implicits._
import doobie.Fragment
import scala.io.Source

object Parse {

  val xa = Sql.xa

  def parseCsvFile[A](fileName: String, parser: Array[String] => Either[String, A], toSqlStr : A =>Fragment) = {
    Source.fromFile(fileName)
      .getLines()
      .drop(1)
      .foreach { line =>
        val columns = line.split(";")
        val parsed = parser(columns)
        parsed match {
          case Left(parsed) => println("Error: "+parsed)
          case Right(parsed)=>{toSqlStr(parsed).update.run.transact(xa).unsafeRunSync()}
        }
      }
  }

}
