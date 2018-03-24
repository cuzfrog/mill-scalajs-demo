package demo

import demo.model.ClientStore.HandlerFunction

import scala.concurrent.ExecutionContextExecutor

package object reducer {
  private[reducer] implicit val executionContext: ExecutionContextExecutor =
    scala.scalajs.concurrent.JSExecutionContext.Implicits.queue

  def getReducers: Seq[HandlerFunction] = Seq(//order counts!!
    new AjaxReducer(),
    new NavigationReducer(),
    new LoinFormReducer()
  )
}
