package demo

import diode.Action

private sealed trait UserAction extends Action with Product with Serializable
private object UserAction{
  sealed trait LoginFormAction extends UserAction
  object LoginFormAction{
    final case class AccountInput(value: String) extends LoginFormAction
    final case class PasswordInput(value: String) extends LoginFormAction
    final case object SubmitButtonClick extends LoginFormAction
    final case object LoggedInClear extends LoginFormAction
  }
}

private sealed trait AjaxAction extends Action with Product with Serializable
private object AjaxAction{
  final case class AjaxRequest(data: Data) extends AjaxAction
  final case class AjaxResponse(session: Session, nextAction: Action) extends AjaxAction
}

private sealed trait ValidationAction extends Action with Product with Serializable
private object ValidationAction{
  sealed trait LoginFormValidationAction extends ValidationAction
  object LoginFormValidationAction{
    final case class Valid(nextAction: Action) extends ValidationAction
    final case class InvalidAccount(msg: String) extends ValidationAction
    final case class InvalidPassword(msg: String) extends ValidationAction
  }
}

private sealed trait NavigationAction extends Action with Product with Serializable
private object NavigationAction{
  final case object Login extends NavigationAction
}