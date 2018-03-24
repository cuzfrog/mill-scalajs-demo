package demo.reducer

import demo.AjaxAction.{AjaxRequest, AjaxResponse}
import demo.{AppStore, Data, RootModel, Session}
import diode.{ActionHandler, Effect}
import demo.AjaxContext._

private final class AjaxReducer extends ActionHandler[RootModel, Session](AppStore.zoomTo(_.session)) {
  override protected def handle = {
    case AjaxRequest(data: Data) => effectOnly(Effect(data.ajaxSend(value)))
    case AjaxResponse(session, nextAction) => updated(session, Effect.action(nextAction))
  }
}
