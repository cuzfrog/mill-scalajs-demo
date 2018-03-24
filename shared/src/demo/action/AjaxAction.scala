package demo.action

import demo.model.Session
import demo.{Deserializer, Serializer}
import diode.Action

sealed trait AjaxAction extends Action with Product with Serializable

object AjaxAction {
  final case class AjaxRequest(nextAction: Action) extends AjaxAction
  final case class AjaxResponse(nextAction: Action) extends AjaxAction

  object AjaxRequest{
    implicit val serializer: Serializer[AjaxRequest] = ???
  }
  object AjaxResponse{
    implicit val deserializer: Deserializer[AjaxResponse] = ???
  }

  final case class StoreSession(session: Session, nextAction: Action) extends AjaxAction
}