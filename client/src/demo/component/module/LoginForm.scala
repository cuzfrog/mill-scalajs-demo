package demo.component.module

import demo.action.{LoginFormAction => action}
import demo.component.CssStyle.Color
import demo.component.button.PrimaryButton
import demo.component.element.{Container, EmptyDiv, Message}
import demo.component.input.{EmailTextInput, PasswordTextInput}
import demo.model.{ClientStore, LoginFormModel}
import sri.react._
import sri.web.vdom.tagsPrefix_<^._

private[component] final class LoginForm extends ComponentP[LoginForm.Props] {
  override def render(): ReactRenders = {
    <.div()(
      EmailTextInput("login-account-input", _.loginForm.data.account, action.AccountInput),
      PasswordTextInput("login-password-input", _.loginForm.data.password, action.PasswordInput),
      PrimaryButton("login-submit-btn", "Submit", _.loginForm.submitButton, action.SubmitButtonClick),
      messageBlock(props.loginFormModel.accountErrMsg),
      messageBlock(props.loginFormModel.passwordErrMsg)
    )
  }

  private def messageBlock(msg: String):ReactElement = Container(
    if(!msg.isEmpty) Message(Color.Danger)(msg) else EmptyDiv()
  )
}

private[component] object LoginForm {
  final case class Props(loginFormModel: LoginFormModel)

  def apply(): ReactElement = {
    ClientStore.connect(_.loginForm) { proxy =>
      CreateElement[LoginForm](Props(proxy()))
    }
  }
}