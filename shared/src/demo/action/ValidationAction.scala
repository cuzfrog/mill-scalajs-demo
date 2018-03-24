package demo.action

import demo.model.Credential
import diode.Action

sealed trait ValidationAction extends Action with Product with Serializable

sealed trait LoginFormValidationAction extends ValidationAction
object LoginFormValidationAction{
  final case class Valid(credential: Credential) extends ValidationAction
  final case class InvalidAccount(msg: String) extends ValidationAction
  final case class InvalidPassword(msg: String) extends ValidationAction
}