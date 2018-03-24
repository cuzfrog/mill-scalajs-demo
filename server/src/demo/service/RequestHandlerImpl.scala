package demo.service

import scala.concurrent.Future
import demo.SerializationContext._
import demo.action.AjaxRequest
import demo.model.ServerStore

private final class RequestHandlerImpl extends RequestHandler{
  override def handle(header: Map[String, String],
                      requestData: String): Future[String] = {
    val request = requestData.deserilize[AjaxRequest]
    ServerStore.dispatch(request.nextAction)
    ???
  }
}
