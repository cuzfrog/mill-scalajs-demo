package demo

import demo.reducer.LoinFormReducer
import diode.Circuit
import diode.react.ReactConnector

object AppStore extends Circuit[RootModel] with ReactConnector[RootModel] {
  override protected def initialModel: RootModel = RootModel(
    greetingWords = "welcome!",
    loginForm = LoginFormModel()
  )

  private val loginFormReducer = new LoinFormReducer()

  override protected def actionHandler: HandlerFunction = composeHandlers(loginFormReducer)
}

