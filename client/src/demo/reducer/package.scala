package demo

import scala.concurrent.ExecutionContextExecutor

package object reducer {
  private[demo] implicit val executionContext: ExecutionContextExecutor = scala.scalajs.concurrent.JSExecutionContext.Implicits.queue
}
