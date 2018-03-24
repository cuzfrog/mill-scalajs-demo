package demo.reducer

import demo.ValidationContext._
import demo.action._
import demo.action.{UserAction, ValidationAction}
import demo.model.{AppStore, LoginFormModel, RootModel}
import diode.{ActionHandler, Effect}
import monocle.macros.syntax.lens._

private[reducer] final class LoinFormReducer extends ActionHandler[RootModel, LoginFormModel](AppStore.zoomTo(_.loginForm)) {

  import LoginFormAction._
  import LoginFormValidationAction._

  override protected def handle = {
    case action: LoginFormAction => action match {
      case AccountInput(v) => updated(value.lens(_.data.account).set(v))
      case PasswordInput(v) => updated(value.lens(_.data.password).set(v))
      case SubmitButtonClick =>
        updated(value.lens(_.submitButton.isLoading).set(true), Effect(value.validate))
      case LoggedInClear =>
        updated(value.lens(_.data.password).set("").lens(_.submitButton.isLoading).set(false))
      case LoginFailed(msg) => ???
    }
    case action: ValidationAction => action match {
      case Valid(nextAction) => updated(value.copy(accountErrMsg = "", passwordErrMsg = ""), Effect.action(nextAction))
      case InvalidAccount(errMsg) => updated(value.copy(accountErrMsg = errMsg), Effect.action(LoggedInClear))
      case InvalidPassword(errMsg) => updated(value.copy(passwordErrMsg = errMsg), Effect.action(LoggedInClear))
    }
  }
}
