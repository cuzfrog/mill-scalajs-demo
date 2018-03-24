package demo.reducer

import demo.AjaxAction.{AjaxRequest, AjaxResponse, StoreSession}
import demo._
import diode.{ActionHandler, Effect}
import demo.AjaxContext._

private final class AjaxReducer extends ActionHandler[RootModel, Session](AppStore.zoomTo(_.session)) {
  override protected def handle = {
    case action: AjaxAction => action match {
      case request: AjaxRequest => effectOnly(Effect(request.ajaxSend(value)))
      case AjaxResponse(nextAction) => effectOnly(Effect.action(nextAction))

      case StoreSession(session, nextAction) => updated(session, Effect.action(nextAction))
    }
  }
}
