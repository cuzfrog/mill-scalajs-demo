package demo

import demo.model.ServerStore

package object reducer {
  def getReducers: Seq[ServerStore.HandlerFunction] = ???
}
