package se.digiplant.resource.api

import org.specs2.mutable.Specification
import play.api._
import play.api.test._
import play.api.test.Helpers._

object ResourcesSpec extends Specification {

  //override def is = args(sequential = true) ^ super.is

  "Resources Controller" should {

    "return resource" in new FakeApp {
      // Put image to have something to get
      res.put(getTestFile)

      val result = Resources.at("5564ac5e3968e77b4022f55a23d36630bdeb0274.jpg")(app)(FakeRequest())

      /*val source = Source.fromFile(logo)
      val imageBytes = source.map(_.toByte).toArray
      source.close()*/

      status(result) must equalTo(OK)
      contentType(result) must beSome("image/jpeg")
    }

    "return resource in supplied source" in new FakeApp {
      // Put image to have something to get
      res.put(getTestFile, "images")

      val result = Resources.at("5564ac5e3968e77b4022f55a23d36630bdeb0274.jpg", "images")(app)(FakeRequest())

      status(result) must equalTo(OK)
      contentType(result) must beSome("image/jpeg")
    }

  }

  step {
    // Delete tmp directory on end
    Files.tmp.delete()
    success
  }
}
