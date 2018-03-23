package demo

import demo.component.MainPage
import org.scalajs.dom
import sri.web.ReactDOM

object ClientApp {

  def main(args: Array[String]): Unit = try {

    ReactDOM.render(MainPage(), dom.document.getElementById("app"))
    println("Client render done!")
  } catch {
    case t: Throwable => t.printStackTrace()
      throw t
  }
}