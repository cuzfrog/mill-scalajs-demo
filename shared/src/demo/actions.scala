package demo

import diode.Action

sealed trait UserAction extends Action with Product with Serializable

object UserAction{

  sealed trait LoginFormAction extends UserAction

  object LoginFormAction{
    final case class AccountInput(value: String) extends LoginFormAction
    final case class PasswordInput(value: String) extends LoginFormAction
    final case object LoginFormSubmit extends LoginFormAction
  }
}

sealed trait AjaxAction extends Action