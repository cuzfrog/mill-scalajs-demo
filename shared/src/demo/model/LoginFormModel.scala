package demo.model

import demo.Validator
import demo.action.AjaxAction.AjaxRequest
import demo.action.ServerAction

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

final case class LoginFormModel(data: LoginFormData = LoginFormData(),
                                accountErrMsg: String = "", passwordErrMsg: String = "",
                                submitButton: ButtonModel = ButtonModel()) extends Model

object LoginFormModel {
  import demo.action.ValidationAction.LoginFormValidationAction._

  implicit val loginFormValidator: Validator[LoginFormModel] = (model: LoginFormModel) => Future {
    println(s"Validate: ${model.data}")
    if (!model.data.account.matches("""\w+@\w+(\.\w+)+""")) InvalidAccount("Must be an email address.")
    else if (model.data.password.lengthCompare(8) < 0) InvalidPassword("Password must be at lease 8 chars")
    else Valid(AjaxRequest(ServerAction.Authenticate(model.data)))
  }
}

final case class LoginFormData(account: String = "guest@domain.com",
                               password: String = "00000000") extends Data

