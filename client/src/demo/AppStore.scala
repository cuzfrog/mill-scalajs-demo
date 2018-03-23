package demo

import diode.react.ReactConnector
import diode.{ActionHandler, Circuit}
import monocle.macros.syntax.lens._
import demo.validation.ValidationContext._

object AppStore extends Circuit[RootModel] with ReactConnector[RootModel] {
  override protected def initialModel: RootModel = RootModel(
    greetingWords = "welcome!",
    loginForm = LoginFormModel()
  )

  private val loginFormReducer = new ActionHandler(zoomTo(_.loginForm)) {
    import UserAction.LoginFormAction
    override protected def handle = {
      case LoginFormAction.AccountInput(v) => updated(value.copy(account = v))
      case LoginFormAction.PasswordInput(v) => updated(value.copy(password = v))
      case LoginFormAction.LoginFormSubmit => value.validate match {
        case errorMsg if errorMsg.nonEmpty =>
          println(s"validation failed: $errorMsg")
          updated(value.copy(errorMsg = errorMsg))
        case _ =>
          println("validation successful")
          updated(value.copy(errorMsg = Seq.empty).value.lens(_.submitButton.isLoading).modify(_ => true))
      }
    }
  }

  override protected def actionHandler: HandlerFunction = composeHandlers(loginFormReducer)
}

