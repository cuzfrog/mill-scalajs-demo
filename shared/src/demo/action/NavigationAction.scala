package demo.action

import diode.Action

sealed trait NavigationAction extends Action with Product with Serializable

object NavigationAction {
  final case object Login extends NavigationAction
}