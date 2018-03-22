package demo


final case class RootModel(loginForm: LoginFormData)

final case class LoginFormData(account: String = "", password: String = "")