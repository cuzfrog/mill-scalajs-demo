package demo.action

import demo.{Deserializer, Serializer}
import diode.Action
import play.api.libs.json.Json

sealed trait AjaxAction extends Action with Product with Serializable{
  def nextAction: Action
}

final case class AjaxRequest(nextAction: Action) extends AjaxAction
final case class AjaxResponse(nextAction: Action) extends AjaxAction

object AjaxRequest{
  implicit val serializer: Serializer[AjaxRequest] = (request: AjaxRequest) => Json.toJson(request).toString()
  implicit val deserializer: Deserializer[AjaxRequest] = (requestData: String) =>
    Json.fromJson[AjaxRequest](Json.parse(requestData))
      .recoverTotal(err => AjaxRequest(JsonError(err.errors.toString())))
}
object AjaxResponse{
  implicit val serializer: Serializer[AjaxResponse] = (request: AjaxResponse) => Json.toJson(request).toString()
  implicit val deserializer: Deserializer[AjaxResponse] = (responseText: String) =>
    Json.fromJson[AjaxResponse](Json.parse(responseText))
      .recoverTotal(err => AjaxResponse(JsonError(err.errors.toString())))
}


final case class AjaxError(lastAttempt: AjaxAction, e: Throwable) extends AjaxAction{
  override def nextAction: Action = lastAttempt.nextAction
}