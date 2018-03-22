package demo

import diode.{ActionHandler, ActionResult, Circuit}
import diode.react.ReactConnector

object AppStore extends Circuit[RootModel] with ReactConnector[RootModel] {
  override protected val initialModel: RootModel = RootModel(
    loginForm = LoginForm()
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

