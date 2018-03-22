package demo


final case class RootModel(loginForm: LoginForm)

final case class LoginForm(account: String = "", password: String = "")