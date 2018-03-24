package demo.action

import diode.Action

sealed trait UserAction extends Action with Product with Serializable

object UserAction {
  sealed trait LoginFormAction extends UserAction
  object LoginFormAction{
    final case class AccountInput(value: String) extends LoginFormAction
    final case class PasswordInput(value: String) extends LoginFormAction
    final case object SubmitButtonClick extends LoginFormAction
    final case object LoggedInClear extends LoginFormAction
    final case class LoginFailed(msg: String) extends LoginFormAction
  }
}