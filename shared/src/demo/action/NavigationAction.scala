package demo.action

import diode.Action

private[demo] sealed trait NavigationAction extends Action with Product with Serializable

private[demo] object NavigationAction {
  final case object Login extends NavigationAction
}