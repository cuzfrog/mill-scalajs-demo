package demo

import demo.UserInput.LoginFormInput
import demo.component.{GreetingView, LoginForm, TextInput}
import sri.web.ReactDOM
import org.scalajs.dom
import sri.react.ReactElement

object ClientMain {
  def main(args: Array[String]): Unit = {
    val topComponent: ReactElement = TextInput("account-input", _.loginForm.account, LoginFormInput.AccountInput)

    ReactDOM.render(topComponent, dom.document.getElementById("app"))
    println("Client render done!")
  }
}