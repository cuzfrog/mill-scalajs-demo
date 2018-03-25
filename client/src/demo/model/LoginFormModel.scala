package demo.model

final case class LoginFormModel(data: Credential = Credential(),
                                submitButton: ButtonModel = ButtonModel(),
                                errMsg: Map[String, String] = Map.empty) extends Model