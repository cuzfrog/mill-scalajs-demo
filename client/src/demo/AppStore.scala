package demo

import diode.react.ReactConnector
import diode.{ActionHandler, Circuit}

object AppStore extends Circuit[RootModel] with ReactConnector[RootModel] {
  override protected def initialModel: RootModel = RootModel(
    greetingWords = "welcome!",
    loginForm = LoginFormData()
  )

  private val loginFormReducer = new ActionHandler(zoomTo(_.loginForm)) {
    import UserInput.LoginFormInput
    override protected def handle = {
      case LoginFormInput.AccountInput(v) => updated(value.copy(account = v))
      case LoginFormInput.PasswordInput(v) => updated(value.copy(password = v))
    }
  }

  override protected def actionHandler: HandlerFunction = composeHandlers(loginFormReducer)
}

