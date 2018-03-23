package demo

sealed trait ButtonStatus

object ButtonStatus{
  case object Ready extends ButtonStatus
  case object Loading extends ButtonStatus
  case object Disabled extends ButtonStatus
}
