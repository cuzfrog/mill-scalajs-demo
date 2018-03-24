package demo

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

sealed trait Model extends Product with Serializable
sealed trait Data extends Model

final case class RootModel(session: Session,
                           navigation: NavigationModel,
                           loginForm: LoginFormModel) extends Model

final case class NavigationModel(currentPage: Page = Page.Login) extends Model
final case class Session(sessionId: Long = -1L) extends Model{
  def toHttpHeader: Map[String, String] = ???
}

final case class ButtonModel(isLoading: Boolean = false) extends Model

final case class LoginFormModel(data: LoginFormData = LoginFormData(),
                                accountErrMsg: String = "", passwordErrMsg: String = "",
                                submitButton: ButtonModel = ButtonModel()) extends Model
final case class LoginFormData(account: String = "guest@domain.com",
                               password: String = "00000000") extends Data

object LoginFormModel {
  import demo.ValidationAction.LoginFormValidationAction._

  implicit val loginFormValidator: Validator[LoginFormModel] = (model: LoginFormModel) => Future {
    println(s"Validate: ${model.data}")
    if (!model.data.account.matches("""\w+@\w+(\.\w+)+""")) InvalidAccount("Must be an email address.")
    else if (model.data.password.lengthCompare(8) < 0) InvalidPassword("Password must be at lease 8 chars")
    else Valid(AjaxAction.AjaxRequest(model.data))
  }
}