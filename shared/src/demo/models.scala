package demo

import demo.validation.ValidationContext.Validator

sealed trait Model extends Product with Serializable

final case class ButtonModel(isLoading: Boolean = false) extends Model

final case class RootModel(greetingWords: String,
                           loginForm: LoginFormModel)

final case class LoginFormModel(account: String = "guest@domain.com",
                                password: String = "00000000",
                                errorMsg: Seq[String] = Seq.empty,
                                submitButton: ButtonModel = ButtonModel()) extends Model

object LoginFormModel {
  implicit val loginFormValidator: Validator[LoginFormModel] = (model: LoginFormModel) => {
    val accountResult =
      if (!model.account.matches("""\w+@\w+(\.\w+)+""")) Some("Must be an email address.") else None
    val passwordResult =
      if(model.password.lengthCompare(8) < 0) Some("Password must be at lease 8 chars") else None
    (accountResult ++ passwordResult).toSeq
  }
}