package demo.component

import demo.UserInput.LoginFormInput
import sri.react._
import sri.web.vdom.tagsPrefix_<^._

final class LoginForm extends ComponentP[LoginForm.Props] {
  override def render(): ReactRenders = {
    <.div()(
      TextInput("account-input", _.loginForm.account, LoginFormInput.AccountInput),
      TextInput("password-input", _.loginForm.password, LoginFormInput.PasswordInput)
    )
  }
}

object LoginForm{
  final case class Props()

  def apply(): ReactElement = CreateElement[LoginForm](Props())
}