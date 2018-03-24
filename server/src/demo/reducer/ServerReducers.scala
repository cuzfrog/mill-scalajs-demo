package demo.reducer

import demo.model.ServerStore
import demo.service.AuthService

object ServerReducers {
  def getReducers: Seq[ServerStore.HandlerFunction] = Seq(
    new AuthReducer(AuthService.getInstance())
  )
}
