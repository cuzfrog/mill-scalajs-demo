package demo.model

final case class LoginFormModel(data: Credential = Credential(),
                                accountErrMsg: String = "", passwordErrMsg: String = "",
                                submitButton: ButtonModel = ButtonModel()) extends Model