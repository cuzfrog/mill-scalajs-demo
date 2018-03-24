package demo.model

import play.api.libs.json.{Json, Reads, Writes}

final case class LoginFormData(account: String = "guest@domain.com",
                               password: String = "00000000") extends Data

object LoginFormData{
  implicit val jsonWrites: Writes[LoginFormData] = Json.writes[LoginFormData]
  implicit val jsonReads: Reads[LoginFormData] = Json.reads[LoginFormData]
}