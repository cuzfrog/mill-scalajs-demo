package demo.model

import demo.Validator
import demo.action.LoginFormValidationAction
import play.api.libs.json.{Json, Reads, Writes}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

final case class Credential(account: String = "guest@domain.com",
                               password: String = "00000000") extends Data

object Credential{
  implicit val jsonWrites: Writes[Credential] = Json.writes[Credential]
  implicit val jsonReads: Reads[Credential] = Json.reads[Credential]

  import LoginFormValidationAction._

  implicit val loginFormValidator: Validator[Credential] = (data: Credential) => Future {
    println(s"Validate: $data")
    if (!data.account.matches("""\w+@\w+(\.\w+)+""")) InvalidAccount("Must be an email address.")
    else if (data.password.lengthCompare(8) < 0) InvalidPassword("Password must be at lease 8 chars")
    else Valid(data)
  }
}