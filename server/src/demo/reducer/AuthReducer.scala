package demo.reducer

import demo.ValidationContext._
import demo.action.LoginFormAction.LoginFailed
import demo.action.{AjaxResponse, Authenticate, DummyAction, LoginFormValidationAction, ProcessRequest, StoreSession}
import demo.model.{ServerRootModel, ServerStore, Session}
import demo.service.AuthService
import diode.{ActionHandler, Effect}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

private[reducer] final class AuthReducer(val authService: AuthService)
  extends ActionHandler[ServerRootModel, Seq[Session]](ServerStore.zoomTo(_.sessions)) {
  override protected def handle = {
    case ProcessRequest(promise, action: Authenticate) =>
      import LoginFormValidationAction._
      val futureAction = action.credential.validate.flatMap {
        case Valid(credential) => authService.auth(credential).map {
          case Some(session) => AjaxResponse(StoreSession(session))
          case None => AjaxResponse(LoginFailed("No such credential"))
        }
        case invalidAction => Future(AjaxResponse(invalidAction))
      }
      promise.completeWith(futureAction)
      val serverNextAction = futureAction.map { response =>
        response.nextAction match {
          case storeSession: StoreSession => storeSession
          case _ => DummyAction
        }
      }
      effectOnly(Effect(serverNextAction))

    case StoreSession(session) => updated(value :+ session)
  }
}
