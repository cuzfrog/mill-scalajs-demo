package demo

import demo.AjaxAction.AjaxResponse
import demo.NavigationAction.Login

import scala.concurrent.Future
import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue

private trait AjaxSender[T <: Data] {
  def send(data: T): Future[AjaxAction]
}

private object AjaxContext {

  implicit final class AjaxOps[T <: Data](data: T)(implicit ajaxSender: AjaxSender[T]) {
    def ajaxSend(session: Session): Future[AjaxAction] = ajaxSender.send(data)
  }

  implicit val loginFormDataAjaxSender: AjaxSender[LoginFormData] = (data: LoginFormData) => Future {
    AjaxResponse(Session(4532L), Login) //todo: implement
  }

  implicit val dataAjaxSender: AjaxSender[Data] = {
    case loginFormData: LoginFormData => loginFormDataAjaxSender.send(loginFormData)
  }
}
