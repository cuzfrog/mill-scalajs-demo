package demo.model

import demo.Validator
import demo.action._
import play.api.libs.json.{Json, Reads, Writes}

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

final case class LoginFormData(account: String = "guest@domain.com",
                               password: String = "00000000") extends Data

object LoginFormData{
  implicit val jsonWrites: Writes[LoginFormData] = Json.writes[LoginFormData]
  implicit val jsonReads: Reads[LoginFormData] = Json.reads[LoginFormData]
}