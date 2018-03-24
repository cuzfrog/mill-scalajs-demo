package demo.reducer

import demo.AjaxContext._
import demo.action.AjaxAction
import demo.action.AjaxAction.{AjaxRequest, AjaxResponse, StoreSession}
import demo.model.{AppStore, RootModel, Session}
import diode.{ActionHandler, Effect}

private[reducer]  final class AjaxReducer extends ActionHandler[RootModel, Session](AppStore.zoomTo(_.session)) {
  override protected def handle = {
    case action: AjaxAction => action match {
      case request: AjaxRequest => effectOnly(Effect(request.ajaxSend(value)))
      case AjaxResponse(nextAction) => effectOnly(Effect.action(nextAction))

      case StoreSession(session, nextAction) => updated(session, Effect.action(nextAction))
    }
  }
}
