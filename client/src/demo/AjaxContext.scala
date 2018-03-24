package demo

import demo.action.AjaxAction.{AjaxRequest, AjaxResponse}
import demo.model.Session
import org.scalajs.dom.ext.Ajax

import scala.concurrent.Future
import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue

private trait AjaxSender[T <: AjaxRequest] {
  def send(request: T)(implicit session: Session): Future[AjaxResponse]
}

private object AjaxContext {

  implicit final class AjaxOps[T <: AjaxRequest](request: T)(implicit ajaxSender: AjaxSender[T]) {
    def ajaxSend(session: Session): Future[AjaxResponse] = ajaxSender.send(request)(session)
  }

  import SerializationContext._

  implicit val dataAjaxSender: AjaxSender[AjaxRequest] = new AjaxSender[AjaxRequest] {
    override def send(request: AjaxRequest)(implicit session: Session): Future[AjaxResponse] = {
      val future = Ajax.post("api/request", data = request.serialize, headers = session.toHttpHeader)
      future.map(_.responseText.deserilize[AjaxResponse])
    }
  }
}
