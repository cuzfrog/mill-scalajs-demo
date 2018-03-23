package demo

import demo.validation.ValidationContext.Validator

sealed trait Model extends Product with Serializable

final case class ButtonModel(isLoading: Boolean = false) extends Model

final case class RootModel(greetingWords: String,
                           loginForm: LoginFormModel)

final case class LoginFormModel(account: String = "",
                                password: String = "",
                                errorMsg: String = "",
                                submitButton: ButtonModel = ButtonModel()) extends Model

object LoginFormModel {
  implicit val loginFormValidator: Validator[LoginFormModel] = (model: LoginFormModel) => {
    if (!model.account.matches("""w+@w+(\.w+)+""") || model.password.lengthCompare(8) < 0) {
      Some("Account or password validation failed.")
    } else None
  }
}