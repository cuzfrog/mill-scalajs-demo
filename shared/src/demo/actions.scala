package demo

import diode.Action

trait UserInput extends Action with Product with Serializable

object UserInput{

  trait LoginFormInput extends UserInput

  object LoginFormInput{
    final case class AccountInput(value: String) extends LoginFormInput
    final case class PasswordInput(value: String) extends LoginFormInput
  }
}


trait AjaxAction extends Action