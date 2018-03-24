package demo.model

import demo.reducer
import diode.Circuit
import diode.react.ReactConnector

object AppStore extends Circuit[RootModel] with ReactConnector[RootModel] {
  override protected def initialModel: RootModel = RootModel(
    session = Session(),
    navigation = NavigationModel(),
    loginForm = LoginFormModel()
  )

  override protected def actionHandler: HandlerFunction = composeHandlers(reducer.getReducers: _*)
}

