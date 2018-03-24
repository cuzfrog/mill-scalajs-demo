package demo.reducer

import demo.ValidationContext._
import demo._
import diode.{ActionHandler, Effect}
import monocle.macros.syntax.lens._

private final class LoinFormReducer extends ActionHandler[RootModel, LoginFormModel](AppStore.zoomTo(_.loginForm)) {

  import UserAction.LoginFormAction._
  import demo.ValidationAction.LoginFormValidationAction._

  override protected def handle = {
    case AccountInput(v) => updated(value.lens(_.data.account).set(v))
    case PasswordInput(v) => updated(value.lens(_.data.password).set(v))
    case SubmitButtonClick =>
      updated(value.lens(_.submitButton.isLoading).set(true), Effect(value.validate))
    case LoggedInClear =>
      updated(value.lens(_.data.password).set("").lens(_.submitButton.isLoading).set(false))

    case Valid(nextAction) => updated(value.copy(accountErrMsg = "", passwordErrMsg = ""), Effect.action(nextAction))
    case InvalidAccount(errMsg) => updated(value.copy(accountErrMsg = errMsg), Effect.action(LoggedInClear))
    case InvalidPassword(errMsg) => updated(value.copy(passwordErrMsg = errMsg), Effect.action(LoggedInClear))
  }
}