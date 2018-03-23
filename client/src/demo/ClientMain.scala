package demo

import demo.UserInput.LoginFormInput
import demo.component.{GreetingView, LoginForm, TextInput}
import sri.web.ReactDOM
import org.scalajs.dom
import sri.react.ReactElement

object ClientMain {

  def main(args: Array[String]): Unit = try {

    ReactDOM.render(LoginForm(), dom.document.getElementById("app"))
    println("Client render done!")
  } catch {
    case t: Throwable => t.printStackTrace()
      throw t
  }
}