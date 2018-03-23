package demo


final case class RootModel(greetingWords: String,
                           loginForm: LoginFormData)

final case class LoginFormData(account: String = "", password: String = "")