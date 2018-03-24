package demo

import demo.model.Credential
import diode.Action
import play.api.libs.json._

package object action {

  case object DummyAction extends Action with Product with Serializable

  private final val ACTION_CLASS_PATH: String = "action-class"
  private final val ACTION_CONTENT_PATH: String = "action-content"

  private[action] implicit val actionWrites: Writes[Action] = (action: Action) => {
    val actionClass = JsString(action.getClass.getName)
    val content = action match {
      case action: AjaxAction => Json.toJson(action.nextAction)
      case action: Authenticate => Json.toJson(action.credential)
      case unserializable => throw new IllegalArgumentException(s"Unserializable action: $unserializable")
    }
    JsObject(Seq(
      ACTION_CLASS_PATH -> actionClass,
      ACTION_CONTENT_PATH -> content
    ))
  }

  private[action] implicit val actionReads: Reads[Action] = (json: JsValue) => {
    val actionClass = (json \ ACTION_CLASS_PATH).get.validate[String]
    def content = (json \ ACTION_CONTENT_PATH).get
    actionClass.flatMap {
      case clazz if clazz == classOf[AjaxRequest].getName => content.validate[Action].map(AjaxRequest(_))
      case clazz if clazz == classOf[AjaxResponse].getName => content.validate[Action].map(AjaxResponse(_))
      case clazz if clazz == classOf[Authenticate].getName => content.validate[Credential].map(Authenticate)
      case unserializable => throw new IllegalArgumentException(s"Unserializable action: $unserializable")
    }
  }
}
