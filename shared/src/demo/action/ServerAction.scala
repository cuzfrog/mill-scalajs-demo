package demo.action

import demo.model.LoginFormData
import diode.Action

sealed trait ServerAction extends Action with Product with Serializable

final case class Authenticate(credential: LoginFormData) extends ServerAction