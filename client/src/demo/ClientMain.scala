package demo

import demo.component.GreetingView
import sri.web.ReactDOM
import org.scalajs.dom

object ClientMain {
  def main(args: Array[String]): Unit = {
    ReactDOM.render(GreetingView(), dom.document.getElementById("app"))
    println("Client render done!")
  }
}