package demo.model

import demo.reducer.ServerReducers
import diode.Circuit

object ServerStore extends Circuit[ServerRootModel] {
  override protected def initialModel = ServerRootModel(
    sessions = Seq.empty
  )

  override protected def actionHandler = composeHandlers(ServerReducers.getReducers: _*)
}
