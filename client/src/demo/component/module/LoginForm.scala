package demo.component.module

import demo.action.{LoginFormAction => action}
import demo.component.button.PrimaryButton
import demo.component.input.{EmailTextInput, PasswordTextInput}
import sri.react._
import sri.web.vdom.tagsPrefix_<^._

private[component] final class LoginForm extends ComponentP[LoginForm.Props] {
  override def render(): ReactRenders = {
    <.div()(
      EmailTextInput("login-account-input", _.loginForm.data.account, action.AccountInput),
      PasswordTextInput("login-password-input", _.loginForm.data.password, action.PasswordInput),
      PrimaryButton("login-submit-btn", "Submit", _.loginForm.submitButton, action.SubmitButtonClick)
    )
  }
}

private[component] object LoginForm{
  final case class Props()

  def apply(): ReactElement = CreateElement[LoginForm](Props())
}