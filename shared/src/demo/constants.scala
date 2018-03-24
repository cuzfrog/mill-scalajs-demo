package demo

sealed trait ButtonStatus
object ButtonStatus{
  case object Ready extends ButtonStatus
  case object Loading extends ButtonStatus
  case object Disabled extends ButtonStatus
}

sealed trait Page extends Product with Serializable
object Page{
  final case object Login extends Page
  final case object ChatRoom extends Page
}