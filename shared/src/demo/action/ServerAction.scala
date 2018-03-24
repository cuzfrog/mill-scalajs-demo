package demo.action

import demo.model.Credential
import diode.Action

import scala.concurrent.Promise

sealed trait ServerAction extends Action with Product with Serializable

final case class Authenticate(credential: Credential) extends ServerAction
final case class ProcessRequest(promise: Promise[AjaxResponse], requestAction: Action) extends ServerAction
