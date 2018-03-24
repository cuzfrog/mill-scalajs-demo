package demo.model

import demo._

trait Model extends Product with Serializable
trait Data extends Model

final case class RootModel(session: Session,
                           navigation: NavigationModel,
                           loginForm: LoginFormModel) extends Model

final case class NavigationModel(currentPage: Page = Page.Login) extends Model

final case class Session(sessionId: Long = -1L) extends Model{
  def toHttpHeader: Map[String, String] = ???
}

final case class ButtonModel(isLoading: Boolean = false) extends Model


