package demo.reducer

import demo.ValidationContext._
import demo.action.{ValidationAction, _}
import demo.model.{ClientRootModel, ClientStore, LoginFormModel}
import diode.{ActionHandler, Effect}
import monocle.macros.syntax.lens._
import monocle.function.At.at

import scala.concurrent.Future

private[reducer] final class LoinFormReducer extends ActionHandler[ClientRootModel, LoginFormModel](ClientStore.zoomTo(_.loginForm)) {

  import LoginFormAction._
  import LoginFormValidationAction._

  override protected def handle = {
    case action: LoginFormAction => action match {
      case AccountInput(v) => updated(value.lens(_.data.account).set(v))
      case PasswordInput(v) => updated(value.lens(_.data.password).set(v))
      case SubmitButtonClick =>
        val nextAction = if(value.enableClientValidation) value.data.validate else Future(Valid(value.data))
        updated(value.lens(_.submitButton.isLoading).set(true), Effect(nextAction))
      case EnableValidationCheckboxChange(checked) =>
        updated(value.copy(enableClientValidation = checked))
      case LoginClear =>
        updated(value.lens(_.data.password).set("").lens(_.submitButton.isLoading).set(false))
      case LoginFailed(msg) =>
        println(s"Login failed with message: $msg")
        updated(value.lens(_.errMsg).composeLens(at("Failed")).set(Some(msg)), Effect.action(LoginClear))
    }
    case action: ValidationAction => action match {
      case Valid(data) =>
        updated(value.copy(errMsg = Map.empty), Effect.action(AjaxRequest(Authenticate(data))))
      case InvalidAccount(errMsg) =>
        updated(value.lens(_.errMsg).composeLens(at("Account")).set(Some(errMsg)), Effect.action(LoginClear))
      case InvalidPassword(errMsg) =>
        updated(value.lens(_.errMsg).composeLens(at("Password")).set(Some(errMsg)), Effect.action(LoginClear))
    }
  }
}
