package fp.model

import fp.model.Runway.RunwayID
import org.scalatest.matchers.must.Matchers

class RunwayTest extends org.scalatest.flatspec.AnyFlatSpec with Matchers {
  "Runway fromStrings" should "parse a valid runway in serveral strings" in {
    Runway.fromStrings(Array("012345")) mustBe (Some(RunwayID(List(0, 1, 2, 3, 4, 5))))
  }
}


