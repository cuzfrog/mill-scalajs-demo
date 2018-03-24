package demo.reducer

import demo.ValidationContext._
import demo.action.{ValidationAction, _}
import demo.model.{ClientRootModel, ClientStore, LoginFormModel}
import diode.{ActionHandler, Effect}
import monocle.macros.syntax.lens._

private[reducer] final class LoinFormReducer extends ActionHandler[ClientRootModel, LoginFormModel](ClientStore.zoomTo(_.loginForm)) {

  import LoginFormAction._
  import LoginFormValidationAction._

  override protected def handle = {
    case action: LoginFormAction => action match {
      case AccountInput(v) => updated(value.lens(_.data.account).set(v))
      case PasswordInput(v) => updated(value.lens(_.data.password).set(v))
      case SubmitButtonClick =>
        updated(value.lens(_.submitButton.isLoading).set(true), Effect(value.data.validate))
      case LoginClear =>
        updated(value.lens(_.data.password).set("").lens(_.submitButton.isLoading).set(false))
      case LoginFailed(msg) =>
        println(s"Login failed with message: $msg")
        effectOnly(Effect.action(LoginClear))
    }
    case action: ValidationAction => action match {
      case Valid(data) =>
        updated(value.copy(accountErrMsg = "", passwordErrMsg = ""), Effect.action(AjaxRequest(Authenticate(data))))
      case InvalidAccount(errMsg) => updated(value.copy(accountErrMsg = errMsg), Effect.action(LoginClear))
      case InvalidPassword(errMsg) => updated(value.copy(passwordErrMsg = errMsg), Effect.action(LoginClear))
    }
  }
}
