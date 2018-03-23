package demo.component

import demo.UserAction.LoginFormAction
import sri.react._
import sri.web.vdom.tagsPrefix_<^._

final class LoginForm extends ComponentP[LoginForm.Props] {
  override def render(): ReactRenders = {
    <.div()(
      TextInput("account-input", _.loginForm.account, LoginFormAction.AccountInput),
      TextInput("password-input", _.loginForm.password, LoginFormAction.PasswordInput),
      Button("login-submit-btn", "Submit", _.loginForm.submitButton, LoginFormAction.LoginFormSubmit)
    )
  }
}

object LoginForm{
  final case class Props()

  def apply(): ReactElement = CreateElement[LoginForm](Props())
}