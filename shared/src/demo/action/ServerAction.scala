package demo.action

import demo.model.LoginFormData
import diode.Action

private[demo] sealed trait ServerAction extends Action with Product with Serializable
private[demo] object ServerAction{
  final case class Authenticate(credential: LoginFormData) extends ServerAction
}