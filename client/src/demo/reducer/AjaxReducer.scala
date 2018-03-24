package demo.reducer

import demo.AjaxContext._
import demo.action.AjaxAction
import demo.action._
import demo.model.{ClientStore, ClientRootModel, Session}
import diode.{ActionHandler, Effect}

private[reducer] final class AjaxReducer extends ActionHandler[ClientRootModel, Session](ClientStore.zoomTo(_.session)) {
  override protected def handle = {
    case action: AjaxAction => action match {
      case request: AjaxRequest => effectOnly(Effect(request.ajaxSend(value)))
      case AjaxResponse(nextAction) => effectOnly(Effect.action(nextAction))

      case StoreSession(session) => updated(session, Effect.action(LoginAction))

      case AjaxError(log) =>
        println(log)
        noChange
    }
  }
}
