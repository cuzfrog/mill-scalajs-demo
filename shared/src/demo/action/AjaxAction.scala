package demo.action

import demo.{Deserializer, Serializer}
import diode.Action
import play.api.libs.json.{Json, Reads, Writes}

sealed trait AjaxAction extends Action with Product with Serializable{
  def nextAction: Action
}

final case class AjaxRequest(nextAction: Action) extends AjaxAction
final case class AjaxResponse(nextAction: Action) extends AjaxAction

object AjaxRequest{
  implicit val serializer: Serializer[AjaxRequest] = (request: AjaxRequest) => Json.toJson(request).as[String]
  implicit val deserializer: Deserializer[AjaxRequest] = (requestData: String) =>
    Json.fromJson[AjaxRequest](Json.parse(requestData))
      .recoverTotal(err => AjaxRequest(JsonError(err.errors.toString())))

  private implicit val requestWrites: Writes[AjaxRequest] = Json.writes[AjaxRequest]
  private implicit val requestReads: Reads[AjaxRequest] = Json.reads[AjaxRequest]
}
object AjaxResponse{
  implicit val serializer: Serializer[AjaxResponse] = (request: AjaxResponse) => Json.toJson(request).as[String]
  implicit val deserializer: Deserializer[AjaxResponse] = (responseText: String) =>
    Json.fromJson[AjaxResponse](Json.parse(responseText))
      .recoverTotal(err => AjaxResponse(JsonError(err.errors.toString())))

  private implicit val requestWrites: Writes[AjaxResponse] = Json.writes[AjaxResponse]
  private implicit val responseReads: Reads[AjaxResponse] = Json.reads[AjaxResponse]
}


final case class AjaxError(lastAttempt: AjaxAction, e: Throwable) extends AjaxAction{
  override def nextAction: Action = lastAttempt.nextAction
}