package demo.model

import demo.reducer
import diode.Circuit
import diode.react.ReactConnector

object ClientStore extends Circuit[ClientRootModel] with ReactConnector[ClientRootModel] {
  override protected def initialModel: ClientRootModel = ClientRootModel(
    session = Session(),
    navigation = NavigationModel(),
    loginForm = LoginFormModel()
  )

  override protected def actionHandler: HandlerFunction = composeHandlers(reducer.getReducers: _*)
}

