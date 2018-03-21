package demo

final case class LoginForm(user: String, password: String)

final case class Message(userId: Long, text: String)