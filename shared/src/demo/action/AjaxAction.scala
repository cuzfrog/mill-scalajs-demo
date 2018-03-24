package demo.action

import demo.model.Session
import demo.{Deserializer, Serializer}
import diode.Action
import play.api.libs.json.{Json, Writes}

sealed trait AjaxAction extends Action with Product with Serializable

final case class AjaxRequest(nextAction: Action) extends AjaxAction
final case class AjaxResponse(nextAction: Action) extends AjaxAction

object AjaxRequest{
  implicit val serializer: Serializer[AjaxRequest] = new Serializer[AjaxRequest]{
    override def serialize(request: AjaxRequest): String = Json.toJson(request).as[String]
  }

  private implicit val residentWrites: Writes[AjaxRequest] = Json.writes[AjaxRequest]
}
object AjaxResponse{
  implicit val deserializer: Deserializer[AjaxResponse] = ???
}

final case class StoreSession(session: Session, nextAction: Action) extends AjaxAction