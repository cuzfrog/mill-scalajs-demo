package demo.model

import demo.Validator
import demo.action._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

final case class LoginFormModel(data: LoginFormData = LoginFormData(),
                                accountErrMsg: String = "", passwordErrMsg: String = "",
                                submitButton: ButtonModel = ButtonModel()) extends Model

object LoginFormModel {

  import LoginFormValidationAction._

  implicit val loginFormValidator: Validator[LoginFormModel] = (model: LoginFormModel) => Future {
    println(s"Validate: ${model.data}")
    if (!model.data.account.matches("""\w+@\w+(\.\w+)+""")) InvalidAccount("Must be an email address.")
    else if (model.data.password.lengthCompare(8) < 0) InvalidPassword("Password must be at lease 8 chars")
    else Valid(AjaxRequest(Authenticate(model.data)))
  }
}