package demo.action

import demo.model.Session
import diode.Action

trait SystemAction extends Action with Product with Serializable

final case class JsonError(log: String) extends SystemAction
final case class StoreSession(session: Session) extends SystemAction