package demo.model

final case class LoginFormModel(data: Credential = Credential(),
                                submitButton: ButtonModel = ButtonModel(),
                                enableClientValidation: Boolean = false,
                                errMsg: Map[String, String] = Map.empty) extends Model