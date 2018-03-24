package demo.model

import diode.Circuit

object ServerStore extends Circuit[ServerRootModel] {
  override protected def initialModel = ServerRootModel(
    sessions = Seq.empty
  )

  override protected def actionHandler = composeHandlers(demo.reducer.getReducers: _*)
}
