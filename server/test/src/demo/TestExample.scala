package demo

import utest._

object TestExample extends TestSuite {
  val tests = Tests{
    "test-example" - {
      println("this is a test example")
    }
  }
}
