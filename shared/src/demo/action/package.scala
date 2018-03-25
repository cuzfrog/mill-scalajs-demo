package demo

import demo.model.{Credential, Session}
import diode.Action
import play.api.libs.json._

package object action {

  case object DummyAction extends Action with Product with Serializable

  private final val ACTION_CLASS_PATH: String = "action-class"
  private final val ACTION_CONTENT_PATH: String = "action-content"

  private[action] implicit def actionWrites[A <: Action]: Writes[A] = (action: A) => {
    println(s"Try to serialize: $action")
    val actionClass = JsString(action.getClass.getName)
    val content = action match {
      case action: AjaxAction => Json.toJson(action.nextAction)
      case action: Authenticate => Json.toJson(action.credential)
      case action: StoreSession => Json.toJson(action.session)
      case unserializable => throw new IllegalArgumentException(s"Unserializable action: $unserializable")
    }
    val jsValue = Json.obj(
      ACTION_CLASS_PATH -> actionClass,
      ACTION_CONTENT_PATH -> content
    )
    jsValue
  }

  private[action] implicit def actionReads[A <: Action]: Reads[A] = (json: JsValue) => {
    println(s"Try to deserialize: $json")
    val actionClass = (json \ ACTION_CLASS_PATH).get.validate[String]
    def content = (json \ ACTION_CONTENT_PATH).get
    actionClass.flatMap {
      case clazz if clazz == classOf[AjaxRequest].getName => content.validate[Action].map(AjaxRequest(_))
      case clazz if clazz == classOf[AjaxResponse].getName => content.validate[Action].map(AjaxResponse(_))
      case clazz if clazz == classOf[Authenticate].getName => content.validate[Credential].map(Authenticate)
      case clazz if clazz == classOf[StoreSession].getName => content.validate[Session].map(StoreSession)
      case unserializable => throw new IllegalArgumentException(s"Unserializable action: $unserializable")
    }.map(_.asInstanceOf[A])
  }
}
