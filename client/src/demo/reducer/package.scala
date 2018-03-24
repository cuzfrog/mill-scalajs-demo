package demo

import demo.model.AppStore.HandlerFunction

import scala.concurrent.ExecutionContextExecutor

package object reducer {
  private[reducer] implicit val executionContext: ExecutionContextExecutor =
    scala.scalajs.concurrent.JSExecutionContext.Implicits.queue

  private[demo]  def getReducers: Seq[HandlerFunction] = Seq(//order counts!!
    new AjaxReducer(),
    new NavigationReducer(),
    new LoinFormReducer()
  )
}
