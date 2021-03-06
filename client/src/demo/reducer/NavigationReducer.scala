package demo.reducer

import demo.Page
import demo.model.{ClientStore, NavigationModel, ClientRootModel}
import diode.{ActionHandler, Effect}

private[reducer] final class NavigationReducer extends ActionHandler[ClientRootModel, NavigationModel](ClientStore.zoomTo(_.navigation)) {
  import demo.action._

  override protected def handle = {
    case LoginAction =>
      println("navigation Login!")
      updated(value.copy(currentPage = Page.ChatRoom), Effect.action(LoginFormAction.LoginClear))
  }
}
