package demo.service

import demo.SerializationContext._
import demo.action.{AjaxRequest, AjaxResponse, ProcessRequest}
import demo.model.ServerStore

import scala.concurrent.{Future, Promise}
import scala.concurrent.ExecutionContext.Implicits.global

private final class RequestHandlerImpl extends RequestHandler {
  override def handle(header: Map[String, String],
                      requestData: String): Future[String] = {
    val request = requestData.deserilize[AjaxRequest]
    val promise = Promise.apply[AjaxResponse]()
    ServerStore.dispatch(ProcessRequest(promise, request.nextAction))
    promise.future.map(_.serialize)
  }
}
