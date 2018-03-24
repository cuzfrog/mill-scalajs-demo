package demo.action

import diode.Action

private[demo] sealed trait ValidationAction extends Action with Product with Serializable

private[demo] object ValidationAction {
  sealed trait LoginFormValidationAction extends ValidationAction
  object LoginFormValidationAction{
    final case class Valid(nextAction: Action) extends ValidationAction
    final case class InvalidAccount(msg: String) extends ValidationAction
    final case class InvalidPassword(msg: String) extends ValidationAction
  }
}