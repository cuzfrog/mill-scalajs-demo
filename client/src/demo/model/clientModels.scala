package demo.model

import demo.Page

final case class ClientRootModel(session: Session,
                           navigation: NavigationModel,
                           loginForm: LoginFormModel) extends Model

final case class NavigationModel(currentPage: Page = Page.Login) extends Model

final case class ButtonModel(isLoading: Boolean = false) extends Model
