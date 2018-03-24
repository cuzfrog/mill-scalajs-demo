package demo.reducer

import monocle.macros.syntax.lens._
import demo.validation.ValidationContext._
import demo.{AppStore, LoginFormModel, RootModel, UserAction}
import diode.ActionHandler
import UserAction.LoginFormAction._

private[demo] final class LoinFormReducer extends ActionHandler[RootModel, LoginFormModel](AppStore.zoomTo(_.loginForm)){

  override protected def handle = {
    case AccountInput(v) => updated(value.copy(account = v))
    case PasswordInput(v) => updated(value.copy(password = v))
    case LoginFormSubmit => value.validate match {
      case errorMsg if errorMsg.nonEmpty =>
        println(s"validation failed: $errorMsg")
        updated(value.copy(errorMsg = errorMsg))
      case _ =>
        println("validation successful")
        updated(value.copy(errorMsg = Seq.empty).value.lens(_.submitButton.isLoading).modify(_ => true))
    }
  }
}
