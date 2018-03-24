package demo.reducer

import demo.AjaxContext._
import demo.action.LoginFormAction.LoginFailed
import demo.action.{AjaxAction, _}
import demo.model.{ClientRootModel, ClientStore, Session}
import diode.{ActionHandler, Effect}

private[reducer] final class AjaxReducer extends ActionHandler[ClientRootModel, Session](ClientStore.zoomTo(_.session)) {
  override protected def handle = {
    case action: AjaxAction => action match {
      case request: AjaxRequest =>
        val ajaxFuture = request.ajaxSend(value).recover {
          case e => AjaxError(request, e)
        }
        effectOnly(Effect(ajaxFuture))
      case AjaxResponse(nextAction) => effectOnly(Effect.action(nextAction))
      case AjaxError(lastAttempt, e) =>
        e.printStackTrace()
        val action = lastAttempt.nextAction match {
          case _: Authenticate => LoginFailed(e.getMessage)
          case _ => DummyAction
        }
        effectOnly(Effect.action(action))
    }
    case StoreSession(session) => updated(session, Effect.action(LoginAction))
  }
}
