package demo

import demo.component.BasicView
import sri.web.ReactDOM
import org.scalajs.dom

object ClientMain {
  def main(args: Array[String]): Unit = {
    ReactDOM.render(BasicView(), dom.document.getElementById("app"))
    println("Client render done!")
  }
}