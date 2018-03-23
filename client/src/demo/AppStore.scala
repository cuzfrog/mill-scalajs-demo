package demo

import diode.react.ReactConnector
import diode.{ActionHandler, Circuit}

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
      case LoginFormAction.LoginFormSubmit => updated(value) //todo: implement submit action
    }
  }

  override protected def actionHandler: HandlerFunction = composeHandlers(loginFormReducer)
}

