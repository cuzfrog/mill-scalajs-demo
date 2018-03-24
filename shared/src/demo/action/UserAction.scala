package demo.action

import diode.Action

sealed trait UserAction extends Action with Product with Serializable

sealed trait LoginFormAction extends UserAction
object LoginFormAction{
  final case class AccountInput(value: String) extends LoginFormAction
  final case class PasswordInput(value: String) extends LoginFormAction
  final case object SubmitButtonClick extends LoginFormAction
  final case object LoginClear extends LoginFormAction
  final case class LoginFailed(msg: String) extends LoginFormAction
}